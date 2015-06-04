package cn.mxz.listeners.fighter;

import cn.mxz.events.AttributeChangeEvent;
import cn.mxz.events.Listener;
import cn.mxz.messagesender.MessageFactory;

public class UpdateClientCapacityChanged implements Listener<AttributeChangeEvent>{

	@Override
	public void onEvent(AttributeChangeEvent e) {
		int oldShenJia = e.getOldShenJia();
		int newShenJia = e.getNewShenJia();
		if(newShenJia != oldShenJia) {
			MessageFactory.getFighter().capacityChanged(e.getCity().getSocket(), oldShenJia, newShenJia);
		}
	}

}
