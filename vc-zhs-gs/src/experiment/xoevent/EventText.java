package experiment.xoevent;

public class EventText {

	public static void main( String[] args ){
		
		
		XOEventSource xoe = new XOEventSource();
		
		
		xoe.addListener( new mylist( ) );
		
		xoe.fireEvent();
		
	}
	
	
	private static class mylist implements XOListener{
		@Override
		public void eventHandle( XOEvent e ) {
			System.out.println( "处理了事件" );
		}
	}
	
	
	
}
