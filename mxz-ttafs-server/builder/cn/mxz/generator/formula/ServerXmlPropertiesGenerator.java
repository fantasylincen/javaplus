package cn.mxz.generator.formula;

import java.io.File;
import java.util.List;

import cn.javaplus.string.StringPrinter;
import cn.javaplus.util.Util;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.lemon.commons.database.ServerProperties;

public class ServerXmlPropertiesGenerator {

	private static final String PATH = "D:/workspace/LoginServer/res/servers";

	public static void generate() {
		generateForServer();
		generateForClient();
	}

	private static void generateForClient() {
		List<ServerProperties> servers = getProperties();
		String temp = buildTemp("ttafs_server");
		temp = temp.replaceAll("SERVERS_VERSION", getVersion());
		StringPrinter out = new StringPrinter();

		out.println("");

		for (ServerProperties s : servers) {
			out.print("		<server ");
			print(out, "logDataBasePassword", s.getLogDataBasePassword());
			print(out, "port", s.getPort());
			print(out, "dataBaseUname", s.getDataBaseUname());
			print(out, "enterAble", s.isEnterAble());
			print(out, "test", s.isTest());
			print(out, "logDataBaseUname", s.getLogDataBaseUname());
			print(out, "dataBasePassword", s.getDataBasePassword());
			print(out, "gameManagerPort", s.getGameManagerPort());
			print(out, "updateSite", s.getUpdateSite());
			print(out, "ip", s.getIp());
			print(out, "id", s.getId());
			print(out, "dataBasePath", s.getDataBasePath());
			print(out, "showAble", s.isShowAble());
			print(out, "logDataBasePath", s.getLogDataBasePath());
			print(out, "name", s.getName());
			print(out, "playerCountMax", s.getPlayerCountMax());
			print(out, "Reserve", "");
			out.println(" /> ");
		}

		Util.File.write(PATH + "/ttafs_server.xml", temp.replace("SERVERS", out.toString()));
	}

	private static String getVersion() {
		return Util.File.getContent("res/build/version").trim();
	}

	private static void print(StringPrinter out, String n, Object v) {
		out.print(" " + n);
		out.print("=");
		out.print("\"");
		out.print(v);
		out.print("\"");
	}

	private static List<ServerProperties> getProperties() {
		List<ServerProperties> ls = Lists.newArrayList();
		List<File> fs = Util.File.getFiles(PATH);
		for (File file : fs) {
			if(file.getName().matches("[0-9]{6}\\.json")) {
				ls.add(buildServerProperties(file));
			}
		}
		return ls;
	}

	private static ServerProperties buildServerProperties(File file) {
		String content = Util.File.getContent(file);
		ServerProperties p = JSON.parseObject(content, ServerProperties.class);
		return p;
	}

	private static String buildTemp(String string) {
		return Util.File.getContent("res/build/" + string + ".xml.temp");
	}

	private static void generateForServer() {
		List<ServerProperties> servers = getProperties();
		String temp = buildTemp("ttafs_serverlist");
		temp = temp.replaceAll("SERVERS_VERSION", getVersion());
		StringPrinter out = new StringPrinter();
		out.println("");
		for (ServerProperties s : servers) {
			out.print("		<server ");
			print(out, "server_name", s.getName());
			print(out, "server_ID", s.getId());
			print(out, "isNew", s.isNew());
			print(out, "status", s.getStatus());
			print(out, "flag", s.getFlag());
			print(out, "view", s.isShowAble() ? 1 : 0);
			print(out, "game", s.getIp() + ":" + s.getPort());
			out.println(" /> ");
		}
		Util.File.write(PATH + "/ttafs_serverlist.xml", temp.replace("SERVERS", out.toString()));
	}


}
