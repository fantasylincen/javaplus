package game.battle.auto;

import game.battle.formation.IFormation;
import game.fighter.FighterBase;
import game.log.Logs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import config.skill.accord.ChooseFighters;

import util.RandomUtil;

import static java.text.MessageFormat.*;


/**
 * 手机游戏阵营，前排3个，后排3个，攻击规则：
 *
 *
 * @author DXF
 */
//@SuppressWarnings("JavaDoc")
public class Formation implements IFormation{

    /**
     * 阵型的总人数
     */
    public static final byte				TOTAL_COUNT 	= 6;
    
    // 一排最大人数
    private static final int 				COUNT_PER_ROW 	= 3;

    // NPC列表
    private List<FighterBase> 				fighters;

    // true攻方    false守方
    private boolean 						isBottom;
    
    
    /** 按照位置从低到高进行排序，否则计算被攻击战士的时候可能出错 */
    public static final Comparator<FighterBase> posComparator = new Comparator<FighterBase>(){
        @Override
        public int compare( FighterBase f1, FighterBase f2 ) {
            return f1.getPosition() - f2.getPosition();
        }
    };

    /** 按照HP从低到高进行排序 */
    public static final Comparator<FighterBase> hpComparator = new Comparator<FighterBase>(){
        @Override
        public int compare( FighterBase f1, FighterBase f2 ) {
        	
        	int f1hp	= (int) (((float)f1.getHp() / (float)f1.getHpMax()) * 100);
        	int f2hp	= (int) (((float)f2.getHp() / (float)f2.getHpMax()) * 100);
        	
            return f1hp - f2hp;
        }
    };

    /**
     * 复制一份实例用于战斗，通常用于复制主线通关的mission的阵型，位于阵型的左边
     * @param formation
     * @return
     */
    public Formation( IFormation formation ){
    	
        List<FighterBase> clonesList 	= new ArrayList<FighterBase>();
        
        for( FighterBase f : formation.getAllFighters() ){
        	
            FighterBase clone = new FighterBase( f );
            clonesList.add( clone );
        }

        fighters = clonesList;
    }

    /**
     * 试炼
     * @param fightersList
     */
    public Formation( List<FighterBase> fightersList ){
    	if( fightersList == null || fightersList.size() == 0 )
            throw new IllegalArgumentException(format("{0}战士列表为空或者数量为0", isBottom ? "攻方" : "守方"));
    	fighters	 	= fightersList;
    	isBottom		= false;
    	// 这里需要重新排序
//        Collections.sort( fighters, posComparator );
    }
    
    /**
     * @param fightersList
     * @param isBottom
     * @param pet
     *
     *	如有必要，请在调用处对 fightersList 进行克隆处理
     */
    public Formation( List<FighterBase> fightersList, boolean isBottom ) throws IllegalArgumentException {
        if( fightersList == null || fightersList.size() == 0 )
            throw new IllegalArgumentException(format("{0}战士列表为空或者数量为0", isBottom ? "攻方" : "守方"));

        this.isBottom 	= isBottom;
        fighters	 	= fightersList;
        
        if ( !isBottom ) {
            for( FighterBase bf : fighters ){
                formatForDefender( bf );
            }
        }
        
        // 这里需要重新排序
        Collections.sort( fighters, posComparator );
    }
    
    /**
     * 对防守方进行一系列改造，包括<br>
     * 所有位置+9(九宫格战斗)<br>
     * 防守方镜面翻转<br>
     * 重新按照位置信息排序<br>
     * 这个代码就应该在这里执行，而不应该放到BaseBattle中，因为这个是和阵型密切相关的
     */
    private void formatForDefender( FighterBase fighter ){

        byte position 	= fighter.getPosition();
        position 		+= TOTAL_COUNT;

        fighter.setPosition( position );//上下水平翻转
        fighter.setIsBottom( false );
    }
    @Override
    /**
     * 是否所有的战士都已经死亡
     * @return
     */
    public boolean isAllDie(){
        for( FighterBase f : fighters ){
            if( !f.isDie() ){
                return false;
            }
        }
        return true;
    }

    /**
     * 获取死亡英雄列表
     * @return
     */
    public List<FighterBase> getDieFighters()
    {
    	List<FighterBase> ret = new ArrayList<FighterBase>();
    	for( FighterBase f : fighters ){
            if( f.isDie() ){
                ret.add( f );
            }
        }
    	return ret;
    }
    
