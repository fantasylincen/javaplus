package game.equipment;


import java.util.HashMap;
import java.util.Map;

/**
 * 装备颜色
 * @author DXF
 */
public enum EColour {
	
	/** 白色 */
	WHITE(0) {
		@Override
		public int getSellPrice() {
			return 2000;
		}

		@Override
		public String toName() {
			return "白";
		}
	},
	
	/** 绿色 */
	GREEN(1) {
		@Override
		public int getSellPrice() {
			return 4000;
		}

		@Override
		public String toName() {
			return "绿";
		}
	},
	
	/** 蓝色 */
	BLUE(2) {
		@Override
		public int getSellPrice() {
			return 8000;
		}

		@Override
		public String toName() {
			return "蓝";
		}
	},
	
	/** 紫色 */
	PURPLE(3) {
		@Override
		public int getSellPrice() {
			return 16000;
		}

		@Override
		public String toName() {
			return "紫";
		}
	},
	
	/** 橙色 */
	ORANGE(4) {
		@Override
		public int getSellPrice() {
			return 32000;
		}

		@Override
		public String toName() {
			return "橙";
		}
	};	
	
	private final byte number;
	EColour( int n ) {
		number = (byte) n;
	}
	public byte toNumber() {
		return number;
	}
	private static final Map<Byte, EColour> numToEnum = new HashMap<Byte, EColour>();
	static{
		for( EColour a : values() ){
			numToEnum.put( a.number, a );
		}
	}
	public static EColour fromNumber( int n ){
		return numToEnum.get( (byte)n );
	}
	
	/**
	 * 获得出售价格
	 * @return
	 */
	public abstract int getSellPrice();
	public abstract String toName();
	
}
