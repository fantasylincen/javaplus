package cn.vgame.b.turntable;

import cn.vgame.b.account.JsonActionAfterRoleEnterGame;
import cn.vgame.b.gen.dto.MongoGen.Daos;
import cn.vgame.b.gen.dto.MongoGen.RoleDao;
import cn.vgame.b.gen.dto.MongoGen.RoleDto;
import cn.vgame.b.gen.dto.MongoGen.RoleDao.RoleDtoCursor;
import cn.vgame.b.result.ErrorResult;
import cn.vgame.share.EncodingUtil;

/**
 * 获取角色基本信息 -----------------
 * 
 * A.正常情况: { roleId: r13125465, nick: 刘** , }
 * 
 * B.错误: 标准错误
 */
public class GetRoleAction extends JsonActionAfterRoleEnterGame {

	private static final long serialVersionUID = -4032228070954317882L;
	private String nickOrId;

	@Override
	public Object run() {

		RoleDto dto = findByNick();
		if (dto == null)
			dto = findById();
		if (dto == null)
			throw new ErrorResult(10031).toException();
		return new RoleGeneralInfo(dto);
	}

	private RoleDto findById() {
		RoleDao dao = Daos.getRoleDao();
		return dao.get(nickOrId);
	}

	private RoleDto findByNick() {

		RoleDao dao = Daos.getRoleDao();
		RoleDtoCursor findByNick = dao.findByNick(nickOrId);
		for (RoleDto dto : findByNick) {
			return dto;
		}
		return null;
	}

	public String getNickOrId() {
		return nickOrId;
	}

	public void setNickOrId(String nickOrId) {
		this.nickOrId = EncodingUtil.iso2Utf8(nickOrId);
	}
}
