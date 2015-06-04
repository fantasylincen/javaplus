package cn.mxz.log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Date;

import cn.mxz.base.config.ServerConfig;
import cn.mxz.log.MXZLogger.LogData;

public  class LogDataImpl implements LogData {

	private Date		date;

	private Object		head;
	private Object		message;
	private Throwable	t;

	public LogDataImpl(Object head, Object message, Throwable t) {
		date = new Date(System.currentTimeMillis());
		this.head = head;
		this.message = message;
		this.t = t;
	}


	@Override
	public int getServerId() {
		return ServerConfig.getConfig().getId();
	}

	@Override
	public Date getTime() {
		return date;
	}

	@Override
	public String getHead() {
		return head == null ? "" : head.toString();
	}

	@Override
	public String getLog() {
		String mes = message == null ? "" : message.toString();
		if (t != null) {
			StringWriter sw = new StringWriter();
			PrintWriter p = new PrintWriter(sw);
			p.println(mes);
			t.printStackTrace(p);

			mes = sw.toString();
		}
		return mes;
	}

	@Override
	public String toString() {
		return "[" + MXZLogger.F.format(getTime()) + "] [" + getHead() + "] ["+ getLog() + "]";
	}

}