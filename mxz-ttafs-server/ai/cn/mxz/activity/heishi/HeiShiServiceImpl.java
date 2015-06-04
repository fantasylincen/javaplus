package cn.mxz.activity.heishi;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.mxz.TrueBlackMarketTemplet;
import cn.mxz.TrueBlackMarketTempletConfig;
import cn.mxz.base.service.AbstractService;
import cn.mxz.city.City;
import cn.mxz.handler.HeishiService;
import cn.mxz.protocols.heishi.HeishiP.HeishiMessage;
import cn.mxz.protocols.heishi.HeishiP.HeishiPro;
import cn.mxz.protocols.heishi.HeishiP.HeishiPro.Builder;

@Component("heishiService")
@Scope("prototype")
public class HeiShiServiceImpl extends AbstractService implements HeishiService{

	@Override
	public HeishiPro getData() {
		City user = getCity();
		HeishiManager heishiManager = user.getHeishiManager();
		Builder builder = HeishiPro.newBuilder();
		builder.setBuyInfo( heishiManager.mapToStr() );
		builder.setQsjs( heishiManager.getQsjs() );
		builder.setHsj(heishiManager.getHsj());
		builder.setBsj(heishiManager.getBsj());
		builder.setRemainEndSecond( HeishiActivity.INSTANCE.getEndRemainSecond() );//活动结束时间，秒
		List<Message> allMessage = HeishiActivity.INSTANCE.getMessage().getAllMessage();
		for( Message msg : allMessage ){
			int propId = msg.getPropId();
			TrueBlackMarketTemplet templet = TrueBlackMarketTempletConfig.get( propId);
			if(templet == null) {
				continue;
			}
			
			cn.mxz.protocols.heishi.HeishiP.HeishiMessage.Builder msgBuilder = HeishiMessage.newBuilder();
			msgBuilder.setNickName( msg.getNickName() );
			String propName = templet.getName();
			msgBuilder.setPropName( propName );
			msgBuilder.setQuality( templet.getQuality() );
			//System.out.println( msg.getNickName() + " " + propName );
			builder.addMessages( msgBuilder.build() );	
		}
		return builder.build();
		
		
	}

	@Override
	public HeishiPro exchange(int propId, int count) {
		City user = getCity();
		HeishiActivity.INSTANCE.exchange( user, propId, count);
		return getData();
		
	}

}
