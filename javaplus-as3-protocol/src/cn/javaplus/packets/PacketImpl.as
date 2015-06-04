package cn.javaplus.packets
{
	import flash.net.Socket;
	import flash.utils.ByteArray;
	
	public class PacketImpl implements Packet
	{
		private var socket:Socket;
		private var data:ByteArray;
		
		public function PacketImpl(socket:Socket)
		{
			this.socket = socket;
			data = new ByteArray();
		}
		
		
		public function writeUTF(value:String):void
		{
			data.writeUTF(value);
		}
		
		public function writeBoolean(b:Boolean):void
		{
			data.writeBoolean(b);
		}
		
		
		public function writeInt(value:int):void
		{
			data.writeInt(value);
		}
		
		
		public function send():void
		{
			if (socket != null && socket.connected)
			{
				socket.writeByte(116);
				socket.writeShort(data.length);
				socket.writeBytes(data);
				socket.writeByte(127);
				
				socket.flush();
			}
		}
	}
}