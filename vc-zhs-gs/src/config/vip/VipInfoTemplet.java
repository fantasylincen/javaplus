package config.vip;

import org.jdom2.Element;

import lombok.Data;

/**
 * VIP信息
 * @author DXF
 */
public class VipInfoTemplet {

	private final byte			id;
	
	public byte getId() {
		return id;
	}

	public int getCondition() {
		return condition;
	}

	public short getPhysicalLimit() {
		return physicalLimit;
	}

	public int getTodayCash() {
		return todayCash;
	}

	public int getTodayGold() {
		return todayGold;
	}

	public byte getBuyStrCount() {
		return buyStrCount;
	}

	public byte getBuyPvpCount() {
		return buyPvpCount;
	}

	public float getEquDiscount() {
		return equDiscount;
	}

	private final int			condition;
	
	private final short			physicalLimit;
	
	private final int			todayCash;
	
	private final int			todayGold;
	
	private final byte			buyStrCount;
	
	private final byte			buyPvpCount;
	
	private final float			equDiscount;
	
	public VipInfoTemplet( Element element ){
		
		id				= Byte.parseByte( element.getChildText( "id" ) );
		condition		= Integer.parseInt( element.getChildText( "condition" ) );
		physicalLimit	= Short.parseShort( element.getChildText( "physicalLimit" ) );
		todayCash		= Integer.parseInt( element.getChildText( "todayCash" ) );
		todayGold		= Integer.parseInt( element.getChildText( "todayGold" ) );
		buyStrCount		= Byte.parseByte( element.getChildText( "buyStrCount" ) );
		buyPvpCount		= Byte.parseByte( element.getChildText( "buyPvpCount" ) );
		equDiscount		= Float.parseFloat( element.getChildText( "EquDiscount" ) );
	}
	
}
