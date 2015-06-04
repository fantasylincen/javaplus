package game.battle.auto;

/**
 * 包含一次攻击的各种信息
 * @author liukun
 * 2012-12-21 下午05:23:52
 */
public class AttackInfo {
	
	/**
	 * 是否命中
	 */
	private static final int 			HIT = 2*2*2*2*2;//第五位
	
	/**
	 * 是否格挡，当前版本把格挡反击合二为一
	 */
	private static final int 			BLOCK = 2*2*2;//第三位
	
	/**
	 * 是否反击
	private static final int 			COUNTER_ATTACK = 2*2*2*2;
	 */
	
	/**
	 * 暴击倍数
	 */
	private static final int 			CRIT = 7;
	
	/**
	 * 获取攻击状态值, 该状态值包含了格挡信息, 命中信息, 暴击倍数
	 * 
	 * 0b00dcbaaa
	 * 
	 * aaa	三位来代表暴击倍数，也就是说最大为7倍暴击<br>
	 * b	是否格挡<br>
	 * c	是否反击<br>
	 * d	是否命中<br>
	 */
	private byte data = 0;
	
	/**
	 * 本次攻击的伤害值 也有可能不是伤害值  可能是加血值 那么就是负数 也可能是其他sp的值 灵活运用
	 */
	private int									damage;
	
	/**
	 * 用于解析战况的时候使用
	 * @param data
	 */
	public AttackInfo( byte data ) {
		super();
		this.data = data;
	}
	
	public AttackInfo(){}

	/**
	 * 获取网络传送用的raw数据的拷贝，就是一个byte
	 * @return
	 */
	byte getRawData(){
		return data;
	}
	
	/**
	 * @see #HIT
	 * @return
	 */
	public boolean isHit(){
		return getBitValue( HIT );
	}
	
	/**
	 * @see #HIT
	 */
	public void setHit( boolean isHit ){
		setBitValue( HIT, isHit );
		
	}
	
	/**
	 * @see #BLOCK
	 * @return
	 */
	public boolean isBlock(){
		return getBitValue( BLOCK );
	}
	
	public void setBlock( boolean isBlock ){
		setBitValue( BLOCK, isBlock );
	}
	
	/**
	 * 根据value的值把某一位置0或者置1
	 * @param flag		如果要对第五位进行处理，则应该传入2的5次方，位数要从左边并且从0开始数
	 * @param value		
	 * 					true	置为1
	 * 					false	置为0
	 */
	private void setBitValue( int flag, boolean value ){
		if( value ){
			data |= flag;
		}
		else{
			data &= ~flag;
		}
	}
	private boolean getBitValue( int flag ){
		return (data & flag) != 0;
	}
	
	/**
	 * 获取暴击倍数<br>
	 * 1:	无暴击
	 * @return
	 */
	public byte getCrit(){
		return (byte) (data & CRIT );
	}
	
	/**
	 * 最大倍数不大于7倍
	 * @param crit
	 */
	public void setCrit( int crit ){
		if( crit > 7 || crit < 0 ){
			throw new IllegalArgumentException( "无效的暴击值:" + crit );
		}
		data &= ~CRIT;
		data |= crit; 
	}
	
	
	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	@Override
	public String toString() {
		return 	"是否命中" + isHit() +
				" 是否格挡" + isBlock() +
				" 暴击倍数" + getCrit() +
				" 伤害值"	+ getDamage();	
	}

	
	public static void main(String[] args) {
		byte data = 0;
//		System.out.println( Integer.toBinaryString(data) );
		data |= ((int)Math.pow( 2, 7 ));
		System.out.println( Integer.toBinaryString(data) );
//		System.out.println( data & BLOCK );
//		data &= ~ (int)Math.pow( 2, 7 );
//		System.out.println( Integer.toBinaryString(data) );
//		System.out.println(  );
	}
}
