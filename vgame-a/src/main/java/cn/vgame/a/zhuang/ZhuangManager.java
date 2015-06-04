package cn.vgame.a.zhuang;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import cn.javaplus.collections.list.Lists;
import cn.javaplus.collections.map.Maps;
import cn.vgame.a.Server;
import cn.vgame.a.account.Role;
import cn.vgame.a.gen.dto.MongoGen.Daos;
import cn.vgame.a.gen.dto.MongoGen.ZhuangDao;
import cn.vgame.a.gen.dto.MongoGen.ZhuangDao.ZhuangDtoCursor;
import cn.vgame.a.gen.dto.MongoGen.ZhuangDto;
import cn.vgame.a.result.ErrorResult;
import cn.vgame.a.system.Const;
import cn.vgame.a.turntable.Turntable;
import cn.vgame.share.GameException;
import cn.vgame.share.KeyValue;

public class ZhuangManager {

	Map<String, Zhuang> roles;

	public ZhuangManager() {
		ZhuangDao dao = Daos.getZhuangDao();
		ZhuangDtoCursor all = dao.find();
		roles = Maps.newConcurrentMap();
		for (ZhuangDto dto : all) {
			roles.put(dto.getId(), new Zhuang(dto));
		}
	}

	public Zhuang getZhuang() {
		Collection<Zhuang> values = Lists.newArrayList(roles.values());
		if (values.isEmpty())
			return null;
		for (Zhuang z : values) {
			if (z.isZhuangNow()) {
				if (!z.isCoinEnouph() || z.isLastRound()) {
					z.xiaZhuang();
					continue;
				}
				return z;
			}
		}

		List<Zhuang> roles2 = getRoles();
		if (roles2.isEmpty())
			return null;
		Zhuang zhuang = roles2.get(0);
		if (zhuang.getStartQueueZhuangRound() == Turntable.getInstance().getRound())
			return null;
		zhuang.shangZhuang();
		return zhuang;
	}

	public void startQueuing(Role role) {
		if (isAreadyQueuing(role))
			throw new ErrorResult(10032).toException();

		checkLowCoin(role);
		checkAreadyZhuang(role);
		appendToZhuangQueue(role);
	}

	private void checkAreadyZhuang(Role role) {
		if (isZhuang(role.getId()))
			throw new ErrorResult(10034).toException();
	}

	private void appendToZhuangQueue(Role role) {
		Zhuang zhuang = createZhuang(role);
		roles.put(role.getId(), zhuang);

		KeyValue roleKv = zhuang.role().getKeyValueForever();
		int round = Turntable.getInstance().getRound();
		roleKv.set("START_QUEUE_ZHUANG_ROUND", round); // 开始排队的回合数
	}

	private Zhuang createZhuang(Role role) {
		ZhuangDao dao = Daos.getZhuangDao();
		ZhuangDto dto = dao.createDTO();
		dto.setId(role.getId());
		dto.setTime(System.currentTimeMillis());
		dao.save(dto);
		return new Zhuang(dto);
	}

	private void checkLowCoin(Role role) {
		Const c = Server.getConst();
		long min = c.getLong("SHANG_ZHUANG_COIN_MIN");
		if (role.getCoin() < min)
			throw new ErrorResult(10024, min).toException();
	}

	private boolean isAreadyQueuing(Role role) {
		return roles.containsKey(role.getId());
	}

	public void remove(String id) {
		Zhuang z = roles.remove(id);
		ZhuangDto dto = z.getDto();
		Daos.getZhuangDao().delete(dto);
	}

	public List<Zhuang> getRoles() {
		ArrayList<Zhuang> ls = Lists.newArrayList();
		ls.addAll(roles.values());
		Collections.sort(ls);
		return ls;
	}

	/**
	 * 是否正在坐庄
	 */
	public boolean isZhuang(String id) {
		Zhuang xz = getZhuang();
		if (xz == null)
			return false;

		return xz.getRoleId().equals(id);
	}

	public void requstXiaZhuang(String id) {
		Zhuang zhuang = getZhuang();
		if(zhuang == null) {
			return ;
		}
		
		String zId = zhuang.getRoleId();
		if(zId.equals(id)) {
			Role role = Server.getRole(zId);
			KeyValue roleKv = role.getKeyValueForever();
			int rt = zhuang.getRemainTimes();
			int rm = rt - 1;
			roleKv.add("END_ZHUANG_ROUND", -rm);
		} else {
			remove(id);
		}
	}

}
