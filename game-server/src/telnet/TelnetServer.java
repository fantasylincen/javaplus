package telnet;

import game.log.Logs;

import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;



/**
 * 远程服务器 启动类
 * @author DXF
 */
public class TelnetServer implements Runnable{

	private Thread 			thread						= null;
	
    private volatile static boolean			isRunning 	= true;

    private ServerSocket	sSocket						= null;
    
    private int				socketPort					= 7777;
    
	@Override
	public void run() {
		
		Socket socket		= null;
		
        while( isRunning ) {

            try {
               
            	 
            	socket 				= sSocket.accept();
            	
                CommandProcesser cp = new CommandProcesser( socket );
                cp.start();
                
            }catch (IOException e) {

                e.printStackTrace();

                try {
					Thread.sleep(300);
				} catch (InterruptedException e1) {
					e.printStackTrace();
				}
            }

        }
        
	}

    public void start() throws IOException {

    	try {
			
    		sSocket		= new ServerSocket( socketPort );
    		
    		thread		= new Thread( this );
    		thread.start();
    		
	        Logs.debug( "[远程服务器 启动成功]  开始监听 > 端口：" + socketPort );
    	        
		} catch (BindException e) {
			
			Logs.error( e.getMessage() + " TelnetServer 端口已经绑定, 更换端口为:" + ++socketPort );
			start();
		}
    }

    public void stop() throws IOException {
        isRunning 	= false;
        
        if( sSocket != null ){
        	sSocket.close();
            sSocket		= null;
        }
    }
    
    
    public static void main( String[] arg ) throws IOException{
    	new TelnetServer().start();
    }
	
}
