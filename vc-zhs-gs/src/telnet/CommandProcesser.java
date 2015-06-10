package telnet;

import game.log.Logs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

import server.ServerManager;


import define.SystemCfg;

/**
* 命令行处理器
* @author deng
*/
public class CommandProcesser {

	private PrintWriter 		out;

	private BufferedReader 		in;

	private int 				checkTimes 		= 0;

	private NodeManagerImpl		manager;

	private Socket				socket;
	
	//3.超级管理员  2.普通管理员
	private byte				jurisdiction	= 0;
	
	public CommandProcesser(  Socket socket ) {

		this.socket 		= socket;

		try {

			in 				= new BufferedReader( new InputStreamReader(socket.getInputStream()) );

			out 			= new PrintWriter( new OutputStreamWriter(socket.getOutputStream()) );

			manager 		= new NodeManagerImpl( );

			welcome();
			
		} catch (IOException e) {

			throw new RuntimeException(e);
		}
	}

	public void start() throws IOException {
//		execute();
		new Thread( new Ticker() ).start();
	}

	private void checkPassword() throws Exception {

		checkTimes++;

		checkPasswordCheckTimes();

		try {
			
			String password = inputText();

//			String ip 		= socket.getRemoteSocketAddress().toString();

//			if(ip.startsWith("/192.168") || ip.startsWith("/127.0.0.1") || ip.startsWith("/0:0:0:0:0:0:0:1:")) {

//				throw new Exception("连接位置有问题!");
//			}

			if( password.endsWith( "vc2013" ) ){
				jurisdiction = 3;
			}else if( password.endsWith( "vc2014" ) ){
				jurisdiction = 2;
			}else{
				printlnErr("密码错误!");

				checkPassword();
			}

		} catch (IOException e) {

			printlnErr(e.getMessage());

			checkPassword();
		}
	}


	private void checkPasswordCheckTimes() throws Exception {
		if(checkTimes > 6) {
			throw new Exception("密码尝试次数超限!");
		}
	}

	private void showWelcome() {
		println("请输入登陆密码:");
	}

	private void execute() throws IOException {

		showWelcome();

		try {

			checkPassword();

			println("密码验证通过,您现在可以输入命令了");

			sendNodes();

			// 开始接受 用户输入
			receiveArgs();
			
		} catch (Exception e) {

			printlnErr( e.getMessage() );

		} 
	}

	private class Ticker implements Runnable {
		@Override
		public void run() {
			
			try {
				execute();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
	}
	
	/**
	 * 接受用户输入的参数
	 * @throws IOException
	 */
	public void receiveArgs() throws IOException  {

		while(true) {

			try {
				
				// 接受输入
				String arg = inputText();

				if( arg.isEmpty() ) {

					println("命令为空, 请重新输入!");

					continue;
				}

				Logs.debug("CommandProcesser.receiveArgs() 接收到参数:" + arg);

				manager.addArg( arg, jurisdiction );
				
				// 输出参数
				print( manager.getTips() );
				
				// 如果可以执行 那么就执行
				manager.run( out, jurisdiction );
				
			} catch (Exception e) {
				
				if( e.getMessage() == null || e.getMessage().equals( "Stream closed" ) ){
					try {
						socket.close();
						in.close();
						out.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					socket 	= null;
					return;
				}
				
				println( e.getMessage() );
			}
		}
		
	}

	/**
	 * 发送玩家可选项
	 */
	private void sendNodes() {

		ServerManager.printCommands( out, jurisdiction );
	}

	// 欢迎
	private void welcome(){
		println( "控制服务器  " + SystemCfg.SERVER_ADDRESS + ":" + SystemCfg.PORT );
		println( SystemCfg.GAME_DISTRICT + "区  " + SystemCfg.SERVER_NAME + " (" + ServerManager.getServerStatus() + ")" );
		println("");
	}
	
	private void println( String o ) {
		out.print( o + "\r\n" );
		out.flush();
	}
	private void print( List<String> o ) {
		if( o.isEmpty() ) return;
		for( String s : o )
			out.print( s + "\r\n" );
		out.flush();
	}
	private void printlnErr( String o ) {
		out.print( "Error: " + o + "\r\n" );
		out.flush();
//		CLog.error( o );
	}
	private String inputText( ) throws IOException {
		out.print( "FSL Server/" + command() + "> " );
		out.flush();
		return in.readLine().trim();
	}
	
	private String command() {
		return !manager.isHave() ? "~" : manager.curNode().getText();
	}

}
