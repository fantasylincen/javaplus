package cn.mxz.equipment;

import cn.mxz.Attribute;
import cn.mxz.city.City;
import cn.mxz.user.team.god.Hero;

public class MessageAttributeChange {

	private Attribute old;
	private Attribute newA;
	private City city;

	public MessageAttributeChange(City city) {
		this.city = city;
	}

	public void sendMessage() {
//		for (AdditionType a : AdditionType.values()) {
//			if(a == AdditionType.CRIT_ADDITION || a == AdditionType.MAGIC) { //会心和暴击加成不显示
//				continue;
//			}
//
//			int old = a.get(this.old);
//			int n = a.get(newA);
//
//			int d = n - old;
//			if (d != 0) {
//				
//
//				String t = d > 0 ? "+" + d : "" + d;
//
//				String formatStr = a.getText() + " " + t;
//				city.getMessageSender().send(S.S0, formatStr);
//			}
//		}
	}

	public void saveOld(Hero h) {
		old = h.getAttribute();
	}

	public void saveNew(Hero h) {
		newA = h.getAttribute();
	}

}
