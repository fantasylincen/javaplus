package cn.mxz.chat;

import mongo.gen.MongoGen.Daos;
import mongo.gen.MongoGen.UserChatRecordDto;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.fighter.PlayerHero;

public class PrivateChatMessage implements UserChatMessage {

	// private City sender;
	// private Message message;
	// private boolean hasRead;
	private UserChatRecordDto dto;

	// /**
	// * @param sender 发送方
	// * @param owner 消息查看者
	// * @param message 消息内容
	// */
	// public PrivateChatMessage(City sender, Message message) {
	// // this.sender = sender;
	// // this.message = message;
	// }

	public PrivateChatMessage(UserChatRecordDto dto) {
		this.dto = dto;
	}

	@Override
	public int getTypeId() {
		PlayerHero player = getSender().getTeam().getPlayer();
		return player.getTypeId();
	}

	@Override
	public int getStep() {
		PlayerHero player = getSender().getTeam().getPlayer();
		return player.getStep();
	}

	@Override
	public String getNick() {
		return getSender().getPlayer().getNick();
	}

	@Override
	public int getLevel() {
		return getSender().getLevel();
	}

	@Override
	public int getVipLevel() {
		City sender = getSender();
		return sender.getVipPlayer().getLevel();
	}

	private City getSender() {
		String id = getId();
		City sender = CityFactory.getCity(id);
		return sender;
	}

	@Override
	public String getContent() {
		return dto.getMessage();
	}

	@Override
	public String getId() {
		return dto.getSender();
	}

	@Override
	public String getReceiverId() {
		return dto.getReceiver();
	}

	@Override
	public UserChatRecordDto getDto() {
		return dto;
	}

	@Override
	public long getTime() {
		return dto.getTime();
	}

	@Override
	public String getFormatTime() {
		return new MessageTimeFormat().format(getTime());
	}

	@Override
	public boolean hasRead() {
		return dto.getHasRead();
	}

	@Override
	public void onRead() {
		dto.setHasRead(true);
		Daos.getUserChatRecordDao().save(dto);
	}

	@Override
	public String getShortContent() {
		return new MessageBuilder().getContent(dto.getMessage());
	}

}
