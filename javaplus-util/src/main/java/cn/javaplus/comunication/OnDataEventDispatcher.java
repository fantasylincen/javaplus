package cn.javaplus.comunication;

import java.util.List;

import cn.javaplus.collections.list.Lists;

public class OnDataEventDispatcher<U extends ProtocolUser> implements IOnDataEventDispatcher<U> {

	protected List<OnDataListener<U>>	ondatalisteners	= Lists.newArrayList();

	public OnDataEventDispatcher() {
		super();
	}

	@Override
	public void addOnDataListener(OnDataListener<U> l) {
		ondatalisteners.add(l);
	}
	@Override
	public void dispatchAfter(U user, Request rq) {
		for (OnDataListener<U> b : ondatalisteners) {
			b.afterOnData(new OnDataEvent<U>(user, rq));
		}
	}
	@Override
	public void dispatchBefore(U user, Request rq) {
		for (OnDataListener<U> b : ondatalisteners) {
			b.beforeOnData(new OnDataEvent<U>(user, rq));
		}
	}

}