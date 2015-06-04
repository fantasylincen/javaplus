package game.hero;

import game.fighter.Hero;
import game.util.heroGrowup.HeroGrowupFormula;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import user.UserInfo;
import util.sort.HeroSortFormula;

import define.DefaultCfg;

public class HeroPageControl {
	
	// 当前页
	private byte 									curPage;
	
	// 最大页
	private byte 									maxPage;
	
	// 颜色区分列表
	private List<List<Hero>>						map ;
	
	// 列表总数据
	private List<Hero> 								aggregate;

	// 当前排序类型
	private HeroSort								curSort 	= null;
	
	// 当前选择排序类型
	private SelectSort								curSSort 	= null;
	
	// 总个数
	private short									curNum;
	
	private UserInfo								user;
	
	public HeroPageControl( ConcurrentHashMap<Integer,Hero> allHeroList, UserInfo user ) {
		aggregate		= new ArrayList<Hero>();
		map				= new ArrayList<List<Hero>>();
		for( int i = 0; i < HeroGrowupFormula.size(); i++ ){
			List<Hero> list = new ArrayList<Hero>();
			map.add( list );
		}
		
		this.user		= user;
		curNum			= 0;
		updata( allHeroList );
	}
	
	public List<List<Hero>> getList() {
		return map;
	}
	public List<Hero> getData(){
		return aggregate;
	}
	public byte getCurPage(){
		return curPage;
	}
	public byte getMaxPage(){
		return maxPage;
	}
	public short getCurNum(){
		if( curSSort == SelectSort.WITHOUT_THE_CAPTAIN )
			return (short) (curNum - user.getTeamManager().getTeam().size()) ;
		return curNum;
	}
	
	public void sortToNull(){
		curSort = null;
	}
	
	/**
	 * 根据页数获取 数据
	 * @param page
	 * @return
	 */
	public List<Hero> get( byte page ){
		List<Hero> list = new ArrayList<Hero>();
		
		if( page > maxPage ) page = maxPage;
		if( page < 1 ) page = 1;
		
		curPage		= page;
		
		int index 	= (page - 1) * DefaultCfg.PAGE_MAX;
		for( int i = index; i < aggregate.size() && list.size() < DefaultCfg.PAGE_MAX; i++ ){
			
			Hero temp = aggregate.get(i);
			
			// 这里 无团队排序 做下特殊处理
			if( curSSort == SelectSort.WITHOUT_THE_CAPTAIN && user.getTeamManager().get( temp.getUID() ) != null )
				continue;
			
			list.add( aggregate.get(i) );
		}
		
		return list;
	}
	
	// 刷新数据
	public void updata( ConcurrentHashMap<Integer,Hero> allHeroList ){
		
		for( List<Hero> list : map ){
			list.clear();
		}
		
		curNum	= (short) allHeroList.values().size();
		
		for( Hero temp : allHeroList.values() ){
			
			// 根据品质获取 实际数
			byte actualNum		= HeroGrowupFormula.actualNumberFormula( temp.getQuality() ) ;
			
			map.get( actualNum ).add( temp );
		}
		
		// 这里在排个序
		for( List<Hero> list : map )
			Collections.sort( list, posComparator );
		
		curPage			= 1;
		maxPage 		= (byte) ( curNum / DefaultCfg.PAGE_MAX );
		if( curNum % DefaultCfg.PAGE_MAX != 0 )
			maxPage		+= 1;
		
		curSort			= null;
		
		sort( HeroSort.PURPLE_SORT, SelectSort.NORMAL );
	}
	
	// 排序
	public void sort( HeroSort sort, SelectSort select ){
		
		// 先排颜色 
		if( curSort != sort ){
			aggregate.clear();
			sort.run( map, aggregate );
			curSort		= sort;
		}
		
		curSSort 		= select;
		// 然后再 选择排序
		select.run( map, aggregate, user );
	}

	// 排序  这里是按职业和等级
	private Comparator<Hero> posComparator = new Comparator<Hero>(){
        @Override
        public int compare( Hero h1, Hero h2 ) {
        	
			int result 	= HeroSortFormula.professionalComparator.compare(h1, h2);
			if( result != 0 )
				return result;
			result 		= HeroSortFormula.levelComparator1.compare(h1, h2);
			if( result != 0 )
				return result;
			
            return 0;
        }
    };
	
}
