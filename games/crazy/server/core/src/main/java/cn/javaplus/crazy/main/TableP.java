package cn.javaplus.crazy.main;

import cn.javaplus.comunication.annotations.CommunicationDto;

@CommunicationDto
public interface TableP {
	OtherP getLeft();

	MineP getMine();

	OtherP getRight();
}
