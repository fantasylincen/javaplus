package db.dao.impl;

import cn.javaplus.util.Util;
import cn.mxz.events.EventDispatcher2;
import cn.mxz.events.EventDispatcher2Impl;
import cn.mxz.events.Listener2;

/**
 *
 * DAO 缓存监控器, 5分钟全部清空一次
 *
 * @author 林岑
 *
 */
public class DaoCacheWartchDog extends Thread implements EventDispatcher2 {

	private static DaoCacheWartchDog	instance;

	private DaoCacheWartchDog() {
		setDaemon(true);
	}

	public static final DaoCacheWartchDog getInstance() {

		if (instance == null) {

			instance = new DaoCacheWartchDog();

			instance.start();
		}

		return instance;
	}

	private EventDispatcher2 dispatcher = new EventDispatcher2Impl();

	@Override
	public void run() {

		while(true) {
			try {
				dispatcher.dispatchEvent(new CacheRefreshEvent());
			} catch (Exception e) {
				throw Util.Exception.toRuntimeException(e);
			}
			Util.Thread.sleep(CacheBase.ALIVE_TIME);
		}
	}

	@Override
	public void addListener(Listener2 listener) {
		dispatcher.addListener(listener);
	}

	@Override
	public void clear() {
		dispatcher.clear();
	}

	@Override
	public void dispatchEvent(Object e) {
		dispatcher.dispatchEvent(e);
	}

	@Override
	public void removeListener(Listener2 listener) {
		dispatcher.removeListener(listener);
	}


}
