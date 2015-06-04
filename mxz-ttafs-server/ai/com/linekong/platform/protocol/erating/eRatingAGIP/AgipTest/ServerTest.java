package com.linekong.platform.protocol.erating.eRatingAGIP.AgipTest;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerTest extends debugTest {
	public static void main(String args[]){
		
		
		
		try
		{
			ServerSocket  server=new ServerSocket(10000);
			
			Socket socket=null;
			OutputStream out=null;
			InputStream in=null;
			byte[] sendMessage={'s','h','a','n'};
			System.out.println("gameServer is running ......");
			while(true)
			{
			
			   //System.out.println("waitting client connect ......");	
			   socket=server.accept();
			   //System.out.println("have client connect ......");
			   
			   socket.setSendBufferSize(32*1024);
			   socket.setReceiveBufferSize(32*1024);
			  
			   
			   out = socket.getOutputStream();   
			   in = socket.getInputStream();
			   
			   byte[] charBuf = new byte[4096]; 
			   
			   int size = 0;    
			   size = in.read(charBuf , 0 , 4096);
			   
			   System.out.println("server recived length="+size);
			   out.write(sendMessage);
			   out.flush();
			
			
			   out.close();
			   in.close();
			   socket.close();
			}
			
			//server.close();
		}
		catch (IOException e)
		{
			System.out.println("Error"+e);			
		}

	}
}
