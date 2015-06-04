package cn.mxz.yoxi;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

import java.io.PrintWriter;
import java.io.StringWriter;

import cn.javaplus.util.Util;
import cn.mxz.util.debuger.SystemLog;

public class YoXiR {

	private class WatchDogThread extends Thread {

		private RunScriptThread thread;

		private long startTime;

		private WatchDogThread(RunScriptThread thread) {
			this.thread = thread;
			startTime = System.currentTimeMillis();
		}

		@SuppressWarnings("deprecation")
		@Override
		public void run() {

			while (true) {

				long now = System.currentTimeMillis();
				long alive = now - startTime;

				if (alive > thread.getTimeOut()) {
					out.println("脚本执行超时!");
					thread.stop();
					break;
				}
				cn.javaplus.util.Util.Thread.sleep(100);
			}
		}

	}

	private class RunScriptThread extends Thread {

		private GroovyObject o;

		private int timeOut;

		private RunScriptThread(GroovyObject o) {
			this.o = o;
		}

		@Override
		public void run() {
			try {
				Object result = o.invokeMethod("run", null);
				out.println(result);
			} catch (Throwable e) {
				String message = e.getMessage();
				if(message == null) {
					SystemLog.error("", e);
				}
				else if(message.contains("已失效")) {//“else” 是lk增加，否则eclipse这边会报异常
					SystemLog.error("不要使用User.getData()来获取数据", e);
				}
				e.printStackTrace(out);
			}
		}

		public void setTimeOut(int timeOut) {
			this.timeOut = timeOut;
		}

		public int getTimeOut() {
			return timeOut;
		}
	}

	private StringWriter sw = new StringWriter();
	PrintWriter out = new PrintWriter(sw);
	private RunScriptThread thread;

	public String getResult() {
		String string = sw.toString();
		return string;
	}

	public void waitItRunOver() {
		try {
			thread.join();
		} catch (InterruptedException e) {
			throw Util.Exception.toRuntimeException(e);
		}
	}

	@SuppressWarnings({ "rawtypes" })
	public void run(String script) {

		ClassLoader parent = ClassLoader.getSystemClassLoader();
		GroovyClassLoader loader = new GroovyClassLoader(parent);
		Class gclass = loader.parseClass(script);

		try {
			GroovyObject groovyObject = (GroovyObject) gclass.newInstance();
			runInThread(groovyObject);
		} catch (Exception e) {
			e.printStackTrace(out);
		} finally {
			loader.clearCache();
		}

	}

	private void runInThread(final GroovyObject o) {

		thread = new RunScriptThread(o);
		thread.setTimeOut(60000);
		Thread t2 = new WatchDogThread(thread);
		thread.start();
		t2.start();

		try {
			thread.join();
		} catch (InterruptedException e) {
			throw new RuntimeException();
		}
	}
}
