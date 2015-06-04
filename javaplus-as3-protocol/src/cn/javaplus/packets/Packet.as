package cn.javaplus.packets
{
	public interface Packet
	{
		
		
		function writeUTF(uname:String):void;
		
		
		function writeInt(b:int):void;	
		
		function writeBoolean(b:Boolean):void;	
		
		
		/**
		 * 发送这个数据包
		 */
		function send():void;

	}
}