package cn.mxz.newpvp;

import java.util.Date;

import message.S;
import mongo.gen.MongoGen.PvpWarSituationDto;
import cn.javaplus.time.Time;
import cn.javaplus.util.Util;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.city.IMessageSender;
import cn.mxz.protocols.pvp.PvpP.VideoPro;
import cn.mxz.protocols.user.battle.WarSituationP.CampPro;
import cn.mxz.protocols.user.battle.WarSituationP.FormationPro;
import cn.mxz.protocols.user.battle.WarSituationP.WarSituationPro;
import cn.mxz.user.builder.UserBaseBuilder;

import com.google.protobuf.InvalidProtocolBufferException;

class VideoBuilder {

	private City	city;

	public VideoBuilder(City city) {
		this.city = city;
	}

	// 瞿伟用: 恩怨PVP标签
	public VideoPro build(PvpWarSituationDto ws) {
		
		WarSituationPro pro;
		try {
			pro = WarSituationPro.parseFrom(ws.getData());
			
		} catch (InvalidProtocolBufferException e) {
			throw Util.Exception.toRuntimeException(e);
		}
		
		VideoPro.Builder b = VideoPro.newBuilder();
		City city = CityFactory.getCity(ws.getChallengerId());

		PvpManager ma = city.getNewPvpManager();

		String id = this.city.getId();

		boolean isBeAttack = !ws.getChallengerId().equals(id);

		PvpWarsituationUser other = getOther(pro);
		
		b.setUser(new UserBaseBuilder().build(other));

		b.setVideoId(ws.getSituationId());
		b.setWinPoint(ma.getPlayer().getScore());


		// 我是否胜利了
		boolean isWin = isBeAttack ? !ws.getIsWin() : ws.getIsWin(); // 如果是我被打了,
																		// 判断对方是否输了

		if (isWin) {
			b.setText(city.getMessageSender().buildText(S.S10200));
		} else {
			b.setText(city.getMessageSender().buildText(S.S10199));
		}

		b.setTime(buildTime(ws));
		b.setIsWin(isWin);

		b.setIsBeAttack(isBeAttack);

		return b.build();
	}

	// 春生用: 斗法详情界面
	public VideoPro buildCs(PvpWarSituationDto ws) {
		
		WarSituationPro pro;
		try {
			pro = WarSituationPro.parseFrom(ws.getData());
			
		} catch (InvalidProtocolBufferException e) {
			throw Util.Exception.toRuntimeException(e);
		}
		

		VideoPro.Builder b = VideoPro.newBuilder();

		City city = CityFactory.getCity(ws.getChallengerId());

		PvpManager ma = city.getNewPvpManager();

		String id = this.city.getId();

		boolean isBeAttack = !ws.getChallengerId().equals(id);

		PvpWarsituationUser other = getOther(pro);
		
		b.setUser(new UserBaseBuilder().build(other));

		b.setVideoId(ws.getSituationId());

		int score = ma.getPlayer().getScore();

		b.setWinPoint(score);

		Date date = new Date(ws.getCreateTime() * 1000L);

		String buildText = buildText(date, isBeAttack, ws.getIsWin(), other, score);
		b.setText(buildText + "\r");

		b.setTime(buildTime(ws));

		b.setIsWin(ws.getIsWin());

		b.setIsBeAttack(isBeAttack);

		return b.build();
	}

	private String buildText(Date date, boolean isBeAttack, boolean isWin, PvpWarsituationUser other, int score) {

		IMessageSender s = city.getMessageSender();
		String nick = other.getNick();

//	60204
//	60205
		if (isWin) {
			return s.buildText(S.S60204, nick/*, score*/);
		} else {
			return s.buildText(S.S60205, nick/*, score*/);
		}
//		if (isBeAttack) {
//			if (isWin) {
//				return s.buildText(S.S10021, nick, score);
//			} else {
//				return s.buildText(S.S10022, nick, score);
//			}
//		} else {
//			if (isWin) {
//				return s.buildText(S.S10023, nick, score);
//			} else {
//				return s.buildText(S.S10024, nick, score);
//			}
//		}
	}

	private PvpWarsituationUser getOther(WarSituationPro pro) {
		
		FormationPro fs = pro.getFormation();
		CampPro up = fs.getUppers();
		CampPro ud = fs.getUnders();

		String nick = city.getPlayer().getNick();
		
		if(nickEquals(up, nick)) {
			return new PvpWarsituationUserImpl(ud);
		} else {
			return new PvpWarsituationUserImpl(up);
		}
	}

	private boolean nickEquals(CampPro up, String nick) {
		String nick2 = up.getNick();
		if(nick2 == null) {
			return false;
		}
		
		return nick2.equals(nick);
	}

	private String buildTime(PvpWarSituationDto ws) {

		long createTime = ws.getCreateTime() * 1000L;

		long t2 = System.currentTimeMillis() - createTime;

		t2 /= Time.MILES_ONE_MIN;// 转分

		if (t2 <= 60) {
			return t2 + S.STR10308;
		}

		t2 /= 60;// 转小时

		if (t2 < 24) {
			return t2 + S.STR10306;
		}

		t2 /= 24;

		return t2 + S.STR10307;

	}

//	private String formate(Date date) {
//		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//		return f.format(date);
//	}

}
