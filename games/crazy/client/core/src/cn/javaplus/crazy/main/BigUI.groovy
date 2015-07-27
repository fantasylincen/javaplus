package cn.javaplus.crazy.main;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

import cn.javaplus.crazy.R;
import cn.javaplus.crazy.R.Altas.GameUIAltas;
import cn.javaplus.crazy.R.Uis.GameUI.Panel.Pockerui;

public class BigUI implements CardUI {

//	private Pockerui ui;

	public BigUI(Pockerui ui) {
//		this.ui = ui;
	}

	@Override
	public Image getSmallColor(int c) {

		GameUIAltas ui = R.Altas.getGameUI();
		if (c == 0) {
			return new Image(ui.getSpades());
		} else if (c == 1) {
			return new Image(ui.getHearts());
		} else if (c == 2) {
			return new Image(ui.getClubs().get(0));
		} else {
			return new Image(ui.getDiamonds().get(0));
		}
	}

	@Override
	public Image getBigColor(int c) {
		GameUIAltas ui = R.Altas.getGameUI();
		if (c == 0) {
			return new Image(ui.getSspades());
		} else if (c == 1) {
			return new Image(ui.getShearts());
		} else if (c == 2) {
			return new Image(ui.getSclubs().get(0));
		} else {
			return new Image(ui.getSdiamonds().get(0));
		}
	}

	@Override
	public Image getValue(boolean black, int v) {
		return new Image(R.Altas.getGameUI().getClubs().get(1));
		// if (black) {
		// // 2 -- 5
		//
		//
		// if(v == 5 ) {
		//
		// }
		// if (v == 3)
		// ar = ui.getBlack3();
		// else if (v == 4)
		// ar = R.Altas.ddz.getBlack4();
		// else if (v == 5)
		// ar = R.Altas.ddz.getBlack5();
		// else if (v == 6)
		// ar = R.Altas.ddz.getBlack6();
		// else if (v == 7)
		// ar = R.Altas.ddz.getBlack7();
		// else if (v == 8)
		// ar = R.Altas.ddz.getBlack8();
		// else if (v == 9)
		// ar = R.Altas.ddz.getBlack9();
		// else if (v == 10)
		// ar = R.Altas.ddz.getBlack10();
		// else if (v == 11)
		// ar = R.Altas.ddz.getBlackj();
		// else if (v == 12)
		// ar = R.Altas.ddz.getBlackq();
		// else if (v == 13)
		// ar = R.Altas.ddz.getBlackk();
		// else if (v == 14)
		// ar = R.Altas.ddz.getBlacka();
		// else if (v == 20)
		// ar = R.Altas.ddz.getBlack2();
		// } else {
		// if (v == 3)
		// ar = R.Altas.ddz.getRed3();
		// else if (v == 4)
		// ar = R.Altas.ddz.getRed4();
		// else if (v == 5)
		// ar = R.Altas.ddz.getRed5();
		// else if (v == 6)
		// ar = R.Altas.ddz.getRed6();
		// else if (v == 7)
		// ar = R.Altas.ddz.getRed7();
		// else if (v == 8)
		// ar = R.Altas.ddz.getRed8();
		// else if (v == 9)
		// ar = R.Altas.ddz.getRed9();
		// else if (v == 10)
		// ar = R.Altas.ddz.getRed10();
		// else if (v == 11)
		// ar = R.Altas.ddz.getRedj();
		// else if (v == 12)
		// ar = R.Altas.ddz.getRedq();
		// else if (v == 13)
		// ar = R.Altas.ddz.getRedk();
		// else if (v == 14)
		// ar = R.Altas.ddz.getReda();
		// else if (v == 20)
		// ar = R.Altas.ddz.getRed2();
		// }

	}

	@Override
	public Image getBackGround(int v) {
		GameUIAltas ui = R.Altas.getGameUI();
		if (v == 30) {
			return new Image(ui.getJokerSmall());
		}
		if (v == 31) {
			return new Image(ui.getJokerBig());
		}
		return new Image(ui.getBackground());
	}

}
