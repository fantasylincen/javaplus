package com.linekong.platform.protocol.erating.server;



import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;

import org.xsocket.MaxReadSizeExceededException;
import org.xsocket.connection.IConnectHandler;
import org.xsocket.connection.IDataHandler;
import org.xsocket.connection.IDisconnectHandler;
import org.xsocket.connection.IIdleTimeoutHandler;
import org.xsocket.connection.INonBlockingConnection;

import scala.collection.concurrent.Debug;
import cn.mxz.util.debuger.Debuger;

import com.linekong.platform.protocol.erating.PDUMessage;
import com.linekong.platform.protocol.erating.server.packets.BindPacket;
import com.linekong.platform.protocol.erating.server.packets.RechargePacket;
import com.linekong.platform.protocol.erating.server.util.DataTransform;

public class RechargeHandler implements  IDataHandler, IConnectHandler,
IIdleTimeoutHandler, IDisconnectHandler{

	@Override
	public boolean onDisconnect(INonBlockingConnection connection) throws IOException {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public boolean onIdleTimeout(INonBlockingConnection connection) throws IOException {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public boolean onConnect(INonBlockingConnection connection) throws IOException, BufferUnderflowException, MaxReadSizeExceededException {
		// TODO 自动生成的方法存根
		ClientInfo client = new ClientInfo();
		connection.setAttachment( client );
		System.out.println( connection.getRemoteAddress() + ":" + connection.getRemotePort() );
		
		return false;
	}

	@Override
	public boolean onData(INonBlockingConnection con) throws IOException, BufferUnderflowException, ClosedChannelException, MaxReadSizeExceededException {
	
		ClientInfo client = (ClientInfo) con.getAttachment();
		
		int totalLength;
		short packageVersion;
		short remainPackages;
		long ReceiveComand,sequenceId,gameId,gatewayId;
		ByteBuffer data;
//
		con.markReadPosition();
		int available = con.available();
        if( available > 0 ) {
//			System.out.println(con.getId() + " onData" + ",available:" + available + "byte");
            try {
            	totalLength = DataTransform.getUnsignedShort( con.readShort() );
            	packageVersion = DataTransform.getUnsigned( con.readByte());
            	remainPackages = DataTransform.getUnsigned( con.readByte());
				ReceiveComand = DataTransform.getUnsignedInt( con.readInt() );
				sequenceId = DataTransform.getUnsignedInt( con.readInt() );
				gameId = DataTransform.getUnsignedInt( con.readInt() );
				//Debuger.debug( "gameId=");
				gatewayId = DataTransform.getUnsignedInt( con.readInt() );
				
//				System.out.println( "------------------------收到的包信息                      开始-----------------------------\r\n");
//				System.out.println("totalLength=" + totalLength);
//				System.out.println("packageVersion=" + packageVersion);
//				System.out.println("remainPackages=" + remainPackages);
//				System.out.println("commandId=" + ReceiveComand);
//				System.out.println("sequenceId=" + sequenceId );
//				System.out.println("gameId=" + gameId );
//				System.out.println("gatewayId=" + gatewayId );
//				System.out.println( "-------------------------收到的包信息                         结束---------------------------\r\n");
				
				int length = totalLength - 20 - 4;				
				data = ByteBuffer.wrap(con.readBytesByLength(length) );

                short resv = con.readShort();
                int checkSum = DataTransform.getUnsignedShort( con.readShort() );
                con.removeReadMark();

            } catch( BufferUnderflowException bue ) {
                con.resetToReadMark();
                return true;
            }
//            if( !checkValid( head, foot ) ) {
//                con.close();
//                return true;
//            }

            //gameLogic.packageRun( con, packageNo, data );

//		if (0x10 == packageVersion) {
//			acheve = true;
//			continue;
//		}
			dispatch(ReceiveComand, data, con, sequenceId,gameId );
			
			return false;
	        }	
		return false;       
        
	}
	
	
	private void dispatch( long command, ByteBuffer data, INonBlockingConnection con, long sequenceId, long gameId ){
		if (PDUMessage.CMD_BIND == command){
			//System.out.println("***************Bind package head***************");
			try {
				BindPacket.run(data, con,sequenceId, gameId);
			} catch (BufferOverflowException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		
		else if (0x10003401L == command){//充值
//			System.out.println("***************充值命令***************");
			try {
				RechargePacket.run(data, con, sequenceId,gameId);
			} catch (BufferOverflowException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		
		
		
	}
}
