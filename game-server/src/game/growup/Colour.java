package game.growup;

import game.fighter.Hero;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 英雄品质
 * @author DXF
 *
 */
public enum Colour {

	/** 绿座 */
	GREEN(0) {

		// 进化需要个数
		private final byte devourNum = 5;
				
		/*
		 * 进化要求 ：</br>
		 * 任意5个绿座英雄
		 */
		public Colour Evolution(Hero curDevourHero, List<Hero> beDevourHero) {
			
			if( beDevourHero.size() != devourNum )
				return null;
			
			
			return BLUE;
		}

		@Override
		public int getBinary() {
			return 2*2*2;
		}
	},
	
	/** 蓝座 */
	BLUE(1) {
		
		// 进化需要个数
		private final byte devourNum = 5;
		
		/*
		 * 进化要求 ：</br>
		 * 任意5个蓝座英雄
		 */
		public Colour Evolution(Hero curDevourHero, List<Hero> beDevourHero) {
			
			if( beDevourHero.size() != devourNum )
				return null;
			
			
			return PURPLE;
		}

		@Override
		public int getBinary() {
			return 2*2*2*2;
		}
	},
	
	/** 紫座 */
	PURPLE(2) {
		
		// 进化需要个数
		private final byte devourNum = 5;
		
		/*
		 * 进化要求 ：</br>
		 * 相同的5个紫座英雄( 这里的相同指表格ID相同 )
		 */
		public Colour Evolution(Hero curDevourHero,List<Hero> beDevourHero) {
			
			if( beDevourHero.size() != devourNum )
				return null;
			
			
			return GOLDEN;
		}

		@Override
		public int getBinary() {
			return 2*2*2*2*2;
		}
	},
	
	/** 橙座 */
	GOLDEN(3) {
		@Override
		public Colour Evolution(Hero curDevourHero, List<Hero> beDevourHero) {
			return null;
		}

		@Override
		public int getBinary() {
			return 2*2*2*2*2*2;
		}
	};
	
	
	private final byte number;
	Colour( int n ) {
		number = (byte) n;
	}
	public byte toNumber() {
		return number;
	}
	private static final Map<Byte, Colour> numToEnum = new HashMap<Byte, Colour>();
	static{
		for( Colour a : values() ){
			numToEnum.put( a.number, a );
		}
	}
	public static Colour fromNumber( int n ){
		return numToEnum.get( (byte)n );
	}
	
	
	/**
	 * 开始进化（不用考虑beDevourHero是否为null 外层已经做好处理）
	 * @param curDevourHero 
	 * @param beDevourHero
	 * @return
	 */
	public abstract Colour Evolution(Hero curDevourHero, List<Hero> beDevourHero);
	
	/**
	 * 获取二进制形式
	 * @return
	 */
	public abstract int getBinary();
	
}
