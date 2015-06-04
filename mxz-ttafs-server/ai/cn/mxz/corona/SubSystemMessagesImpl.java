package cn.mxz.corona;

import java.util.ArrayList;
import java.util.List;

/**
 * 管理功能子系统的广播消息
 * @author Administrator
 *
 * @param <T>
 */
class SubSystemMessagesImpl<T> implements ISubSystemMessages<T> {


	private final int	maxMessageCount;
	private List<T> messages = new ArrayList<T>();

	SubSystemMessagesImpl(int maxMessageCount) {
		super();
		this.maxMessageCount = maxMessageCount;
	}

	/* (non-Javadoc)
	 * @see cn.mxz.corona.ISubSystemMessageManager#add(T)
	 */
	@Override
	public List<T> add( T m ){
		messages.add( 0,  m );
		if( messages.size() > maxMessageCount ){
			messages.remove( messages.size() - 1 );
//			messages.subList( MAX_NUMBER, messages.size()  ).clear();
		}
		return messages;
	}


	public static void main(String[] args) {
		SubSystemMessagesImpl<String> cmm = new SubSystemMessagesImpl<String>(10);
		long begin = System.nanoTime();
		for( int i = 0; i < 5; i++ ){
			cmm.add( "" + i );
		}

		for( String s : cmm.messages )
		System.out.println( s );

		System.out.println( (System.nanoTime() - begin) / 1000000000f );
	}

	@Override
	public List<T> get() {
		return messages;
	}

}