    @Override
    public List<FighterBase> getAllFighters() {
        List<FighterBase> ret = new ArrayList<FighterBase>();
        for( FighterBase f : fighters ){
            if( !f.isDie() ){
                ret.add( f );
            }
        }
        return ret;
    }

    @Override
    public FighterBase getBaseDefender( FighterBase attacker ) {

    	// 先获得前排英雄
    	List<FighterBase> fighterList = getFightByFrontrow();
    	
    	// 这里 如果前排没人 那么就取后排
    	if( fighterList.isEmpty() ){
    		fighterList	= getFightByBackrow();
    	}
    	if( fighterList.isEmpty() ){
    		Logs.error( "战斗 -获取普通攻击英雄   ： 未找到被攻击者！" );
    		return null;
    	}
    	
    	// 取得攻击者列数
    	int col 				= getCol( attacker.getPosition() );
    	
    	
    	FighterBase[] isHave 	= new FighterBase[COUNT_PER_ROW];
    	
    	// 这里写死  所以如果以后要改规则 那么就要重新写
    	for( int i = 0; i < COUNT_PER_ROW; i++ ){
    		isHave[i] = null;
    		for( FighterBase f : fighterList ){
    			int tmpe = getCol( f.getPosition() );
        		
    			if( tmpe == col )
                    return f;
        		
        		if( tmpe == i )
        			isHave[i] = f;
        	}
    	}
    	
    	FighterBase fighter = null;
    	// 如果在下面 那么就是从左到右  在上面就是从右到做
    	fighter = isBottom ? TheLeftToRight( isHave, col ) : TheRightToLeft( isHave, col );
    	if( fighter == null )
    		fighter = isBottom ? TheRightToLeft( isHave, col ) : TheLeftToRight( isHave, col );
    		
        return fighter;
    }

    // 从左到右
    private FighterBase TheLeftToRight( FighterBase[] isHave, int col ){
    	
    	int i = col;
    	
    	while( --i >= 0 )
    	{
    		if( isHave[i] != null )
    			return isHave[i];
    	}
    	
    	return null;
    }
    // 从右到左
    private FighterBase TheRightToLeft( FighterBase[] isHave, int col ){
    	
    	int i = col;
    	
    	while( ++i < isHave.length )
    	{
    		if( isHave[i] != null )
    			return isHave[i];
    	}
    	
    	return null;
    }
    
    private List<FighterBase> getFightersByCol(int col) {
        List<FighterBase> ret = new ArrayList<FighterBase>();
        for( FighterBase f : fighters ){
            if(  !f.isDie() && getCol( f.getPosition() ) == col ){
                ret.add( f );
            }
        }
        return ret;
    }

    /**
     * 按行获取战士
     * @param   attacker    攻击者
     * @return
     */
    /**
     *
     * @param attacker
     * @return
     */
    private List<FighterBase> getFightersByRow( FighterBase attacker ) {
        FighterBase defender = getBaseDefender( attacker );
        int row = getRow( defender.getPosition() );
        return getFightersByRow( row );
    }

    /**
     * 按列获取战士
     * @param attacker
     * @return
     */
    private List<FighterBase> getFightersByCol(FighterBase attacker) {
//    	FighterBase defender = getBaseDefender( attacker );
        int col = getCol( attacker.getPosition() );
        
        // 先获得前排英雄
    	List<FighterBase> fighterList = getFightByFrontrow();
    	for( FighterBase f : fighterList ){
            if( !f.isDie() && getCol( f.getPosition() ) == col ){
            	return getFightersByCol( col );
            }
        }
    	
        return new ArrayList<FighterBase>();
	}
    
    /**
     * 按行获取战士
     * @param row
     * @return
     */
    private List<FighterBase> getFightersByRow( int row ) {
        List<FighterBase> ret = new ArrayList<FighterBase>();
        for( FighterBase f : fighters ){
            if( !f.isDie() && getRow( f.getPosition() ) == row ){
                ret.add( f );
            }
        }
        return ret;
    }

    /**
     * 获取自己
     * @param fighter
     * @return
     */
    private List<FighterBase> getFightersBySelf( FighterBase fighter ) {
        List<FighterBase> ret = new ArrayList<FighterBase>();
        ret.add( fighter );
        return ret;
    }

    /**
     * 技能攻击模式下，找出普通攻击下被攻击的战士对象
     * @param attacker
     * @return
     */
    private List<FighterBase> getFightersByNormal(FighterBase attacker) {
    	
        List<FighterBase> ret = new ArrayList<FighterBase>();
        ret.add( getBaseDefender( attacker ) );
        
        return ret;
    }

