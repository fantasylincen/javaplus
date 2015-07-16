package cn.vgame.a.yinshang;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.vgame.a.gen.dto.MongoGen.Daos;
import cn.vgame.a.gen.dto.MongoGen.YinShangDao;
import cn.vgame.a.gen.dto.MongoGen.YinShangDto;

import com.opensymphony.xwork2.ActionSupport;

public class AddYinShangAction extends ActionSupport {

	private static final long serialVersionUID = -7216556879198306440L;

	String id;
	String roleId;
	String password;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	private HttpSession session;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		session = request.getSession();

		YinShangDao dao = Daos.getYinShangDao();

		YinShangDto dto = dao.get(id);
		if (dto != null)
			return "error";
		dto = dao.createDTO();
		dto.setId(id);
		dto.setPassword(password);
		dto.setRoleId(roleId);

		dao.save(dto);
		return SUCCESS;
	}

}
