package cn.mxz.chat;

import mongo.gen.MongoGen.WorldChatRecordDto;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.fighter.PlayerHero;

public class WorldChatMessage implements ChatMessage {

	private WorldChatRecordDto dto;

	public WorldChatMessage(WorldChatRecordDto dto) {
		this.dto = dto;
	}

	public WorldChatRecordDto getDto() {
		return dto;
	}

	@Override
	public int getTypeId() {
		PlayerHero p = getPlayerHero();
		return p.getTypeId();
	}

	private PlayerHero getPlayerHero() {
		City city = getCity();
		PlayerHero p = city.getTeam().getPlayer();
		return p;
	}

	private City getCity() {
		return CityFactory.getCity(dto.getSender());
	}

	@Override
	public int getStep() {
		return getPlayerHero().getStep();
	}

	@Override
	public String getNick() {
		return getCity().getPlayer().getNick();
	}

	@Override
	public int getLevel() {
		return getCity().getLevel();
	}

	@Override
	public int getVipLevel() {
		return getCity().getVipPlayer().getLevel();
	}

	@Override
	public String getContent() {
		String message = dto.getMessage();
		String[] split = message.split("\\|");
		return split[split.length - 1];
	}

	@Override
	public String getId() {
		return dto.getSender();
	}

	@Override
	public long getTime() {
		return dto.getTime();
	}

	@Override
	public String getFormatTime() {
		return new MessageTimeFormat().format(getTime());
	}

}
