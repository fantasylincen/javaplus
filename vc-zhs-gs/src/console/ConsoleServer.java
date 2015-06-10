package console;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;



/**
 * 控制服务器
 * @author DXF
 */
public class ConsoleServer implements Runnable{

    private volatile static boolean			isRunning 	= true;
    private ServerSocket	sSocket						= null;
    private final int 	PORT 							= 7171;
    
	@Override
	public void run() {
		
		Socket socket		= null;
		
        while( isRunning ) {

            try {
               
            	 // 等待用户连接
            	socket 				= sSocket.accept();
                new Factorys( socket );
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

    public void start() {
    	try {
    		User.init();
    		CommandManager.init();
    		sSocket		= new ServerSocket( PORT );
    		new Thread( this ).start();
		} catch ( IOException e ) { }
    }

    public void stop() {
        isRunning 	= false;
        try {
	        if( sSocket != null ){
	        	sSocket.close();
	            sSocket		= null;
	        }
        } catch ( IOException e ) { }
    }
    
    
    public static void main( String[] arg ) throws IOException{
    	new ConsoleServer().start();
    }
	
}
