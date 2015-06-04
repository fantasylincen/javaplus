package com.cnbizmedia.recharge;

import java.io.IOException;

import cn.javaplus.log.Log;
import cn.javaplus.util.Util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cnbizmedia.Server;
import com.cnbizmedia.config.GameProperties;
import com.cnbizmedia.config.GameServersXml;
import com.cnbizmedia.config.ServerNode;
import com.cnbizmedia.gen.dto.MongoGen.Daos;
import com.cnbizmedia.gen.dto.MongoGen.OrderDao;
import com.cnbizmedia.gen.dto.MongoGen.OrderDao.OrderDtoCursor;
import com.cnbizmedia.gen.dto.MongoGen.OrderDto;
import com.cnbizmedia.gen.dto.MongoGen.OrderFinishDto;
import com.cnbizmedia.user.User;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Request;
import com.ning.http.client.RequestBuilder;

public class RechargeManager implements Runnable {

	private static final int PROCESS_SPACE = 5000;

	private class AsyncHandlerImpl extends AbstractAsyncHandler {

		private final OrderDto dto;

		public AsyncHandlerImpl(OrderDto dto) {
			this.dto = dto;
		}

		@Override
		public void onError() {
			delayProcess(dto);// 订单延时处理
			Log.d("订单处理错误:" + dto.getServerId() + "," + dto.getId() + ","
					+ dto.getUserId());
		}

		
		@Override
		public void onFinish() {

		}

		@Override
		protected void onCompleted(String bodyContent) {
			Log.d("游戏服务器反馈:" + bodyContent);
			JSONObject js = null;
			try {
				js = JSON.parseObject(bodyContent);
			} catch (Exception e) {
				String r = "gs-callback-error";
				setReason(r);
				onError();
				return;
			}
			String code = js.getString("code");
			if (!"success".equals(code)) {
				String r = js.getString("desc");
				setReason(r);
				onError();
				return;
			}

			dto.setLastProcessTime(System.currentTimeMillis());
			moveToFinish(dto);

			Log.d("订单处理成功:" + dto.getId());
		}

		private void setReason(String r) {
			dto.setReason(r);
			Daos.getOrderDao().save(dto);
		}

	}

	private void pushToRechargeQueue(OrderDto dto) {

		ServerNode node = GameServersXml.getServer(dto.getServerId());

		final AsyncHttpClient c = new AsyncHttpClient();
		RequestBuilder b = new RequestBuilder("POST");
		b = b.setUrl(node.getString("rechargeUrl"));
		b = b.addHeader("Content-Type", "application/x-www-form-urlencoded");

		ParameterSetter ps = getSetter(node);
		ps.addParameters(b, dto, node);

		Request request = b.build();
		try {
			c.prepareRequest(request).execute(new AsyncHandlerImpl(dto) {
				@Override
				public void onFinish() {
					c.close();
					c.closeAsynchronously();
				}
			});
			Log.d("提交给了游戏服务器:" + node.getGameName() + " id:"
					+ node.getString("id") + " name:" + node.getString("name")
					+ " 订单号:" + dto.getId());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private ParameterSetter getSetter(ServerNode node) {
		try {
			String string = node.getString("rechargeParameterSetter");
			return (ParameterSetter) Class.forName(string).newInstance();
		} catch (Exception e) {
			throw Util.Exception.toRuntimeException(e);
		}
	}

	public void addOrder(OrderDto o) {
		OrderDao dao = Daos.getOrderDao();
		dao.save(o);
		Log.d("new order:" + JSON.toJSONString(o));
	}

	private void proceess() {
		OrderDao dao = Daos.getOrderDao();
		OrderDtoCursor find = dao.find();
		for (OrderDto dto : find) {
			processOne(dto);
		}
	}

	private void processOne(OrderDto dto) {
		if (isTimeUp(dto)) {
			try {
				pushToRechargeQueue(dto);
			} catch (Exception e) {
				delayProcess(dto);
				e.printStackTrace();
			}
		}
	}

	private boolean isTimeUp(OrderDto dto) {
		long lastProcessTime = dto.getLastProcessTime();
		long l = System.currentTimeMillis() - lastProcessTime;
		long retrySpace = dto.getRetrySpace();
		return l > retrySpace;
	}

	/**
	 * 延期处理订单
	 */
	void delayProcess(OrderDto dto) {
		dto.setLastProcessTime(System.currentTimeMillis());
		long space = dto.getRetrySpace();
		dto.setRetrySpace((long) ((space + 1000) * 1.5));
		if (space > GameProperties.getLong("maxDelayProcessMillis")) {
			dto.setIsError(true);
			Log.d("订单处理次数过多, 系统将其认为错误订单处理了:" + dto.getId());
			moveToFinish(dto);
			onErrorEnd(dto);
		} else {
			Log.d("延期处理订单:" + dto.getId() + " 延期时长:" + space + " ms");
			Daos.getOrderDao().save(dto);
		}
	}

	/**
	 * 最后还是出现了错误, 将订单移入了已处理的订单中, 同时标记这个订单出了错
	 */
	private void onErrorEnd(OrderDto dto) {
		giveBackVbs(dto);
	}

	private void giveBackVbs(OrderDto dto) {
		int vbBack = OrderUtil.getVbNeed(dto);
		User user = Server.loadUserById(dto.getRechargeUserId());
		user.addVb(vbBack);
		Log.d("归还玩家  " + dto.getId() + " V币:" + vbBack);
	}

	private void moveToFinish(OrderDto dto) {
		Daos.getOrderDao().delete(dto);
		Daos.getOrderFinishDao().save(parse(dto));
	}

	private OrderFinishDto parse(OrderDto dto) {
		OrderFinishDto d = new OrderFinishDto();
		d.setCount(dto.getCount());
		d.setId(dto.getId());
		d.setIsError(dto.getIsError());
		d.setLastProcessTime(dto.getLastProcessTime());
		d.setRetrySpace(dto.getRetrySpace());
		d.setReason(dto.getReason());
		d.setServerId(dto.getServerId());
		d.setTime(dto.getTime());
		d.setUserId(dto.getUserId());
		d.setRechargeUserId(dto.getRechargeUserId());
		return d;
	}

	@Override
	public void run() {
		while (true) {
			Util.Thread.sleep(PROCESS_SPACE);
			try {
				proceess();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
