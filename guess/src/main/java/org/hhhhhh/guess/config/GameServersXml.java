package org.hhhhhh.guess.config;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import cn.javaplus.util.Util;

import com.google.common.collect.Lists;
import com.google.common.io.Resources;

public class GameServersXml {

	@SuppressWarnings("unchecked")
	public static List<ServerNode> getServers() {

		SAXReader reader = new SAXReader();
		ArrayList<ServerNode> ls = Lists.newArrayList();
		try {
			Document d = reader.read(Resources.getResource("game-servers.xml"));
			Element root = d.getRootElement();
			List<Element> games = root.elements("game");

			for (Element game : games) {
				List<Element> servers = game.elements("server");
				String gameName = game.elementTextTrim("name");
				for (Element server : servers) {
					ServerNode e = new ServerNode(gameName, server);
					ls.add(e);
				}

			}
		} catch (DocumentException e) {
			throw Util.Exception.toRuntimeException(e);
		}

		return ls;
	}

	public static ServerNode getServer(String serverId) {
		final List<ServerNode> servers = GameServersXml.getServers();
		for (final ServerNode b : servers) {
			if (b.getString("id").equals(serverId)) {
				return b;
			}
		}
		return null;
	}
}
