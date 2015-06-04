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
			Log.d("order process error:" + dto.getServerId() + "," + dto.getId() + ","
					+ dto.getUserId());
		}

		@Override
		public void onFinish() {

		}

		@Override
		protected void onCompleted(String bodyContent) {
			Log.d("game server call back:" + bodyContent);
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

			Log.d("order process ok:" + dto.getId());
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
			Log.d("submit to game server:" + node.getGameName() + " id:"
					+ node.getString("id") + " name:" + node.getString("name")
					+ " order id:" + dto.getId());
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
			Log.d("order process too many times, system mark it error:" + dto.getId());
			moveToFinish(dto);
			onErrorEnd(dto);
		} else {
			Log.d("delay order:" + dto.getId() + " delay:" + space + " ms");
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
		Log.d("give back to user  " + dto.getId() + " VCoin:" + vbBack);
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
