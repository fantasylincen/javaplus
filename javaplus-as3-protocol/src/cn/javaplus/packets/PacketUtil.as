package cn.javaplus.packets
{
	import flash.net.Socket;
	import flash.utils.ByteArray;
	import flash.utils.CompressionAlgorithm;

	public class PacketUtil
	{
		/**
		 * 获取协议内容(去掉包头、类型、包尾)
		 * @param data
		 * @return 
		 */		
		public static function getMessageBody(buffer:ByteArray):ByteArray
		{
			var data:ByteArray = new ByteArray();
			buffer.position = 3;
			buffer.readBytes(data, 0, buffer.length - 4);
			data.position = 0;
			return data;
		}
		
		/**
		 * 从套接字中, 把消息读取出来
		 */
		public static function readData(socket:Socket):ByteArray
		{
			var data:ByteArray = new ByteArray();
			data.position = 0;
			socket.readBytes(data, data.bytesAvailable, socket.bytesAvailable);
			data = PacketUtil.getMessageBody(data);
			data.uncompress(CompressionAlgorithm.ZLIB);
			var packetNum:int = data.readInt();	//占位符 , 大多数项目可能无用
			return data;
		}
		
	}
}