package cn.vgame.a.zhuang;

import cn.vgame.a.Server;
import cn.vgame.a.account.Role;
import cn.vgame.a.gen.dto.MongoGen.Daos;
import cn.vgame.a.gen.dto.MongoGen.ZhuangDto;
import cn.vgame.a.system.Const;
import cn.vgame.a.turntable.Turntable;
import cn.vgame.share.KeyValue;

public class Zhuang implements Comparable<Zhuang> {

	private final ZhuangDto dto;

	public Zhuang(ZhuangDto dto) {
		this.dto = dto;
	}

	public String getRoleId() {
		return dto.getId();
	}

	public long getCoin() {
		return role().getCoin();
	}

	public String getNick() {
		return role().getNick();
	}

	/** 去掉get是为了让json不生成该字段 */
	public Role role() {
		String id = dto.getId();
		return Server.getRole(id);
	}

	public ZhuangDto getDto() {
		return dto;
	}

	@Override
	public int compareTo(Zhuang o) {
		return (int) (dto.getTime() / 1000)
				- (int) (o.getDto().getTime() / 1000);
	}

	/**
	 * 是否是庄
	 */
	public boolean isZhuangNow() {
		return dto.getIsZhuang();
	}

	/**
	 * 金豆是否足够
	 */
	public boolean isCoinEnouph() {
		Const c = Server.getConst();
		long min = c.getLong("SHANG_ZHUANG_COIN_MIN");
		return role().getCoin() >= min;
	}

	public void xiaZhuang() {

		dto.setIsZhuang(false);
		Daos.getZhuangDao().save(dto);

		Server.getZhuangManager().remove(getRoleId());
	}

	/**
	 * 上庄
	 */
	public void shangZhuang() {
		dto.setIsZhuang(true);
		Daos.getZhuangDao().save(dto);

		KeyValue roleKv = role().getKeyValueForever();
		int round = Turntable.getInstance().getRound();

		int zhuangRound = Server.getConst().getInt("MAX_ZHUANG_TIMES"); // 当庄回合数

		roleKv.set("END_ZHUANG_ROUND", round + zhuangRound);
	}

	/**
	 * 剩余当庄次数
	 */
	public int getRemainTimes() {
		int endZhuangRound = getEndZhuangRound();
		int currentRound = Turntable.getInstance().getRound();
		return Math.max(0, endZhuangRound - currentRound);
	}

	/**
	 * 当庄结束的回合数
	 */
	public int getEndZhuangRound() {
		KeyValue roleKv = role().getKeyValueForever();
		int round = roleKv.getInt("END_ZHUANG_ROUND");
		return round;
	}

	/**
	 * 开始坐庄的总回合数
	 */
	public int getStartQueueZhuangRound() {
		KeyValue roleKv = role().getKeyValueForever();
		int startZhuangRound = roleKv.getInt("START_QUEUE_ZHUANG_ROUND"); // 开始排队的回合数
		return startZhuangRound;
	}

	/**
	 * 是否是当庄的最后一轮
	 */
	public boolean isLastRound() {
		return getRemainTimes() == 0;
	}
}
