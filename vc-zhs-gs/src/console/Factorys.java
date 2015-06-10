package console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;

/**
* 用户处理
* @author DXF
*/
public class Factorys {

	private Socket				socket;
	private PrintWriter 		out;
	private BufferedReader 		in;

	// 用户   也是登陆密码
	private String root 		= "";
	private int checkTimes 		= 3;

	// 当前命令
	private Command curCommand	= null;
	
	
	public Factorys( final Socket socket ) {

		this.socket 		= socket;
		try {
			in 				= new BufferedReader( new InputStreamReader(socket.getInputStream()) );
			out 			= new PrintWriter( new OutputStreamWriter(socket.getOutputStream()) );
			welcome();
		} catch (Exception e) {
			printlnErr( e.getMessage() );
		}
	}
	
	private String getIP(){ return socket.getInetAddress().getHostAddress(); }
	
	// 欢迎
	private void welcome() throws Exception{
		root 	= User.getRoot( getIP() );
		if( root.isEmpty() ) throw new Exception( "IP " + getIP() + " 未注册!" );
		new Thread( new Ticker() ).start();
	}

	private void showInformation() {
		
	}
	
	private void checkPassword() throws Exception {

		printAndFlush( "login as：" );
		String password = in.readLine().trim();
		
		if( !password.endsWith( root ) ){
			checkPasswordCheckTimes(); // 检查密码使用次数
			printlnErr("用户不存在,还可以尝试" + checkTimes + "次.");
			checkPassword();
		}
	}

	private void checkPasswordCheckTimes() throws Exception {
		if( --checkTimes == 0 ) throw new Exception( "尝试次数已超过3次!" );
	}

	// 线程
	private class Ticker implements Runnable {
		public void run() {
			try {
				checkPassword();// 检查登陆

				showInformation();
				
				receiveArgs();// 开始接受 用户输入
			} catch (Exception e) {
				printlnErr( e.getMessage() );
			} 
		}
	}
	
	
	/** 接受用户输入的参数 */
	public void receiveArgs() {
		String args;
		while(true) {
			try {
				args = inputText();
				String arr[] = args.split( " " );
				if( isSpecialCommand( arr[0] ) ) continue;
				Command command = checkCommandIsExist( arr[0] ) ;
				if( command == null ) { printlnErr( arr[0] + "? command not found!" ); continue; }
				if( !command.nexts.isEmpty() ) curCommand = command;
				
				command.run( arr, out );
			} catch ( Exception e ) {
				if( checkIsDisconnect(e) ) return;
				printlnAndFlush( e.getMessage() ) ;
			}
		}
	}
	
	

	private Command checkCommandIsExist( String command ){
		if( command.isEmpty() ) return null;
		return CommandManager.isExist( curCommand, command );
	}


	private boolean checkIsDisconnect( Exception e ){
		if( e.getMessage() == null || e.getMessage().equals( "Stream closed" ) ){
			try {
				socket.close();
				in.close();
				out.close();
				socket 	= null;
			} catch (IOException e1) { e1.printStackTrace(); }
			printlnErr( "和服务器断开连接." );
			return true;
		}
		return false;
	}
	
	private boolean isSpecialCommand( String command ) throws Exception {
		if( command.equals( "back" ) ) {// 大退
			curCommand = null;
			return true; 
		}
		if( command.equals( "b" ) ) { // 小退
			if( curCommand != null )
				curCommand = curCommand.head;
			return true;
		}
		if( command.equals( "ls" ) ){// 显示可用命令列表
			if( curCommand == null ) 
				throw new Exception( CommandManager.showLs() );
			throw new Exception( curCommand.showLs() );
		}
		if( command.equals( "[A" ) ){ // 执行上一个操作
			
		}
		
		return false;
	}
	
	private void printlnAndFlush(String o) {
		out.print( o + "\r\n" );
		out.flush();
	}
	private void printAndFlush( String o ) {
		out.print( o );
		out.flush();
	}
	private void printlnErr( String o ) {
		out.print( "Error：" + o + "\r\n" );
		out.flush();
	}
	private String inputText( ) throws IOException {
		String date = new SimpleDateFormat("HH:mm:ss").format( System.currentTimeMillis() ); 
		printAndFlush( "[" + date + "@" + root + hierarchy()+"]# " );
		return in.readLine().trim();
	}
	private String hierarchy(){
		if( curCommand == null ) return " /";
		String reslut 	= curCommand.command;
		Command head 	= curCommand.head;
		while( head != null ){
			reslut 	= (head.command + "/" + reslut);
			head 	= head.head;
		}
		return " " + reslut;
	}
	
}

