package cn.vgame.result;

import cn.javaplus.excel.Row;
import cn.javaplus.excel.Sheet;
import cn.vgame.share.GameException;
import cn.vgame.share.IErrorResult;
import cn.vgame.share.Xml;

public abstract class ErrorResultBase implements IErrorResult {

	private int code = 10001;
	private Object error;
	private Object[] args = new String[0];

	public ErrorResultBase(String error) {
		this.error = error;
	}

	public ErrorResultBase(int code, Object... args) {
		this.code = code;
		if (args != null)
			this.args = args;
		error = buildText();
	}

	/* (non-Javadoc)
	 * @see cn.vgame.a.result.IErrorResult#getCode()
	 */
	@Override
	public int getCode() {
		return code;
	}

	/* (non-Javadoc)
	 * @see cn.vgame.a.result.IErrorResult#getArgs()
	 */
	@Override
	public Object[] getArgs() {
		return args;
	}

	/* (non-Javadoc)
	 * @see cn.vgame.a.result.IErrorResult#getError()
	 */
	@Override
	public Object getError() {
		return error;
	}

	private String buildText() {
		Xml xml = getXml();
		Sheet s = xml.get("messages");
		Row row = s.get(code);
		if(row == null)
			return "undefined code:" + code;
		String value = row.get("value");

		for (int i = 0; i < args.length; i++) {
			value = value.replaceFirst("%s" + i, args[i] + "");
		}
		return value;
	}

	protected abstract Xml getXml();

	public GameException toException() {
		return new GameException(this);
	}
}