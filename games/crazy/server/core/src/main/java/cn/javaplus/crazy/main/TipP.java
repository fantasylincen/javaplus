package cn.javaplus.crazy.main;

import cn.javaplus.comunication.annotations.CommunicationDto;

@CommunicationDto
public interface TipP {
	String getMessage();

	int getPlaceNumber();
}
