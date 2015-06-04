package util.sort;

import game.fighter.Hero;
import game.growup.Colour;

import java.util.Comparator;

/**
 * 英雄排序 算法
 * @author DXF
 *
 */
public class HeroSortFormula {
	
	/** 根据品质 从紫色开始 **/
	public static final Comparator<Hero> qualityComparator_2 = new Comparator<Hero>() {
		@Override
		public int compare(Hero o1, Hero o2) {
			
			if( o2.getQuality().getColour() == o1.getQuality().getColour() ) {
				if( o2.getQuality().getLevel() == o1.getQuality().getLevel() )
					return 0;
				else
					return o2.getQuality().getLevel() - o1.getQuality().getLevel();
			}
			
			if( o1.getQuality().getColour() == Colour.PURPLE ){
				return -1;
			}else if( o2.getQuality().getColour() == Colour.PURPLE  ){
				return 1;
			}else if( o1.getQuality().getColour() == Colour.BLUE  ){
				return -1;
			}else if( o2.getQuality().getColour() == Colour.BLUE  ){
				return 1;
			}else if( o1.getQuality().getColour() == Colour.GREEN  ){
				return -1;
			}else if( o2.getQuality().getColour() == Colour.GREEN  ){
				return 1;
			}
		
			return 0;
		}
	};
	/** 根据品质 从蓝色开始 **/
	public static final Comparator<Hero> qualityComparator_1 = new Comparator<Hero>() {
		@Override
		public int compare(Hero o1, Hero o2) {
			
			if( o2.getQuality().getColour() == o1.getQuality().getColour() ) {
				if( o2.getQuality().getLevel() == o1.getQuality().getLevel() )
					return 0;
				else
					return o2.getQuality().getLevel() - o1.getQuality().getLevel();
			}
			
			if( o1.getQuality().getColour() == Colour.BLUE ){
				return -1;
			}else if( o2.getQuality().getColour() == Colour.BLUE  ){
				return 1;
			}else if( o1.getQuality().getColour() == Colour.PURPLE  ){
				return -1;
			}else if( o2.getQuality().getColour() == Colour.PURPLE  ){
				return 1;
			}else if( o1.getQuality().getColour() == Colour.GREEN  ){
				return -1;
			}else if( o2.getQuality().getColour() == Colour.GREEN  ){
				return 1;
			}
			
			return 0;
		}
	};
	/** 根据品质 从绿色开始 **/
	public static final Comparator<Hero> qualityComparator_0 = new Comparator<Hero>() {
		@Override
		public int compare(Hero o1, Hero o2) {
			
			if( o2.getQuality().getColour() == o1.getQuality().getColour() ) {
				if( o2.getQuality().getLevel() == o1.getQuality().getLevel() )
					return 0;
				else
					return o2.getQuality().getLevel() - o1.getQuality().getLevel();
			}
			
			if( o1.getQuality().getColour() == Colour.GREEN ){
				return -1;
			}else if( o2.getQuality().getColour() == Colour.GREEN  ){
				return 1;
			}else if( o1.getQuality().getColour() == Colour.PURPLE  ){
				return -1;
			}else if( o2.getQuality().getColour() == Colour.PURPLE  ){
				return 1;
			}else if( o1.getQuality().getColour() == Colour.BLUE  ){
				return -1;
			}else if( o2.getQuality().getColour() == Colour.BLUE  ){
				return 1;
			}
			
			return 0;
		}
	};
	
	/** 根据 职业 从小到大 **/
    public static final Comparator<Hero> professionalComparator = new Comparator<Hero>() {
		@Override
		public int compare(Hero o1, Hero o2) {
			return o1.getProfessional().toNumber() - o2.getProfessional().toNumber();
		}
	};
	
	/** 根据等级 从大到小 **/
	public static final Comparator<Hero> levelComparator1 = new Comparator<Hero>() {
		@Override
		public int compare(Hero o1, Hero o2) {
			return o2.getLevel() - o1.getLevel();
		}
	};
	
	/** 根据等级 从小到大 **/
	public static final Comparator<Hero> levelComparator2 = new Comparator<Hero>() {
		@Override
		public int compare(Hero o1, Hero o2) {
			return o1.getLevel() - o2.getLevel();
		}
	};

	/**
	 * 筛选出售 英雄 
	 * @param user
	 * @return
	 */
//	public static List<Hero> ScreeningForSale( UserInfo user ) {
//		
//		List<Hero> list = new ArrayList<Hero>();
//		
//		for( Hero h : user.getHeroManager().getPageControl().getData() ){
//			if( h.IsNonHero() )
//				list.add(h);
//		}
//		
//		return list;
//	}
	
}
