package cn.mxz.chat;

import cn.mxz.city.City;


public interface Message {

	String getMessage();

	String getSender();

	long getTime();

	String getFormatTime();

	City getReceiver();

}
