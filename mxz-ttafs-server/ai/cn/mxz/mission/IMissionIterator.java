package cn.mxz.mission;

import java.util.Iterator;

public interface IMissionIterator<E> extends Iterator<E>{
	
	E getCurrent();
	void setCurrent( E e);

}
