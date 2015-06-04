package cn.vgame.b.account;

import cn.javaplus.events.Listener;
import cn.vgame.b.gen.dto.MongoGen.Daos;
import cn.vgame.b.gen.dto.MongoGen.RoleDto;

public class SelectRoleEnterGameMarkOnline implements Listener<SelectRoleEnterGameEvent> {

	@Override
	public void onEvent(SelectRoleEnterGameEvent e) {

		Role role = e.getRole();

		RoleDto dto = role.getDto();
		dto.setIsOnline(true);
		Daos.getRoleDao().save(dto);
	}

}
