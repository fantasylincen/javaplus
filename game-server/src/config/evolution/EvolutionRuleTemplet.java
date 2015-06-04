package config.evolution;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

import org.jdom2.Element;

/**
 * 进化规则模板
 * @author DXF
 *
 */
public class EvolutionRuleTemplet {

	private final byte					id;
	
	// 规则
	private final List<ConsumeBase>   	consume;
	
	// 需要金币
	private final int   				needGold;
	
	// 需要奖杯个数
	@Getter
	private final int					needTrophy;

	public EvolutionRuleTemplet(  Element element  ){
		
		this.id			= Byte.parseByte( element.getChildText( "id" ) );
		this.needGold 	= Integer.parseInt( element.getChildText( "needGold" ) );
		this.needTrophy	= Integer.parseInt( element.getChildText( "awardTrophy" ) );
		this.consume	= analysis( element.getChildText( "consume" ) );
	}
	
	
	private List<ConsumeBase> analysis( String content ) {
		
		List<ConsumeBase> rule 	= new ArrayList<ConsumeBase>();

		if( content.isEmpty()  )
			return rule;
		
		String[] list 			= content.split("\\|");
		
		for( String s : list ){
			
			String[] str 		= s.split(",");
			try {
				rule.add( new ConsumeBase( Byte.parseByte( str[0] ), Byte.parseByte( str[1] ), Byte.parseByte( str[2] )) );
			} catch (Exception e) {
				return rule;
			}
		}
		
		return rule;
	}

	public byte getId(){
		return this.id;
	}
	
	public int needGold() {
		return this.needGold;
	}

	public List<ConsumeBase> getConsume(){
		return this.consume;
	}

	public List<ConsumeBase> copyConsume() {
		List<ConsumeBase> list = new ArrayList<ConsumeBase>();
		
		for( ConsumeBase value : consume ){
			ConsumeBase con = new ConsumeBase( value );
			list.add( con );
		}
		
		return list;
	}
	
	public int count(){
		int count = 0;
		for( ConsumeBase value : consume )
			count += value.num();
		return count;
	}
}
