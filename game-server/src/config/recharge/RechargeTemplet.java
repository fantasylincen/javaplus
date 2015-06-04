package config.recharge;


import lombok.Data;

import org.jdom2.Element;

@Data
public class RechargeTemplet {

	private final short  	id;
	
	private final int 		gold;
	
	private final float		money;

	private final String[]	goodsId;

	private final String	desc;
	
	public RechargeTemplet( Element element ) {
		
		id					= Short.parseShort( element.getChildText( "id" ) );
		gold				= Integer.parseInt( element.getChildText( "gold" ) );
		money				= Float.parseFloat( element.getChildText( "money" ) );
		String content[]	= element.getChildText( "goodsId" ).split("\\|");
		goodsId				= new String[3];
		for( int i = 0; i < content.length; i++ )
			goodsId[i]		= content[i];
		desc				= element.getChildText( "desc" );
	}

	public boolean isMonthCard(){
		return (int)(id * 0.01) == 1;
	}

	public boolean isRestriction() {
		return (int)(id * 0.01) == 3;
	}
	
}
