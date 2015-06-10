package game.award.system;

/**
 * 奖励条件
 * @author DXF
 *
 */
public class AwardCondition {

	public AwardCondition(){};
	
	/** 条件值 */
	private int 				_nValue		= 0;

	public void setValue( int value ){
		this._nValue = value;
	}
	public int getValue(){
		return this._nValue;
	}

	public void addValue(){
		++this._nValue;
	}
}
