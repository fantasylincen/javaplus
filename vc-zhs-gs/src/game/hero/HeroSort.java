package game.hero;

import game.fighter.Hero;
import game.growup.Colour;
import game.util.heroGrowup.HeroGrowupFormula;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 英雄排序
 * @author DXF
 */
public enum HeroSort {
	
	/** 按紫色排*/
	PURPLE_SORT( 1 ) {
		@Override
		public void run( List<List<Hero>> map, List<Hero> aggregate ) {
			byte[] data	= HeroGrowupFormula.actualNumber[Colour.PURPLE.toNumber()];
			for ( int i = data.length - 1; i >= 0; i-- )
				aggregate.addAll( map.get(data[i]) );
			data		= HeroGrowupFormula.actualNumber[Colour.BLUE.toNumber()];
			for ( int i = data.length - 1; i >= 0; i-- )
				aggregate.addAll( map.get(data[i]) );
			data		= HeroGrowupFormula.actualNumber[Colour.GREEN.toNumber()];
			for ( int i = data.length - 1; i >= 0; i-- )
				aggregate.addAll( map.get(data[i]) );
		}

	},
	/** 按蓝色排*/
	BLUE_SORT( 2 ) {

		@Override
		public void run(List<List<Hero>> map, List<Hero> aggregate ) {
			byte[] data	= HeroGrowupFormula.actualNumber[Colour.BLUE.toNumber()];
			for ( int i = data.length - 1; i >= 0; i-- )
				aggregate.addAll( map.get(data[i]) );
			data		= HeroGrowupFormula.actualNumber[Colour.PURPLE.toNumber()];
			for ( int i = data.length - 1; i >= 0; i-- )
				aggregate.addAll( map.get(data[i]) );
			data		= HeroGrowupFormula.actualNumber[Colour.GREEN.toNumber()];
			for ( int i = data.length - 1; i >= 0; i-- )
				aggregate.addAll( map.get(data[i]) );
		}
	},
	/** 按绿色排*/
	GREEN_SORT( 3 ) {
		@Override
		public void run(List<List<Hero>> map, List<Hero> aggregate ) {
			byte[] data	= HeroGrowupFormula.actualNumber[Colour.GREEN.toNumber()];
			for ( int i = data.length - 1; i >= 0; i-- )
				aggregate.addAll( map.get(data[i]) );
			data		= HeroGrowupFormula.actualNumber[Colour.PURPLE.toNumber()];
			for ( int i = data.length - 1; i >= 0; i-- )
				aggregate.addAll( map.get(data[i]) );
			data		= HeroGrowupFormula.actualNumber[Colour.BLUE.toNumber()];
			for ( int i = data.length - 1; i >= 0; i-- )
				aggregate.addAll( map.get(data[i]) );
		}
	};

	private final byte number;
	HeroSort( int n ) {
		number = (byte) n;
	}
	public byte toNumber() {
		return number;
	}
	private static final Map<Byte, HeroSort> numToEnum = new HashMap<Byte, HeroSort>();
	static{
		for( HeroSort a : values() ){
			numToEnum.put( a.number, a );
		}
	}
	public static HeroSort fromNumber( int n ){
		return numToEnum.get( (byte)n );
	}
	
	    
	/** 排序 
	 * @param aggregate 
	 * @param type 
	 **/
	public abstract void run( List<List<Hero>> map, List<Hero> aggregate );
}
