package config.evolution;

/**
 * 材料基础类
 * @author DXF
 */
public class ConsumeBase {

	// 颜色
	private byte  		colour;
	
	// 等级
	private byte 		level;
	
	// 对应个数
	private byte		num;
	
	public ConsumeBase( byte colour,byte level, byte num ){
		this.colour = colour;
		this.level 	= level;
		this.num	= num;
	}
	
	public ConsumeBase( ConsumeBase consume ){
		this.colour = consume.colour;
		this.level 	= consume.level;
		this.num	= consume.num;
	}
	
	public byte colour(){
		return this.colour;
	}
	
	public byte level(){
		return this.level;
	}
	
	public byte num(){
		return this.num;
}

	public byte cutNum() {
		return --this.num;
	}
}
