package cn.javaplus.comunication;

public interface IOnDataEventDispatcher<U extends ProtocolUser> {

	public abstract void addOnDataListener(OnDataListener<U> l);

	void dispatchBefore(U user, Request rq);

	void dispatchAfter(U user, Request rq);

}