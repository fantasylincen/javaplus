package cn.mxz.testbase;

import java.lang.reflect.Method;

import mongo.gen.MongoGen.Daos;

import org.junit.Before;

import cn.javaplus.comunication.ProtocolsLoader;
import cn.javaplus.util.Util;
import cn.mxz.base.server.MongoCollectionFetcher;
import cn.mxz.base.world.World;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;
import cn.mxz.handler.InitService;
import cn.mxz.init.InitServiceImpl;
import db.GameDB;
import db.dao.impl.DaoFactory;

/**
 * 这是一个已经通过系统认证后的测试用例
 *
 * 即: 模拟了一个正常用户, 这个用户已经登陆了, 可以进行各种操作
 *
 * 只有继承该测试用例,才可以进行各种操作, 否则莫法
 *
 * @author 林岑
 *
 */
public class TestBaseAccessed extends ServiceTestBase {

	private InitService		service;

	/**
	 * 当前用户ID
	 */
//	protected final String	userId	= "100011";
	protected final String	userId	= "lc601";

	/**
	 * 当前用户
	 */
	protected City			user;

	@Before
	public void access() {
		service = new InitServiceImpl();


		DaoFactory.setFetcher(GameDB.getInstance());
		Daos.setCollectionFetcher(new MongoCollectionFetcher());

		service.init(socket);
		service.access(userId, "222222", 0, userId, 0, 0, userId);
		user = getCity(userId);


	}

	/**
	 * 获得指定类型的Service
	 *
	 * @param c
	 *            比如 BagService.class MissionService.class
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected <T> T getService(Class<T> c) {
		String name = Util.Str.firstToLowerCase("" + c.getSimpleName());
		T t = (T) factory.get(name);
		Method[] ms = t.getClass().getMethods();
		for (Method method : ms) {
			if (method.getName().equals("setUser")) {

				ProtocolsLoader.bindUser(user, t);
				break;
			}
		}
		return t;
	}

	/**
	 * 拿到指定用户的城池
	 *
	 * @param uname
	 * @return
	 */
	protected City getCity(String uname) {

		World w = WorldFactory.getWorld();

		return w.get(uname);
	}
}
