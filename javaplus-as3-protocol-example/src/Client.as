package
{
	import cn.javaplus.packets.PacketUtil;
	
	import flash.display.Sprite;
	import flash.events.Event;
	import flash.events.ProgressEvent;
	import flash.net.Socket;
	import flash.utils.ByteArray;
	
	public class Client extends Sprite
	{
		private var socket:Socket;
		
		private var handler:ProtocolHandler;
		
		public function Client() {
			
			trace("Main.Main()");
			
			handler = new ProtocolHandler();
			socket = new Socket();
			socket.addEventListener(ProgressEvent.SOCKET_DATA, onData);
			Server.socket = socket;
			socket.connect("localhost",16464);
			
			socket.addEventListener(Event.CONNECT, onConnect);
			
		}
		
		protected function onConnect(event:Event):void
		{
		}		
		
		private function onReceiveData(e:HelloWorldProtocolGetStudentEvent):void
		{
		}		
		
		protected function onData(event:ProgressEvent):void
		{
			var data:ByteArray = PacketUtil.readData(socket);
			handler.onData(data);
		}
	}
}