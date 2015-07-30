package org.hhhhhh.fqzs.login;

import java.util.Properties;
import java.util.Set;

import org.javaplus.game.common.assets.Assets;
import org.javaplus.game.common.util.Sets;
import org.javaplus.game.common.util.Util;

import com.badlogic.gdx.files.FileHandle;

public class GateConfig {

	private String configAction;

	private String zoneId;

	private String gateUrl;

	public GateConfig() {

		FileHandle file = Assets.getDefaultLoader().getFile("data/.properties");
		String string = file.readString();
		Properties p = Util.Properties.getProperties(string);
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
