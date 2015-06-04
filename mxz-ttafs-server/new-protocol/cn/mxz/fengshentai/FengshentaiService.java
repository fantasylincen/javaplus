package cn.mxz.fengshentai;

import cn.mxz.city.City;
import cn.mxz.fengshentai.ui.ExchangePropUIImpl;
import cn.mxz.fengshentai.ui.FengshenMainUIImp;
import cn.mxz.fengshentai.ui.PropTransformImpl;

public class FengshentaiService implements IFengshentaiService {

	private City	user;

	@Override
	public IFengshenMainUI getdata() {
		FengshenMainUIImp ui = new FengshenMainUIImp();
		ui.setHasFengshenling( user.getFengshentaiManager().isHasfengshenling() );
		return ui;
	}

	@Override
	public void exchange() {
		user.getFengshentaiManager().exchange();
		
	}

	@Override
	public void fengshen( int fighterId) {
		boolean success = user.getFengshentaiManager().fengshen(fighterId);
		if( success ){
//		Hero hero = user.getTeam().getFightersAll().get(fighterId);
//		
//		FightersPro fighters = new FightersBuilder().build(Lists.newArrayList(hero));
//		
//		MessageFactory.getFighter().fightersUpdate(user.getSocket(), fighters);
		}
	}

	@Override
	public void setUser(City user) {
		this.user= user;
	}

	@Override
	public IExchangePropUI getExchangePropUI() {
		IExchangePropUI ui = new ExchangePropUIImpl( user.getFengshentaiManager().getPropData(), user );
		return ui;
	}

	@Override
	public IPropTransform buy(int propId) {
		PropItem item = user.getFengshentaiManager().buy( propId );
		return new PropTransformImpl(item, user);
	}

}
