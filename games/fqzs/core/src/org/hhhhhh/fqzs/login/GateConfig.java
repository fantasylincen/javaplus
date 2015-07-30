package org.hhhhhh.fqzs.login;

import java.util.Properties;
import java.util.Set;

import org.hhhhhh.fqzs.welcome.WelcomeStage;
import org.javaplus.game.common.util.Sets;
import org.javaplus.game.common.util.Util;

public class GateConfig {

	private String configAction;

	private String zoneId;

	private String gateUrl;

	public GateConfig() {

		String content = Util.File.getContent(WelcomeStage.class.getResource(".properties"));
		Properties p = Util.Properties.getProperties(content);
		Set<Object> keySet = p.keySet();
		for (Object k : keySet) {
			replaceValue(k, p);
		}
		configAction = p.getProperty("configAction");
		gateUrl = p.getProperty("gateUrl");
		zoneId = p.getProperty("zoneId");

	}

	public String getGateUrl() {
		return gateUrl;
	}

	private void replaceValue(Object k, Properties p) {

		String v = p.getProperty(k.toString());
		Set<Object> ks = Sets.newHashSet(p.keySet());
		for (Object kk : ks) {
			v = v.replaceAll("\\$\\{" + kk + "\\}", p.getProperty(kk.toString()));
		}
		p.setProperty(k.toString(), v);
	}

	public String getConfigAction() {
		return configAction;
	}

	public void setConfigAction(String configAction) {
		this.configAction = configAction;
	}

	public String getZoneId() {
		return zoneId;
	}

	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}

}
