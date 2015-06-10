package telnet;

import java.io.PrintWriter;

/**
 * telnet命令
 * @author deng
 */
public interface TelnetCommand {

	/**
	 * 执行该命令
	 * @param out
	 * @param args	参数列表
	 * @return	命令执行结果
	 * @throws Throwable
	 */
	void run(PrintWriter out, String... args) throws Throwable;
}
