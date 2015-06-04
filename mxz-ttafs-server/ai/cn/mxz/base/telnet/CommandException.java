package cn.mxz.base.telnet;

/**
 * 命令行执行错误
 * @author 林岑
 *
 */
public class CommandException extends RuntimeException {

	private static final long serialVersionUID = 2089845773201051881L;

	public CommandException(String m) {
		super(m);
	}
}
