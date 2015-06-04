package cn.mxz.equipment;
//package cn.mxz.prop.equipment;
//
//import cn.javaplus.common.util.Util;
//import cn.mxz.Attribute;
//import cn.mxz.formation.AlternateFormation;
//import cn.mxz.formation.PlayerCamp;
//import cn.mxz.pvp.SuperCamp;
//import cn.mxz.user.City;
//import cn.mxz.user.team.Formation;
//import cn.mxz.user.team.god.Hero;
//import cn.mxz.util.debuger.Debuger;
//
//public class JiaFormation implements Formation {
//
//	private City		city;
//	private Formation	formation;
//	private double	minCapacityX;
//	private double	maxCapacityX;
//
//	public JiaFormation(City city, double minCapacityX, double maxCapacityX) {
//		this.city = city;
//		this.minCapacityX = minCapacityX;
//		this.maxCapacityX = maxCapacityX;
//		formation = city.getFormation();
//	}
//
//	public int getShenJia() {
//		return formation.getShenJia();
//	}
//
//	public PlayerCamp getSelected() {
//		double attributeX = Util.Random.get(minCapacityX, maxCapacityX);
//		PlayerCamp selected = formation.getSelected();
//		return new SuperCamp(new RandomPositionCamp(selected), attributeX);
//	}
//
//	public Attribute getAddition(Hero hero) {
//		return formation.getAddition(hero);
//	}
//
//	public int getPosition(Hero f) {
//		return formation.getPosition(f);
//	}
//
//	public AlternateFormation getAlternate() {
//		return formation.getAlternate();
//	}
//
//	public void addNewTactical(Integer id) {
//		formation.addNewTactical(id);
//	}
//
//	public int getTacticalsCount(int a0) {
//		return formation.getTacticalsCount(a0);
//	}
//
//	public int getCurrentTacticalId() {
//		return formation.getCurrentTacticalId();
//	}
//
//	public int getFormationPart(Hero hero) {
//		return formation.getFormationPart(hero);
//	}
//
//	public int getMaxCount() {
//		return formation.getMaxCount();
//	}
//
//}
