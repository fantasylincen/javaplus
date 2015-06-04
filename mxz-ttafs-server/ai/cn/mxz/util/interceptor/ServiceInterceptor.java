package cn.mxz.util.interceptor;

import java.util.Iterator;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import cn.mxz.base.config.KeyValueDefine;
import cn.mxz.base.config.KeyValueManagerImpl;
import cn.mxz.base.world.WorldFactory;
import cn.mxz.city.City;
import cn.mxz.city.PlayerProperty;
import cn.mxz.temp.TempKey;
import cn.mxz.user.Player;
import cn.mxz.util.Service;
import cn.mxz.util.debuger.Debuger;

import com.google.common.collect.Lists;
import com.lemon.commons.socket.ISocket;

/**
 * 服务拦截器
 * 
 * @author 林岑
 * @time 2013-6-19
 */
@Aspect
@Component
public class ServiceInterceptor {

	private static final String CONFIG = "transformImpl() && !setUser() || serviceImpl() && !init() && !login()";

	@Pointcut("execution(public * cn.mxz..*TransformImpl.*(..))")
	public void transformImpl() {
	}

	@Pointcut("execution(public * cn.mxz..*.setUser(..))")
	public void setUser() {
	}

	@Pointcut("execution(public * cn.mxz..*Service.*init(..))")
	public void init() {
	}

	@Pointcut("execution(public * cn.mxz..InitService.init(..))")
	public void login() {
	}

	@Pointcut("execution(public * cn.mxz..*ServiceImpl.*(..))")
	public void serviceImpl() {
	}

	/**
	 * 各个通信包操作完成后日志记录
	 * 
	 * @throws Throwable
	 */
	@Around(CONFIG)
	public Object serviceLog(ProceedingJoinPoint jp) throws Throwable {

		long time = System.currentTimeMillis();

		Object rt = null;

		Throwable e = null;

		String method = getMethod(jp);

		Object tg = jp.getTarget();

		if (tg instanceof Service) {

			Service sb = (Service) tg;

			City u = sb.getCityNullAble();

			if (u != null) {
				u.getTempCollection().put(TempKey.OPERATION_THIS_TIME, method);
			}
		}

		try {

			rt = jp.proceed();

		} catch (Throwable e1) {

			e = e1;
		}

		long w = System.currentTimeMillis() - time;

		String log = method + "(" + buildArgs(jp) + ")" + "|" + w + "ms("
				+ tag(w) + ")|";

		if (tg instanceof Service) {

			Service sb = (Service) tg;

			City u = sb.getCityNullAble();

			ISocket s = sb.getSocket();

			if (s != null) {

				if (u == null) {

					u = WorldFactory.getWorld().getSocketManager().getUser(s);

				}

				if (u != null) {

					log += "userId:" + u.getId();

					log += "|nick:" + u.getPlayer().getNick();
				}

				log += "|socketId:" + s.getId();
			}

			if (w > 1000) {// 1秒
				System.err.println("该操作执行时间太长 你在调试吗? 如果不是调试, 那就得优化了!" + w);
			}
		}

		Debuger.debug("Service", log);

		if (e != null) {

			throw e;
		}

		return rt;
	}

	private String tag(long w) {
		if (w < 20) {
			return "a";
		}
		if (w < 50) {
			return "b";
		}
		return "c";
	}

