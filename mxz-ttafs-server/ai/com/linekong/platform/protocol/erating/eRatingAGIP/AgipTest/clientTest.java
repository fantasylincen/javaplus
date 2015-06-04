package com.linekong.platform.protocol.erating.eRatingAGIP.AgipTest;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class clientTest extends debugTest {
		public static void main(String args[]){
			InetSocketAddress endpoint=new InetSocketAddress("127.0.0.1",10000);
			Socket socket=null;
			OutputStream out=null;
			InputStream in=null;
			
			byte[] sendMessage={'l','i','u'};
			try{
				socket=new Socket();
				socket.setSoLinger(true, 2);
			   //socket.setSoTimeout(2000);
			   socket.setSendBufferSize(32*1024);
			   socket.setReceiveBufferSize(32*1024);
			   socket.setTcpNoDelay(true);
			   socket.connect(endpoint);
			   out = socket.getOutputStream();   
			   in = socket.getInputStream();
			   
			   
			   out.write(sendMessage);
			   out.flush();
			   
			   System.out.println("client already send msg and wating rev ...");	
			   //bindӦ
			   in=socket.getInputStream();
		
			   byte[] charBuf = new byte[4096]; 
			   int size = 0; 
			   size = in.read(charBuf , 0 , 4096);
			   System.out.println("client received  length="+size);			  
				  
			  
			   out.close();
			   in.close();
			   socket.close();

				
			} catch ( Exception e)
			{
				System.out.println("Error"+e);			
			}

		}
}
