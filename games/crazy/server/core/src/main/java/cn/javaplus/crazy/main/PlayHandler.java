package cn.javaplus.crazy.main;

import cn.javaplus.comunication.annotations.Communication;
import cn.javaplus.comunication.annotations.ServerToClientOnly;

@Communication
interface PlayHandler {

	@ServerToClientOnly
	void gameStart(TableP room);

	@ServerToClientOnly
	void enterOldTable(TableP room);

	@ServerToClientOnly
	void startPlayPocker(StartPlayP p);

	@ServerToClientOnly
	void tip(TipP p);

	@ServerToClientOnly
	void chuPai(CpP p);

	@ServerToClientOnly
	void clearAllTip(EmptyP p);

	void jdz(boolean isJ);

	void qdz(boolean isQ);

	void cp(String cardIds);

	void bcp();

	void restartGame();
}
