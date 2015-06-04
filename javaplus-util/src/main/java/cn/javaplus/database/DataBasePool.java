package cn.javaplus.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import cn.javaplus.commons.logger.Logs;
import cn.javaplus.exception.RuntimeSQLException;
import cn.javaplus.util.Util.Thread;


/**
 * 游戏数据库连接池
 *
 * 负责分配,管理和释放数据库连接,它允许应用程序重复使用一个现有的数据库连接,当某些连接失效后, 该连接池自动重新补足足够的连接到连接池中
 *
 * 该连接池在执行getPst方法的时候, 会自动获得某个连接来执行, 同时会清空所有失效的 连接
 *
 * @author 	林岑
 * @time	2013年4月29日 21:24:24
 */
public class DataBasePool implements PreparedStatementFetcher {

	private static int closedSize = 0;

	/**
	 * 连接池大小.
	 */
	public static int SIZE = 20;

	/**
	 * 数据库连接配置
	 */
	private DBProperties properties;

	/**
	 * 所有数据库连接
	 */
	private Queue<Connection> connections;

	/**
	 * 构造一个数据库连接池
	 *
	 * @author 林岑	2010-9-4 下午11:33:50
	 *
	 * @param properties	数据库连接配置(驱动, 帐号密码...)
	 */
	public DataBasePool(DBProperties properties){
		this.properties = properties;
		initConnections();
//		new WatchDog().start();
	}

	/**
	 * 初始化SIZE个数据库连接
	 */
	private void initConnections() {

		try {
			Class.forName(properties.getDrives());
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}

		connections = new LinkedList<Connection>();
		for (int i = 0; i < SIZE; i++) {
			connections.add(createConnection());
		}
	}

	/*
	 * 创建一个新的数据库连接
	 */
	private Connection createConnection() {
		try {

			Connection c = DriverManager.getConnection(
					properties.getDataCon().trim(),
					properties.getUser(),
					properties.getPassword()
					);

			Logs.debug.debug("Create DB Connection Successful!" + properties.getDataCon());

			return c;

		} catch (SQLException e) {
			System.err.println("创建新连接失败");
			throw new RuntimeSQLException(e);
		}
	}

	@Override
	public PreparedStatement getPst(String sql) {
		try {
			return getConnection().prepareStatement(sql);
		} catch (SQLException e) {
			throw new RuntimeSQLException(e);
		}
	}

	/**
	 * 获取一个连接
	 *
	 * 执行过程:
	 *
	 * 	1.清除所有无效的连接
	 * 	2.用新连接替换已经被关闭或者断开了的连接
	 * 	3.取得第一个连接,取得之后,同时将第一个连接插入到队尾
	 *
	 * @return	一个连接
	 * @throws SQLException
	 */
	private synchronized Connection getConnection() throws SQLException {
		if(removeUnValidAll()) {
			Logs.debug.debug("Remove connections has been closed successful! closed count:" + closedSize);
		}

		fillNewConnections();
		return getFirstConnection();
	}

	/**
	 * 取得第一个连接,取得之后,同时将第一个连接插入到队尾.
	 * @return
	 */
	private Connection getFirstConnection() {

		while(connections.isEmpty()) {	//等到新连接产生了才去获取第一个连接
			Logs.debug.info("正在等待数据库新的连接");
			Thread.sleep(1);
		}

		Connection c = connections.poll();
		connections.add(c);
		return c;
	}

	/**
	 * 补足SIZE个连接
	 */
	private void fillNewConnections() {
//		new Thread(){会出现死锁...
//			public void run() {


				while(connections.size() < SIZE) {
					Connection c = createConnection();
					connections.add(c);
				}


//			};
//		}.start();
	}

	/**
	 * 移除所有无效连接
	 * @return
	 * @throws SQLException
	 */
	private boolean removeUnValidAll() throws SQLException {
		Iterator<Connection> it = connections.iterator();
		boolean hasSomeOneRemoved = false;
		while (it.hasNext()) {
			Connection c = it.next();
			if(!c.isValid(0)) {
				it.remove();
				closedSize++;
				hasSomeOneRemoved = true;
			}
		}
		return hasSomeOneRemoved;
	}

//	/**
//	 * 数据库连接监控线程
//	 * @author 林岑
//	 */
//	public class WatchDog extends Thread{
//
//		@Override
//		public void run() {
//			while(true) {
//
//				try {
//					getConnection();					//获取连接
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//
//				Util.sleep(1 * Time.MILES_ONE_MIN);
//			}
//		}
//	}
}
