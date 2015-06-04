package cn.mxz.chat;

import cn.mxz.FighterTemplet;
import cn.mxz.FighterTempletConfig;
import cn.mxz.city.City;
import cn.mxz.enemy.EnemyManager;
import cn.mxz.friend.FriendManager;

public class PrivateChatMessageUIAdaptor implements ChatMessageUI {


	private UserChatMessage message;
	private City user;

	public PrivateChatMessageUIAdaptor(City user, UserChatMessage message) {
		this.user = user;
		this.message = message;
	}

	@Override
	public int getTypeId() {
		return message.getTypeId();
	}

	public int getStep() {
		return message.getStep();
	}

	public String getNick() {
		return message.getNick();
	}

	public int getLevel() {
		return message.getLevel();
	}

	public int getVipLevel() {
		return message.getVipLevel();
	}

	public String getContent() {
		return message.getShortContent();
	}

	@Override
	public String getId() {
		return message.getId();
	}

	@Override
	public boolean isMan() {
		FighterTemplet temp = FighterTempletConfig.get(getTypeId());
		return temp.getSex() == 1;
	}

	@Override
	public boolean isEnemy() {
		EnemyManager e = user.getEnemyManager();
		return e.isEnemy(message.getId());
	}

	@Override
	public boolean isFriend() {
		FriendManager fm = user.getFriendManager();
		return fm.isFriend(message.getId());
	}

	@Override
	public String getTime() {
		return message.getFormatTime();
	}

}
