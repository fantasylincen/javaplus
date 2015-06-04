package cn.mxz.base.telnet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

import org.dom4j.Element;
import org.springframework.beans.BeansException;

import cn.javaplus.file.IProperties;
import cn.javaplus.util.Closer;
import cn.javaplus.util.Util;

import com.lemon.commons.logger.Logs;

/**
 *
 * 命令行处理器
 *
 * @author 林岑
 *
 */
public class CommandProcesser extends Thread {


	private class MyListener implements CommandListener {


		@Override
		public void onEnterCommandNode(CommandEvent e) {

			showTips(e.getCurrentElement());	//显示提示性文字给客户端

			try {

				receiveArgs();		//接受当前节点参数列表

			} catch (IOException e1) {

				throw new CommandException(e1.getMessage());
			}
		}

		/**
		 * 显示提示性文字给客户端
		 * @param e
		 */
		@SuppressWarnings("unchecked")
		private void showTips(Element e) {

			List<Element> elements = e.elements("tip");

			for (Element element : elements) {

				println(element.getText());
			}
		}

		@Override
		public void onEntered(EnteredEvent e) {

			sendNodes();
		}

		@Override
		public void onArgsFull(CommandEvent e) {

			Element c = e.getCurrentElement();

			String trim = c.getTextTrim();

			Logs.debug.debug("CommandProcesser.MyListener.onCommand() commandNode: " + trim + " args: " + Arrays.toString(manager.getArgs()));

			TelnetCommand runable = (TelnetCommand) factory.get("telnetCommand:" + e.getCommand());

			try {

				runable.run(out, manager.getArgs());

			} catch (Throwable e1) {

				e1.printStackTrace();

				e1.printStackTrace(out);

			} finally {

				manager.back();
			}
		}
	}


	private PrintWriter out;

	private BufferedReader in;

	private int checkTimes = 0;

	private NodeManager manager;

	private Socket	socket;

	private SpringFactory	factory;

	private TelnetServer	telnetServer;

	CommandProcesser(TelnetServer telnetServer, Socket socket, String configPath, SpringFactory factory) {

		this.telnetServer = telnetServer;

		this.socket = socket;

		this.factory = factory;

		try {

			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

			manager = new NodeManagerImpl(configPath);

			manager.addCommandListener(new MyListener());

		} catch (IOException e) {

			throw new RuntimeException(e);
		}
	}


	private void checkPassword() throws Exception {

		checkTimes++;

		checkPasswordCheckTimes();

		try {

			String ip = socket.getRemoteSocketAddress().toString();

			if(ip.startsWith("/192.168") || ip.startsWith("/127.0.0.1") || ip.startsWith("/0:0:0:0:0:0:0:1:")) {

				return;
			}

			String readLine = in.readLine();
			
			String password = readLine.trim();

			IProperties p = Util.Property.getProperties("config/config.properties");

			if(!password.equals(p.get("gmPassword"))) {

				println("Password error!");

				checkPassword();
			}

		} catch (IOException e) {

			println(e.getMessage());

			checkPassword();
		}
	}

	private void println(Object o) {

		out.println(o);

		out.flush();
	}

	private void print(Object o) {

		out.print(o);

		out.flush();
	}

	private void checkPasswordCheckTimes() throws Exception {

		if(checkTimes > 6) {

			throw new Exception("密码尝试次数超限!");
		}
	}

	private void showWelcome() {

		println("请输入登陆密码:");
	}

	@Override
	public void run() {

		showWelcome();

		try {

			checkPassword();

			println("密码验证通过,您现在可以输入命令了");

			telnetServer.dispatchEvent(new TelnetServerLoginEvent(out));

			sendNodes();

			loopCommand();

		} catch (Exception e) {

			Logs.debug.debug("命令处理错误:" + e.getMessage());

		} finally {

			Closer.close(in);

			Closer.close(out);
		}
	}

	private void loopCommand() {

		int max = 1000;

		for(int i = 0; i < max; i++) {

			if(i >= max - 10) {

				println("您还剩" + (max - i) + "次命令输入机会, " + (max - i) + "次之后, 请重新登陆!");
			}

			print(manager);

			try {

				String line = in.readLine();

				Logs.debug.debug("CommandProcesser.loopCommand() command:" + line);

				if(line == null) {

					Logs.debug.debug("CommandProcesser.loopCommand() GM 管理员下线!");

					break;
				}

				String command = line.trim();

				if(!command.isEmpty()) {

					processCommand(command);
				}

			} catch (CommandException e) {

				println(e.getMessage());

			} catch (BeansException e) {

				println("未知命令 : " + e.getMessage());

			} catch (Error e) {

				println("Error : " + e.getMessage());

			} catch (Exception e) {

				e.printStackTrace(out);

				println("未知错误 : " + e.getMessage());

				Logs.debug.error("读取命令行异常!" + e.getMessage());
			}
		}
	}

	private void processCommand(String command) throws IOException {

		if(command.equals("back")) {

			manager.back();

			return;
		}

		try {

			int selecte = new Integer(command);

			manager.enterNode(selecte);

			Logs.debug.debug("CommandProcesser.processCommand() " + manager);

		} catch (NumberFormatException e) {

			throw new CommandException("您只能输入数字!");
		}
	}

	/**
	 * 接受用户输入的参数
	 * @throws IOException
	 */
	private void receiveArgs() throws IOException {

		while(true) {

			Element e = manager.nextArgNode();

			if(e == null) {

				break;
			}

			print(e.getTextTrim());

			String arg = in.readLine().trim();

			if(arg.isEmpty()) {

				out.println("输入参数为空, 请重新输入!");

				continue;
			}

			Logs.debug.debug("CommandProcesser.receiveArgs() 接收到参数:" + arg);

			manager.addArg(arg);
		}
	}


	/**
	 * 发送玩家可选项
	 */
	private void sendNodes() {

		println("");
//		println(" 服务器ID: " + ServerConfig.getServerId() + " 当前在线人数:" + world.getOnlineAll().size());
		println("----------MENU----------");

		List<Element> elements = manager.nextNodes();

		for (int i = 0; i < elements.size(); i++) {

			Element e1 = elements.get(i);

			println(i + "." + e1.getTextTrim());
		}

		println("----------END-----------");
		println("");
	}
}
