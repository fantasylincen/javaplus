package cn.vgame.a.account;

import cn.javaplus.events.Listener;
import cn.vgame.a.gen.dto.MongoGen.Daos;
import cn.vgame.a.gen.dto.MongoGen.RoleDto;
import cn.vgame.share.GameException;

public class SelectRoleEnterGameMarkOnline implements Listener<SelectRoleEnterGameEvent> {

	@Override
	public void onEvent(SelectRoleEnterGameEvent e) {

		Role role = e.getRole();

		RoleDto dto = role.getDto();
		dto.setIsOnline(true);
		Daos.getRoleDao().save(dto);
	}

}
