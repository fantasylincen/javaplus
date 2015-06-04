package cn.mxz.corona;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.mxz.base.service.AbstractService;
import cn.mxz.handler.CoronaService;
import cn.mxz.protocols.corona.CoronaP.CoronaMessageSPro;
import cn.mxz.protocols.corona.CoronaP.CoronaPro;
import cn.mxz.protocols.user.PropP.PropPro;

@Component("coronaService")
@Scope("prototype")

public class CoronaServiceImpl extends AbstractService implements CoronaService {

	@Override
	public CoronaPro getData() {


//		City user = getCity();
//		UserCoronaData ucd = CoronaManager.getInstance().getUserData( user.getId() );
//
//		Builder b = CoronaPro.newBuilder();
//
//		b.setCd( ucd.getCd() );
//		b.setRefreshTimes( ucd.getRefreshTimes() );
//		b.setRunTimes( ucd.getFreeRunTimes() );
//		b.setTimeStamp( ucd.getTimeStamp() );
//		List<Prize> list = ucd.getCoronaData();
//
//		cn.mxz.protocols.user.PropP.PropsPro.Builder pp = PropsPro.newBuilder();
//
//		pp.addAllProps( new PropsBuilder().build(list) );
//
//		b.setProps(pp);
//
//
//		return b.build();
		return null;
	}

	@Override
	public PropPro run(int type, int ts) {
//		City user = getCity();
//
//		Prize p = CoronaManager.getInstance().run( user.getId(), type, ts );
//		return new PropBuilder().build(p);
		return null;
	}



	@Override
	public void refreshCorona() {
//		City user = getCity();
//		CoronaManager.getInstance().refreshCorona( user.getId() );

	}

	@Override
	public CoronaMessageSPro getMessages() {
//		List<CoronaMessage> list = CoronaManager.getInstance().getMessages();
//
//		CoronaMessageSPro.Builder pp = CoronaMessageSPro.newBuilder();
//
//		for( CoronaMessage cm : list){
//			CoronaMessagePro.Builder b = CoronaMessagePro.newBuilder();
//			b.setCount( cm.getPrize().getCount() );
//			b.setName( cm.getUname() );
//			b.setPid( cm.getPrize().getId() );
//			pp.addMessages( b );
//		}
//
//		return pp.build();
		return null;

	}



}