	/**
	 * 金币日志
	 * 
	 * @param jp
	 * @return
	 * @throws Throwable
	 */
	@Around(CONFIG)
	public Object cashLog(ProceedingJoinPoint jp) throws Throwable {

		if (!(jp.getTarget() instanceof Service)) {
			return jp.proceed();
		}

		Service sb = (Service) jp.getTarget();

		City city = sb.getCityNullAble();

		if (city == null) {
			return jp.proceed();
		}

		String explain = getExplain(jp);
		Player player = sb.getPlayer();

		city.getTempCollection().put(TempKey.OPERATION_THIS_TIME, explain);

		int cashOld = getCash(sb);
		int goldOld = getGold(sb);
		int giftGoldOld = getGiftGold(sb);
		int rechargeGoldOld = getRechargeGold(sb);
		int jinBeiKeOld = getJinBeiKe(sb);

		Object rt = jp.proceed();

		int cashNow = getCash(sb);
		int goldNow = getGold(sb);
		int giftGoldNow = getGiftGold(sb);
		int rechargeGoldNow = getRechargeGold(sb);
		int jinBeiKeNow = getJinBeiKe(sb);

		city.getTempCollection().put(TempKey.OPERATION_LAST_TIME, explain);

		explain += "(" + buildArgs(jp) + ")";

		if (cashNow != cashOld) {

			log(

			"Cash",

			explain,

			cashNow - cashOld,

			cashNow,

			player.getNick(),

			city.getId());

		}

		if (jinBeiKeNow != jinBeiKeOld) {

			log(

			"JinBeiKe",

			explain,

			jinBeiKeNow - jinBeiKeOld,

			jinBeiKeNow,

			player.getNick(),

			city.getId());

		}

		if (goldNow != goldOld) {

			log(

			"Gold",

			explain,

			goldNow - goldOld,

			goldNow, rechargeGoldNow - rechargeGoldOld, // 充值元宝消耗

					giftGoldNow - giftGoldOld, // 系统元宝消耗

					rechargeGoldNow, // 充值元宝剩余

					giftGoldNow,// 系统元宝剩余
					
					player.getNick(),

					city.getId());

			if (goldNow < goldOld) { // 如果是消费

				String method = getMethod(jp);

				saveToDB(method, goldOld - goldNow);
			}
		}

		cn.mxz.log.Logs.consume.addLog(cashNow, cashOld, goldNow, goldOld,
				jinBeiKeNow, jinBeiKeOld, explain, city.getId(),
				player.getNick());

		return rt;
	}

	/**
	 * 记录消费点
	 * 
	 * @param method
	 * @param gold
	 */
	private void saveToDB(String method, int gold) {

		KeyValueManagerImpl k = new KeyValueManagerImpl();

		String c = k.get(KeyValueDefine.GOLD_REDUCE, "|" + method);

		Integer count = new Integer(c);

		count += gold;

		k.put(KeyValueDefine.GOLD_REDUCE, count, "|" + method);
	}

	private String getMethod(ProceedingJoinPoint jp) {
		Object tg = jp.getTarget();

		String name = jp.getSignature().getName();

		String method = tg.getClass().getSimpleName() + "." + name;
		return method;
	}

	private String buildArgs(ProceedingJoinPoint jp) {

		List<Object> args = Lists.newArrayList();

		for (Object arg : jp.getArgs()) {

			args.add(arg);
		}

		Iterator<Object> it = args.iterator();

		StringBuilder sb = new StringBuilder();

		while (it.hasNext()) {

			Object arg = it.next();

			sb.append(arg + "");

			if (it.hasNext()) {

				sb.append(",");
			}
		}

		return sb + "";
	}

	private String getExplain(ProceedingJoinPoint jp) {

		Object tg = jp.getTarget();

		String explain = tg.getClass().getSimpleName() + "."
				+ jp.getSignature().getName();

		return explain;
	}

	private void log(String head, String explain, Object... objs) {

		if (explain == null || explain.isEmpty()) {

			return;
		}

		StringBuilder info = new StringBuilder();

		info.append(explain + ";"); // 前置说明

		for (int i = 0; i < objs.length; i++) {

			boolean isLast = i == objs.length - 1;
			info.append(objs[i]);
			if (!isLast) {
				info.append(";");
			}

		}

		// info.append(userId + ";"); // 用户帐号
		//
		// info.append(userNick + ";"); // 用户昵称
		//
		// info.append(now + ";"); // 用户金币
		//
		// info.append(d); // 用户金币增量

		Debuger.info(head, info);
	}

	/**
	 * 获得玩家元宝
	 * 
	 * @param sb
	 * @return
	 */
	private int getGold(Service sb) {

		Player p = sb.getPlayer();

		if (p != null) {

			return p.getGold();
		}

		return 0;
	}

	private int getGiftGold(Service sb) {

		Player p = sb.getPlayer();

		if (p != null) {

			return p.getGiftGold();
		}

		return 0;
	}

	private int getRechargeGold(Service sb) {

		Player p = sb.getPlayer();

		if (p != null) {

			return p.getRechargeGold();
		}

		return 0;
	}

	/**
	 * 获取玩家金币
	 */
	private int getCash(Service sb) {

		Player p = sb.getPlayer();

		if (p != null) {

			return p.get(PlayerProperty.CASH);
		}

		return 0;
	}

	/**
	 * 玩家金贝壳
	 * 
	 * @param sb
	 * @return
	 */
	private int getJinBeiKe(Service sb) {

		Player p = sb.getPlayer();

		if (p != null) {

			return p.get(PlayerProperty.NEW_GOLD);
		}

		return 0;
	}
}
