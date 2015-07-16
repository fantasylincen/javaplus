package cn.vgame.a.yinshang;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.vgame.a.gen.dto.MongoGen.Daos;
import cn.vgame.a.gen.dto.MongoGen.YinShangDao;
import cn.vgame.a.gen.dto.MongoGen.YinShangDto;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {

	private static final long serialVersionUID = -7216556879198306440L;

	private String id;
	String password;

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
		if(dto == null)
			throw new YinShangException("用户不存在");

		if(!dto.getPassword().equals(password)) {
			throw new YinShangException("密码错误");
		}

		session.setAttribute("id", dto.getId());
		session.setAttribute("roleId", dto.getRoleId());
		
		return SUCCESS;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
