package cn.javaplus.crazy.main;

import cn.javaplus.comunication.annotations.CommunicationDto;

@CommunicationDto
public interface Card {

	int getId();

	int getColor();
}
