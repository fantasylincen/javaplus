package cn.javaplus.crazy.main;

import cn.javaplus.comunication.annotations.CommunicationDto;

@CommunicationDto
public interface PlayerP {
	String getNick();

	boolean isDz();

	int getRemainSec();

	int getRamainCards();

	/**
	 * 座位号
	 * 
	 * @return
	 */
	int getPlaceNumber();
}
