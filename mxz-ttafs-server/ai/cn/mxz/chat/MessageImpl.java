package cn.mxz.chat;

import java.util.List;

import cn.mxz.city.City;
import cn.mxz.fighter.PlayerHero;
import cn.mxz.user.Player;
import cn.mxz.user.team.Team;
import cn.mxz.util.sencitive.SencitiveConfig;

import com.google.common.collect.Lists;

public class MessageImpl implements Message {

	private String	message;
	private City	sender;
	private long time;
	private City receiver;

	/**
	 * @param sender	发送者
	 * @param receiver 
	 * @param message
	 */
	public MessageImpl(City sender, City receiver, String message) {
		this.sender = sender;
		this.receiver = receiver;
		this.message = message;
		sencitive();
		time = System.currentTimeMillis();
	}

	private void sencitive() {
		List<String> words = splits();
		for (String string : words) {
			if(SencitiveConfig.isSencitive(string.replaceAll("\\s", ""))) {
				message = message.replaceAll(string, string.replaceAll(".", "*"));
			}
		}
	}

	private List<String> splits() {
		String[] split = message.split("");
		List<String> ls = Lists.newArrayList();
		for (int i = 0; i < split.length; i++) {
			add(ls, i, split);
		}
		return ls;
	}
	
//	public static void main(String[] args) {
//		SencitiveConfig.init();
//		new MessageImpl(null, "ad123啊扽了的");
//	}

	@Override
	public City getReceiver() {
		return receiver;
	}

	private static void add(List<String> ls, int i, String[] split) {
		for (int j = i; j < split.length; j++) {
			ls.add(get(split, i, j));
		}
	}

	private static String get(String[] split, int start, int end) {
		StringBuilder sb = new StringBuilder();
		for (int k = start; k <= end; k++) {
			sb.append(split[k]);
		}
		return sb.toString();
	}

	@Override
	public String getMessage() {
//		格式: 发送者ID|昵称|等级|性别(0:女 1:男)|主角神将ID|消息内容
		Player player = sender.getPlayer();
		String nick = player.getNick();
		Team team = sender.getTeam();
		PlayerHero hero = team.getPlayer();
		int typeId = hero.getTypeId();

		StringBuilder sb = new StringBuilder();
		sb.append(sender.getId() + "|");
		sb.append(nick + "|");
		sb.append(sender.getVipPlayer().getLevel() + "|");
		sb.append((player.isMan() ? 1 : 0) + "|");
		sb.append(typeId + "|");
		
		sb.append(message);
		return sb + "";
	}

	@Override
	public String getSender() {
		return sender.getId();
	}

	@Override
	public long getTime() {
		return time;
	}

	@Override
	public String getFormatTime() {
		return new MessageTimeFormat().format(getTime());
	}

}
