package game.award;

import game.ITransformStream;

import java.nio.ByteBuffer;

public class AwardInfo implements ITransformStream {

	// 奖励类型
	private AwardType 			award;
	// 物品ID
	private int					propId;
	// 物品个数
	private int					number;
	// 随机 如果有 比如掉落
	private float[]				rand		= null;
	// 其他参数 如果有 比如英雄
	private int[]				arguments	= null;
	
	/**
	 * 道具奖励
	 * @param award
	 * @param propId
	 * @param number
	 */
	public AwardInfo( AwardType award, int propId, int number, float[] rand ) {
		super();
		this.award 	= award;
		this.propId = propId;
		this.number = number;
		this.rand 	= rand;
	}
	
	/**
	 * 不包括道具的奖励
	 * @param award
	 * @param propId 物品ID
	 * @param number 数量
	 */
	public AwardInfo(AwardType award, int propId,  int number) {
		super();
		this.award 	= award;
		this.propId = propId;
		this.number = number;
	}
	
	/**
	 * 不包括道具的奖励
	 * @param award
	 * @param number
	 */
	public AwardInfo(AwardType award, int number) {
		super();
		this.award 	= award;
		this.number = number;
	}

	/**
	 * 拷贝一个
	 * @param a
	 */
	public AwardInfo( AwardInfo a ) {
		award 		= a.award;
		propId		= a.propId;
		number		= a.number;
		rand		= a.rand;
		arguments	= a.arguments;
		
	}

	public AwardInfo(AwardType hero, int i, int j, int k, int l, int m) {
		award 		= hero;
		propId		= i;
		number		= j;
		arguments	= new int[]{ k, l, m };
	}

	public AwardType getAward() {
		return award;
	}
	public void setAward(AwardType award) {
		this.award = award;
	}

    public int getPropId() {
		return propId;
	}
	public void setPropId(int propId) {
		this.propId = propId;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	public void setRand( float[] rand ) {
		this.rand = rand;
	}
	public float[] getRand(){
		return this.rand;
	}
	
	public int[] getArguments(){
		return arguments;
	}
	public void setArguments( int[] arguments ){
		this.arguments = arguments;
	}
	
	@Override
	public void buildTransformStream(ByteBuffer buf) {
		buf.put( award.toNumber() );
		buf.putInt( propId );
		buf.putInt( number );
	}

	public String toString(){
		return "[" + award + "," + propId + "," + number + "]";
	}
}
