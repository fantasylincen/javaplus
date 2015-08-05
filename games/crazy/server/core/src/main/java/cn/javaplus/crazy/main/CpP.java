package cn.javaplus.crazy.main;

import java.util.List;

import cn.javaplus.comunication.annotations.CommunicationDto;

@CommunicationDto
public interface CpP {
	List<CardP> getCards();

	int getPlaceNumber();

	int getRemainCards();
}
