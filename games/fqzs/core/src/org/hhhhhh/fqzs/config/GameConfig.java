package org.hhhhhh.fqzs.config;

import com.alibaba.fastjson.JSON;

public class GameConfig {
	String gameXmlPath;
	String gameXmlVersion;
	String host;
	boolean isDebug;
	boolean isNotice;
	boolean isShowDjt;
	boolean isShowGg;
	boolean isShowJd;
	boolean isShowJs;
	boolean isShowZfb;
	boolean isShowZxwj;

	String tokenKey;
	String webContextRoot;
	String zipGameXmlPath;
	String zipXmlVersion;
	String zoneId;
	String zoneName;

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

	public String getGameXmlPath() {
		return gameXmlPath;
	}

	public void setGameXmlPath(String gameXmlPath) {
		this.gameXmlPath = gameXmlPath;
	}

	public String getGameXmlVersion() {
		return gameXmlVersion;
	}

	public void setGameXmlVersion(String gameXmlVersion) {
		this.gameXmlVersion = gameXmlVersion;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public boolean isDebug() {
		return isDebug;
	}

	public void setDebug(boolean isDebug) {
		this.isDebug = isDebug;
	}

	public boolean isNotice() {
		return isNotice;
	}

	public void setNotice(boolean isNotice) {
		this.isNotice = isNotice;
	}

	public boolean isShowDjt() {
		return isShowDjt;
	}

	public void setShowDjt(boolean isShowDjt) {
		this.isShowDjt = isShowDjt;
	}

	public boolean isShowGg() {
		return isShowGg;
	}

	public void setShowGg(boolean isShowGg) {
		this.isShowGg = isShowGg;
	}

	public boolean isShowJd() {
		return isShowJd;
	}

	public void setShowJd(boolean isShowJd) {
		this.isShowJd = isShowJd;
	}

	public boolean isShowJs() {
		return isShowJs;
	}

	public void setShowJs(boolean isShowJs) {
		this.isShowJs = isShowJs;
	}

	public boolean isShowZfb() {
		return isShowZfb;
	}

	public void setShowZfb(boolean isShowZfb) {
		this.isShowZfb = isShowZfb;
	}

	public boolean isShowZxwj() {
		return isShowZxwj;
	}

	public void setShowZxwj(boolean isShowZxwj) {
		this.isShowZxwj = isShowZxwj;
	}

	public String getTokenKey() {
		return tokenKey;
	}

	public void setTokenKey(String tokenKey) {
		this.tokenKey = tokenKey;
	}

	public String getWebContextRoot() {
		return webContextRoot;
	}

	public void setWebContextRoot(String webContextRoot) {
		this.webContextRoot = webContextRoot;
	}

	public String getZipGameXmlPath() {
		return zipGameXmlPath;
	}

	public void setZipGameXmlPath(String zipGameXmlPath) {
		this.zipGameXmlPath = zipGameXmlPath;
	}

	public String getZipXmlVersion() {
		return zipXmlVersion;
	}

	public void setZipXmlVersion(String zipXmlVersion) {
		this.zipXmlVersion = zipXmlVersion;
	}

	public String getZoneId() {
		return zoneId;
	}

	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}

	public String getZoneName() {
		return zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

	public String getRootPath() {
		return "http://" + host + "/" + webContextRoot + "/";
	}
}
