package cn.vgame.plantform;


public interface Plantform {

	TokenChecker getChecker();

	/**
	 * 充值配置表文件名
	 */
	String getRechargeSheetName();
	
	String getName();

}