package cn.javaplus.crazy.main;

import java.util.List;

import cn.javaplus.comunication.annotations.CommunicationDto;

@CommunicationDto
public interface MineP {

	PlayerP getPlayer();

	List<CardP> getCards();
}
