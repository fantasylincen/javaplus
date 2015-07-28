package cn.javaplus.crazy.main;

import java.util.List;

import cn.javaplus.crazy.Protocols.CardP;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public interface IPlayerUI {

	void showMessage(String message);

	void onSendCards(List<CardP> cards);

	Actor getBuQiang();

	Actor getQiangDiZhu();

	Actor getJiaoDiZhu();

	Actor getBuJiao();

	Actor getBuChu();

	IPockerBox getPockerBox();

	Actor getHead();
	
	TextField getMessageBox();

}
