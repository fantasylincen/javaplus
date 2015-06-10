package game.growup;

import java.nio.ByteBuffer;

import game.util.heroGrowup.HeroGrowupFormula;

/**
 * 品质管理
 * @author DXF
 *
 */
public class Quality {

	/** 颜色 */
//	private static final int 			COLOUR 	= 248;//前5位
	
	/** 品质等级 */
	private static final int 			LEVEL 	= 7;//后3位
	
	/** 00001111	前4位表示 颜色 后4位表示等级 */
//	private byte 			data 			= 0;
	
	// 当前颜色
	private Colour 			curColour;
	
	// 当前品质等级(0表示没有等级     1,表示+  2，表示+1)
	private byte			level			= 0;
	
	// 最大颜色
	private Colour			maxColour;
	
	
	public Quality( Colour colour, Colour maxColour, byte level ){
		this.curColour 	= colour;
		this.maxColour	= maxColour;
		this.level		= level;
		if( colour.toNumber() > maxColour.toNumber() )
			colour		= maxColour;
	}
	
	public Quality( Quality quality ){
		this.curColour 	= quality.curColour;
		this.maxColour 	= quality.maxColour;
		this.level		= quality.level;
	}
	
	public Colour getMaxColour(){
		return this.maxColour;
	}
	
	public Colour getColour(){
		return this.curColour;
	}
	public void setColour( Colour colour ){
		this.curColour = colour;
	}
	
	public byte getLevel(){
		return this.level;
	}
	public void setLevel( byte level ){
		this.level = level;
	}

	public byte toNumber() {
		return curColour.toNumber();
	}

	/** 是否最高 */
	public boolean isMax() {
		return  (curColour == maxColour) && ((level + 1) == HeroGrowupFormula.actualNumber[curColour.toNumber()].length);
	}

	/** 是否精英 */
	public boolean isElite() {
		return level > 0;
	}

	/** 数据库 需要 转成字符串 */
	public String toContent() {
		return curColour + "," + level;
	}
	/** 数据库 需要 转成字符串 */
	public String toContentNew() {
		return curColour.toNumber() + "," + level;
	}

	/**
	 * 将品质信息转成Byte数据
	 * @return
	 */
	public void toByte( ByteBuffer response ) {
//		data = 0;
//		data |= curColour.getBinary(); 
//		data &= ~LEVEL;  // 等级
//		data |= level; 
		
		response.put( curColour.toNumber() );
		response.put( level );
//		return data;
	}

	public String toName() {
		return (level > 0 ? "+" : "") + (level <= 1 ? "" : level - 1);
	}
	
	public static void main( String[] args ){
		
		byte data = 0;
		
		data |= Colour.GOLDEN.getBinary();
		data &= ~LEVEL;
		data |= 7;
		System.out.println( Integer.toBinaryString(data) );
		System.out.println( "品质=" + ((data & 248))  + ", 等级=" + (data & LEVEL) );
//		System.out.println( "品质=" + (data & COLOUR) );
	}

	
}
