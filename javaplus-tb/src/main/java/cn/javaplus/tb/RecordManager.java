package cn.javaplus.tb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.javaplus.log.Log;
import cn.javaplus.tb.gen.dto.MongoGen.Daos;
import cn.javaplus.tb.gen.dto.MongoGen.RecordDao;
import cn.javaplus.tb.gen.dto.MongoGen.RecordDao.RecordDtoCursor;
import cn.javaplus.tb.gen.dto.MongoGen.RecordDto;
import cn.javaplus.tb.gen.dto.MongoGen.TbItemDto;
import cn.javaplus.tb.http.AbstractAsyncHandler;
import cn.javaplus.util.Util;

import com.google.common.collect.Lists;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Request;
import com.ning.http.client.RequestBuilder;

public class RecordManager {

	public class UpdateThread extends Thread {

		@Override
		public void run() {
			while (true) {
				updateAll();
				Util.Thread.sleep(20000 * 3600);
			}
		}
	}

	public RecordManager() {
		new UpdateThread().start();
	}

	private final class ProcessNextOnCallBack implements CallBack {
		private final AsyncHttpClient c;
		private final ConcurrentLinkedQueue<RecordAdaptor> tasks;

		public ProcessNextOnCallBack(AsyncHttpClient c,
				ConcurrentLinkedQueue<RecordAdaptor> tasks) {
			this.c = c;
			this.tasks = tasks;
		}

		@Override
		public void onFinish() {
			c.close();
			c.closeAsynchronously();
			process(tasks);
		}
	}

	private final class RequestSourceHandler extends AbstractAsyncHandler {
		private final class RequestMineHandler extends AbstractAsyncHandler {

			@Override
			protected void onFinish() {
				callBack.onFinish();
			}

			@Override
			public void onError() {
				Log.e("request mine error:" + poll.getMineId());
			}

			@Override
			protected void onCompleted(String bodyContent) {
				poll.getDto().setMine(buildDtoByHtml(bodyContent));
				poll.getDto().setLastUpdateTime(System.currentTimeMillis());
				update();
				Log.d("request mine completed");
			}

		}

		private void update() {
			Daos.getRecordDao().save(poll.getDto());
		}

		private final AsyncHttpClient c;
		private final CallBack callBack;
		private final RecordAdaptor poll;

		private RequestSourceHandler(AsyncHttpClient c, CallBack callBack,
				RecordAdaptor poll) {
			this.c = c;
			this.callBack = callBack;
			this.poll = poll;
		}

		@Override
		protected void onFinish() {
			request(c, poll.getUrl(), new RequestMineHandler());
		}

		@Override
		public void onError() {
			Log.e("request mine error:" + poll.getSourceId());
		}

		@Override
		protected void onCompleted(String bodyContent) {
			poll.getDto().setSource(buildDtoByHtml(bodyContent));
			update();
			Log.d("request source completed");
		}

	}

	private TbItemDto buildDtoByHtml(String bodyContent) {
		TbItemDto dto = new TbItemDto();

		bodyContent = bodyContent.replaceAll("\r\n", " ");
		bodyContent = bodyContent.replaceAll("\r", " ");
//
//		Pattern p = Pattern.compile("<em id=\"J_Price\" class=\"tb-rmb-num\">[0-9\\.]{1,8}</em>");
//		Matcher m = p.matcher(bodyContent);
//		if(m.find())
//			dto.setPrice(m.group().replaceAll("<em id=\"J_Price\" class=\"tb-rmb-num\">", "").replaceAll("</em>", ""));
//		else 
//			dto.setPrice("NOT FOUND");
//		
//		
//		Pattern p2 = Pattern.compile("\"库存\" <span id=\"J_SpanStock\" class=\"tb-count\">[0-9]+</span>");
//		Matcher m2 = p2.matcher(bodyContent);
//		if(m2.find())
//			dto.setCount(m.group().replaceAll("\"库存\" <span id=\"J_SpanStock\" class=\"tb-count\">", "").replaceAll("</em>", ""));
//		else 
//			dto.setCount("NOT FOUND");
//		
//		
//		
		
		
		

		Pattern p = Pattern.compile("price:[0-9\\.]+");
		Matcher m = p.matcher(bodyContent);
		if(m.find())
			dto.setPrice(m.group().replaceAll("price:", ""));
		else 
			dto.setPrice("NOT FOUND");
		
		
		Pattern p2 = Pattern.compile("<span id=\"J_SpanStock\" class=\"tb-count\">[0-9]+</span>");
		Matcher m2 = p2.matcher(bodyContent);
		if(m2.find())
			dto.setCount(m2.group().replaceAll("<span id=\"J_SpanStock\" class=\"tb-count\">", "").replaceAll("</em>", ""));
		else 
			dto.setCount("NOT FOUND");
		
		
		
		
		return dto;
	}

	public interface CallBack {
		void onFinish();
	}

	public void updateAll() {
		ConcurrentLinkedQueue<RecordAdaptor> tasks = new ConcurrentLinkedQueue<RecordAdaptor>();
		for (RecordDto r : getRecords()) {
			tasks.add(new RecordAdaptor(r));
		}
		process(tasks);
	}

	public List<RecordDto> getRecords() {
		RecordDao dao = Daos.getRecordDao();
		RecordDtoCursor find = dao.find();
		ArrayList<RecordDto> ls = Lists.newArrayList();
		for (RecordDto dto : find) {
			ls.add(dto);
		}
		return ls;
	}

	private void process(ConcurrentLinkedQueue<RecordAdaptor> tasks) {
		RecordAdaptor poll = pollFirstTimeOut(tasks);
		if (poll != null) {
			Log.d("process " + poll.getSourceId() + " ...");
			final AsyncHttpClient c = new AsyncHttpClient();

			ProcessNextOnCallBack cb = new ProcessNextOnCallBack(c, tasks);// 当处理完毕后,
			// 紧接着处理下一个
			RequestSourceHandler handler = new RequestSourceHandler(c, cb, poll);
			request(c, poll.getSourceUrl(), handler);
		}
	}

	private RecordAdaptor pollFirstTimeOut(
			ConcurrentLinkedQueue<RecordAdaptor> tasks) {
		if (tasks.isEmpty())
			return null;
		while (true) {
			RecordAdaptor poll = tasks.poll();
			if (poll.isTimeUp())
				return poll;

		}
	}

	private void request(AsyncHttpClient c, String url,
			AbstractAsyncHandler handler) {

		RequestBuilder b = new RequestBuilder("GET");
		b = b.setUrl(url);

		b = b.addHeader("Content-Type", "application/x-www-form-urlencoded");

		Request request = b.build();
		try {
			c.prepareRequest(request).execute(handler);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
