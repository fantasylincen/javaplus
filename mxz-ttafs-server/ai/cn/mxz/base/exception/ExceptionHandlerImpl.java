package cn.mxz.base.exception;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import message.S;
import cn.javaplus.exception.InvalidValuesException;
import cn.javaplus.util.Util;
import cn.mxz.city.City;
import cn.mxz.util.debuger.Debuger;

public class ExceptionHandlerImpl implements ExceptionHandler {

	private List<String> excludes = new ArrayList<String>();

	private static ExceptionHandlerImpl instance;

	private ExceptionHandlerImpl() {

		List<String> lines = Util.File.getLines("res/excludeException.txt");

		for (String line : lines) {
			line = line.trim();
			if (!line.isEmpty()) {
				excludes.add(line);
			}
		}

	}

	public static final ExceptionHandlerImpl getInstance() {
		if (instance == null) {
			instance = new ExceptionHandlerImpl();
		}
		return instance;
	}

	@Override
	public void process(Throwable exception, City user) {

		String id = user != null ? user.getId() : "unknown";

		try {
			throw exception;

		} catch (IllegalProtocolException e) {
			e.printStackTrace();
			Debuger.error("[非法协议异常]", buildMessage(e) + " user = " + id);

		} catch (SureIllegalOperationException e) {
			sendMessage(user, S.S0, "[" + e.getText() + "]");
			Debuger.error("[非法操作异常]", e.getText() + " user = " + id);

		} catch (cn.mxz.thirdpaty.ThirdPartyException e) {
			// sendMessage(user, S.S0, "[EratingException]" + "[" +
			// e.getMessage() + "]");
			Debuger.error(e.getMessage() + " user = " + id);
			Debuger.error("[Erating异常]", e);

		} catch (MessageOnlyException e) {
			sendMessage(user, e.getCode(), e.getInfos());
			Debuger.error("[操作失败]", e + " user = " + id);

		} catch (OperationFaildException e) {
			sendMessage(user, e.getCode(), e.getInfos());
			Debuger.error("[操作失败]", e + " user = " + id);

		} catch (InvalidValuesException e) {
			Debuger.error("[非法数值异常]", "[User:" + id + "]", e);

		} catch (UnImportentException e) {
			System.err.println("[无影响的异常]" + e.getMessage());
			System.err.println("[无影响的异常]" + e.getMessage());
			Debuger.error("[无影响的异常]", "[User:" + id + "]" + e.getMessage());

		} catch (Throwable e) {

			try {
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				e.printStackTrace(pw);
				pw.close();
				sw.close();
				e.printStackTrace();
				
				Debuger.error("[未知异常] " + sw.toString());
			} catch (Exception e1) {
				Debuger.error("[未知异常xxx]", e1);
//				e1.printStackTrace();
			}

			Debuger.error("[未知异常]", e);
		}
	}

	private void sendMessage(City user, int code, Object... string) {
		if (user != null)
			user.getMessageSender().send(code, string);
	}

	private String buildMessage(Exception e) {
		String text = e.getMessage() != null ? e + "" : "";
		return text;
	}

	@Override
	public void filter(Throwable e) {
		StackTraceElement[] traces = e.getStackTrace();
		List<StackTraceElement> ls = new ArrayList<StackTraceElement>();
		for (StackTraceElement s : traces) {
			if (!isExlude(s)) {
				ls.add(s);
			}
		}
		e.setStackTrace(build(ls));
	}

	private StackTraceElement[] build(List<StackTraceElement> ls) {
		StackTraceElement[] sts = new StackTraceElement[ls.size()];
		for (int i = 0; i < ls.size(); i++) {
			StackTraceElement s = ls.get(i);
			sts[i] = s;
		}
		return sts;
	}

	private boolean isExlude(StackTraceElement s) {
		for (String regex : excludes) {
			if (s.getClassName().contains(regex.trim())) {
				return true;
			}
		}
		return false;
	}
}
