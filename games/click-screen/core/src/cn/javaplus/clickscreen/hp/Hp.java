package cn.javaplus.clickscreen.hp;

import java.util.List;

import org.javaplus.game.common.util.Lists;

public class Hp {

	public interface HpListener {

		void onDeath(Hp hp);

	}

	List<HpListener> listeners = Lists.newArrayList();
	private double hpMax;
	private double hp;

	public Hp(double hpMax) {
		this.hpMax = hpMax;
		hp = hpMax;
	}

	public double getMax() {
		return hpMax;
	}

	public double getValue() {
		return hp;
	}

	public void reduce(double damage) {
		if (isDeath()) {
			return;
		}
		hp -= damage;
		limit();
		if (isDeath()) {
			dispatchEvent();
		}

	}

	private void dispatchEvent() {
		for (HpListener m : listeners) {
			m.onDeath(this);
		}
	}

	public boolean isDeath() {
		return hp <= 0;
	}

	public void addListener(HpListener l) {
		listeners.add(l);
	}

	public void add(double add) {
		hp += add;
		limit();
	}

	private void limit() {
		if (hp > hpMax)
			hp = hpMax;
		if (hp < 0)
			hp = 0;
	}
}
