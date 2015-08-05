package cn.javaplus.crazy.main;

import java.util.List;

import cn.javaplus.comunication.annotations.CommunicationDto;

@CommunicationDto
interface CardsP {
	List<Card> getCards();

	int getSize();
}
