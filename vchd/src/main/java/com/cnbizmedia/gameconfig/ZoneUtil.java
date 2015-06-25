package com.cnbizmedia.gameconfig;

import cn.javaplus.log.Log;

import com.alibaba.fastjson.JSONObject;
import com.cnbizmedia.Server;
import com.cnbizmedia.config.GameProperties;
import com.cnbizmedia.gen.dto.MongoGen.KeyValueDto;
import com.cnbizmedia.gen.dto.MongoGen.ZoneDto;
import com.cnbizmedia.gm.Zone;
import com.cnbizmedia.gm.gamexml.ClientXml;
import com.cnbizmedia.gm.gamexml.GameXml;

public class ZoneUtil {

	public static Object buildZone(ZoneDto dto, String pid,
			String serverConfigKey) {

		Log.d("projectId", pid);
		Log.d("zoneId", dto.getId());
		Log.d("name", dto.getName());
		Log.d("serverConfigKey", serverConfigKey);
		
		JSONObject obj = new JSONObject();

		putHead(obj, dto, pid);
		for (KeyValueDto k : dto.getProperties()) {
			if (isShowKV(k, serverConfigKey)) {
				obj.put(k.getKey(), k.getValue());
			}
		}
		return obj;
	}

	public static Object getGameXmlPath(String pId, String zoneId) {
		String w = Server.getConfig().getMyIp();
		return "http://" + w + "/gm/fileDownload?projectId=" + pId + "&zoneId="
				+ zoneId;
	}

	/**
	 * 服务器配置表下载路径
	 * 
	 * @param pId
	 * @param zoneId
	 * @return
	 */
	public static Object getZipGameXmlPath(String pId, String zoneId) {
		String w = Server.getConfig().getMyIp();
		return "http://" + w + "/gm/zipFileDownload?projectId=" + pId
				+ "&zoneId=" + zoneId;
	}

	public static int getGameXmlVersion(String projectId, String zoneId) {
		Zone zone = Server.getProjectManager().getZone(projectId, zoneId);
		GameXml gameXml = zone.getGameXml();
		if(gameXml == null) {
			return 0;
//			throw new RuntimeException("game xml not found! please upload game xml file!");
		}
		int version = gameXml.getVersion();
		return version;
	}

	private static boolean isShowKV(KeyValueDto k, String serverConfigKey) {
		boolean visible = k.getIsClientVisible();
		if (visible)
			return true;
		String kkk = GameProperties.getString("serverConfigKey");
		boolean isServerKeyRight = kkk.equals(serverConfigKey);

		if (serverConfigKey != null && !isServerKeyRight) {
			Log.d("serverKeyError", kkk, serverConfigKey);
		}

		return isServerKeyRight;
	}

	public static void putHead(JSONObject obj, ZoneDto dto, String pid) {
		String zoneId = dto.getId();

		obj.put("zoneId", zoneId);
		obj.put("zoneName", dto.getName());
		obj.put("gameXmlPath", getGameXmlPath(pid, zoneId));
		obj.put("zipGameXmlPath", getZipClientXmlPath(pid, zoneId));
		obj.put("gameXmlVersion", getGameXmlVersion(pid, zoneId));
		obj.put("zipXmlVersion", getClientXmlVersion(pid, zoneId));
	}

	/**
	 * 客户端zip版本号
	 * 
	 * @param pid
	 * @param zoneId
	 * @return
	 */
	private static int getClientXmlVersion(String pid, String zoneId) {
		Zone zone = Server.getProjectManager().getZone(pid, zoneId);
		ClientXml clientXml = zone.getClientXml();
		if(clientXml == null)
			return 0;
		int version = clientXml.getVersion();
		return version;
	}

	/**
	 * 客户端下载zip配置表路径
	 * 
	 * @param pid
	 * @param zoneId
	 * @return
	 */
	private static Object getZipClientXmlPath(String pid, String zoneId) {
		String w = Server.getConfig().getMyIp();
		return "http://" + w + "/gm/fileDownloadClientXml?projectId=" + pid
				+ "&zoneId=" + zoneId;
	}

}
