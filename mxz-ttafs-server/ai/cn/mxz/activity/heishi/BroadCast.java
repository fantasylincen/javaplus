package cn.mxz.activity.heishi;

import java.util.List;

import com.google.common.collect.Lists;

public class BroadCast<T> {
	private final int  maxSize;
	
	public BroadCast(int maxSize) {
		super();
		this.maxSize = maxSize;
	}

	private List<T> messages = Lists.newArrayList();
	
	public void add( T t){
		messages.add( 0, t );
		if( messages.size() > maxSize ){
			messages.remove( messages.size() - 1 );
		}
		
		
	}
	
	public List<T> getAllMessage(){
		return messages;
	}
	
	public void setMessage( List<T> list){
		this.messages = list;
	}
	
	private void print(){
		for( T t : messages ){
			System.out.println( t );
		}
	}
}