    /**
     * 找出血量最少的战士
     * @return
     */
    private List<FighterBase> getFighterByMinHp( int num ) {
        List<FighterBase> ret = new ArrayList<FighterBase>();
        
        for( FighterBase f : fighters ){
            if( !f.isDie() )
            	 ret.add( f );
        }
       
        Collections.sort( ret, hpComparator );
        
        if( num >= ret.size() ) return ret;
        
        while( num < ret.size() ){
        	ret.remove( ret.size() - 1 );
        }
        
        return ret;
    }

        
    private int getRow(byte position) {
    	int row = position / COUNT_PER_ROW;
        row = row < COUNT_PER_ROW ? row : row - COUNT_PER_ROW;
        return row;
    }

    private int getCol( byte position ) {
        return position % COUNT_PER_ROW;
    }

    @Override
    public List<FighterBase> getFighterOnEffect( FighterBase attacker, ChooseFighters type ) {
        if( type == null )
            return null;
        switch( type ){
        	case COL:
        		return getFightersByCol( attacker );
            case ROW:
                return getFightersByRow( attacker );
            case SELF:
                return getFightersBySelf( attacker );
            case MIN_HP:
                return getFighterByMinHp( 1 );
            case MIN_HP2:
            	return getFighterByMinHp( 2 );
            case MIN_HP3:
            	return getFighterByMinHp( 3 );
            case NORMAL_ATTACK:
                return getFightersByNormal( attacker );
            case ALL:
                return getAllFighters();
            case RANDOM_1:
            	return getFightByRandom( 1 );
            case RANDOM_2:
            	return getFightByRandom( 2 );
            case RANDOM_3:
            	return getFightByRandom( 3 );
            case FRONTROW:
            	return getFightByFrontrow();
            case BACKROW:
            	return getFightByBackrow();
            case NEXT:
                return getFightByNext( attacker );
            default:
                break;
        }
        return null;
    }

    /**
     * 对 敌方后排
     * @param attacker
     * @return
     */
    private List<FighterBase> getFightByBackrow() {
    	// 根据防守方一个人的 位置确定 前排第一个位置
    	byte position = isBottom ? (byte)COUNT_PER_ROW : (TOTAL_COUNT + (byte)COUNT_PER_ROW);
    	
		return getFightersByRow( getRow( position ) );
	}

	/**
     * 对 敌方前排
     * @param attacker
     * @return
     */
    private List<FighterBase> getFightByFrontrow() {
    	// 根据防守方一个人的 位置确定 前排第一个位置
    	byte position = isBottom ? 0 : TOTAL_COUNT;
    	
		return getFightersByRow( getRow( position ) );
	}

	/**
     * 随机一个
     * @param attacker
     * @return
     */
	private List<FighterBase> getFightByRandom( int num ) {
		
		List<FighterBase> content = new ArrayList<FighterBase>();
		
        for( FighterBase f : fighters ){
            if( !f.isDie() )
            	content.add( f );
        }
        
		if( content.size() <= 0 ) return content;
		
		num = num > content.size() ? content.size() : num;
		
		List<FighterBase> ret = new ArrayList<FighterBase>();
		
		while( ret.size() < num ){
			
			int idx = RandomUtil.getRandomInt( 0, content.size() - 1 );
			
			FighterBase fb = content.remove(idx);
			
			ret.add( fb );
		}
		
		return ret;
	}

	/**
     * 获取当前攻击者的下一个出手战士
     *
     * @param attacker      攻击者
     * @return              下一个出手的战士列表
     */
    private List<FighterBase> getFightByNext( FighterBase attacker) {

        List<FighterBase> ret = new ArrayList<FighterBase>();
        
        byte pos = attacker.getPosition();
        
        for( FighterBase f : fighters ){
        	if( f.getPosition()%TOTAL_COUNT >= pos%TOTAL_COUNT && f.getPosition() != pos ){
        		ret.add( f );
        		return ret;
        	}
        }
        return ret;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder( "{" );
        for( FighterBase f : fighters ){
            sb.append( f.toSimpleString() );
            sb.append( "|" );
        }
        sb.append("}");
        return sb.toString();
    }

    public static void main(String[] args) {
        FighterBase f = new FighterBase( 1 );
        List<FighterBase> fighters = new ArrayList<FighterBase>();
        fighters.add( f );
        Formation ff = new Formation(fighters, false );
        System.out.println( ff.getFighterOnEffect( null, null ));
    }
}
