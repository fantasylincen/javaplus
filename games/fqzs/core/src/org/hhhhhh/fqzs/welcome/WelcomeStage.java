package org.hhhhhh.fqzs.welcome;

import java.util.List;

import org.hhhhhh.fqzs.config.GameConfig;
import org.hhhhhh.fqzs.core.App;
import org.hhhhhh.fqzs.core.D;
import org.hhhhhh.fqzs.login.GateConfig;
import org.javaplus.game.common.http.HttpComponents.CallBackJsonAdaptor;
import org.javaplus.game.common.http.JsonResult;
import org.javaplus.game.common.log.Log;
import org.javaplus.game.common.stage.AbstractStage;
import org.javaplus.game.common.stage.GameUI;
import org.javaplus.game.common.util.Lists;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class WelcomeStage extends AbstractStage {

	public class GetGameConfigCallBack extends CallBackJsonAdaptor {

		@Override
		public void completed(JsonResult result) {

			GameConfig config = getConfig(result,gateConfig.getZoneId());
			Log.d("load game config successful");
			Log.d(config);
			
			App.setConfig(config);
			
			LoadOverEvent e = new LoadOverEvent(config);

			for (LoadOverListener d : listeners) {
				d.onLoadOver(e);
			}
		}
		
		@Override
		public void onTimeOut() {
			requestGameConfig(); //重新请求数据
			Log.d("重新请求游戏配置数据");
		}

		private GameConfig getConfig(JsonResult result, String zoneId) {

			JSONObject obj = result.toJsonObject();
			JSONArray zones = obj.getJSONArray("zones");
			for (Object object : zones) {
				JSONObject o = (JSONObject) object;
				String zid = o.getString("zoneId");
				if (zid.equals(zoneId)) {
					return JSON.parseObject(o.toJSONString(), GameConfig.class);
				}
			}
			return null;
		}
	}
	// {
	// "name":"FeiQinZouShou","projectId":"142562186547910000009",
	// "zones":[{
	// "gameXmlPath":"http://120.24.227.207/gm/fileDownload?projectId=142562186547910000009&zoneId=10002",
	// "gameXmlVersion":1,
	// "host":"113.204.112.162",
	// "isDebug":"true",
	// "isNotice":"true",
	// "isShowDjt":"true",
	// "isShowGg":"true",
	// "isShowJd":"true",
	// "isShowJs":"true",
	// "isShowZfb":"true",
	// "isShowZxwj":"true",
	// "tokenKey":"ACoiuqwe9712311d",
	// "webContextRoot":"vgame-a",
	// "zipGameXmlPath":"http://120.24.227.207/gm/fileDownloadClientXml?projectId=142562186547910000009&zoneId=10002",
	// "zipXmlVersion":1,
	// "zoneId":"10002",
	// "zoneName":"??(??)"
	// },
	//
	// {"gameXmlPath":...........................

	public WelcomeStage() {
		super(D.STAGE_W, D.STAGE_H);
	}

	@Override
	public void loadAssets() {
		App.getAssets().loadFontInternal("simhei.ttf");
	}

	@Override
	public void unloadAssets() {
		App.getAssets().unloadFont("simhei.ttf");
	}

	private List<LoadOverListener> listeners = Lists.newArrayList();
	private GateConfig gateConfig;

	@Override
	public void onLoadingOver() {
		gateConfig = App.getGateConfig();
		requestGameConfig();
	}

	private void requestGameConfig() {
		App.getHttp().request(gateConfig.getConfigAction(), new GetGameConfigCallBack());
	}

	@Override
	public boolean keyTyped(char c) {

		return true;
	}

	@Override
	public GameUI getGameUI() {
		return new GameUI() {

			@Override
			public void unload() {

			}

			@Override
			public void load() {

			}

			@Override
			public void buildComponents() {

			}
		};
	}

	public void addLoadOverListener(LoadOverListener loadOverListener) {
		this.listeners.add(loadOverListener);
	}

}
