package lincen.javame.game.numrubikcube.canvas;

import java.util.List;

import org.javaplus.game.common.util.Lists;

import lincen.javame.util.Pen;

public abstract class KarelCanvas_Old {

	private List<PaintToKarelCanvasAble> components = Lists.newArrayList();

	public void paint(Pen g) {
		paintBackground(g);
		paintAllComponents(g);
	}

	public abstract void paintBackground(Pen g);

	private void paintAllComponents(Pen g) {
		for (PaintToKarelCanvasAble p : components) {
			p.paint(g);
		}
	}

	void add(PaintToKarelCanvasAble comp) {
		this.components.add(comp);
	}
}
