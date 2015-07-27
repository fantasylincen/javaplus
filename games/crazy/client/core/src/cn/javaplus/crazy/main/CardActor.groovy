package cn.javaplus.crazy.main;

import cn.javaplus.crazy.D;
import cn.javaplus.crazy.Protocols.CardP;
import cn.javaplus.crazy.pocker.Card;
import cn.javaplus.crazy.pocker.Pocker;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class CardActor extends Group {

	private int id;

	boolean isSelect;
	private Image value;
	private Image bigColor;
	private Image smallColor;
	private Image background;

	public CardActor(CardP p, CardUI ui) {
		addListener(new SelectCardListener());
		id = p.getId();
		Card card = Pocker.get(id);
		int v = card.getValue();

		int c = card.getColor();

		bigColor = ui.getBigColor(c);
		smallColor = ui.getSmallColor(c);
		value = ui.getValue(card.isBlack(), v);
		background = ui.getBackGround(v);

		addActor(background);

		if (bigColor != null)
			addActor(bigColor);
		if (smallColor != null)
			addActor(smallColor);
		if (value != null)
			addActor(value);
	}

	public int getId() {
		return id;
	}

	public boolean isSelect() {
		return isSelect;
	}

	public void changeSelected() {
		isSelect = !isSelect;
		if (isSelect) {
			setPosition(getX(), D.CARD_UP_PIXEL);
		} else {
			setPosition(getX(), 0);
		}
	}

	public int compareTo(CardActor o) {
		return getId() - o.getId();
	}

}
