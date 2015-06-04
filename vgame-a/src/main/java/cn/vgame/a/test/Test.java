package cn.vgame.a.test;

import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.javaplus.util.Util;
import cn.vgame.a.gen.dto.MongoGen.Daos;
import cn.vgame.a.gen.dto.MongoGen.MongoDbProperties;
import cn.vgame.a.gen.dto.MongoGen.RoleDao;
import cn.vgame.a.gen.dto.MongoGen.RoleDto;
import cn.vgame.a.util.RoleIdGenerator;

public class Test {
	protected static HttpServletResponse response;
	protected static HttpServletRequest request;
	protected static HttpSession session;

	static class MongoDbPropertiesImpl implements MongoDbProperties {

		private MongoDbPropertiesImpl() {
		}

		@Override
		public int getPort() {
			return 27017;
		}

		@Override
		public String getName() {
			return "dbjd";
		}

		@Override
		public String getHost() {
			return "localhost";
		}
	}


}
