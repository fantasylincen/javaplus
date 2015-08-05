package cn.javaplus.crazy.user;

import cn.javaplus.crazy.main.GameRoom;
import cn.javaplus.crazy.mongo.Daos;
import cn.javaplus.crazy.mongo.RoleDao;
import cn.javaplus.crazy.mongo.RoleDto;
import cn.javaplus.crazy.pocker.Place;
import cn.javaplus.crazy.pocker.Table;
import cn.javaplus.crazy.protocol.Protocols.Channel;
import cn.javaplus.crazy.role.RoleData;

public class User {

	private String uname;
	private int roleId;
	private String nick;
	private RoleDto dto;
	private Channel channel;
	private String id;

	public User(RoleData dto,
			cn.javaplus.crazy.protocol.Protocols.Channel channel) {
		this.channel = channel;
		uname = dto.getUname();
		roleId = dto.getRoleId();
		nick = dto.getNick();
		id = uname + ":" + roleId;
	}

	public String getNick() {
		return nick;
	}

	public int getRoleId() {
		return roleId;
	}

	public String getId() {
		return id;
	}

	public String getUname() {
		return uname;
	}

	public int getLevel() {
		return getRoleDto().getLevel();
	}

	private RoleDto getRoleDto() {
		if (dto == null) {
			RoleDao dao = Daos.getRoleDao();
			dto = dao.get(getRoleId());
		}
		if (dto == null) {
			dto = new RoleDto();
			dto.setId(getRoleId());
			dto.setLevel(1);
			RoleDao dao = Daos.getRoleDao();
			dao.save(dto);
		}
		return dto;
	}

	public Channel getChannel() {
		return channel;
	}

	public void commit() {
		Daos.getRoleDao().save(getRoleDto());
	}

	@Override
	public String toString() {
		return id;
	}

	public Place getPlace() {
		GameRoom room = Game.getGameRoom();
		Table table = room.getTable(id);
		Place p = table.getPlace(id);
		return p;
	}
}
