package cn.vgame.a.gm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.vgame.a.Server;
import cn.vgame.a.account.Role;
import cn.vgame.a.robot.Robot;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 重新加载系统配置(刘雨诚请无视) ----------------- 无
 */
public class SetRobotAction extends ActionSupport {

	private static final long serialVersionUID = -6099859675509539457L;
	
	private boolean isRobot;
	private String roleId;


	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf8");

		HttpSession session = request.getSession();

		Role role = Server.getRole(getRoleId());

		role.setRobot(isRobot);
		
		if(isRobot) {
			Server.getRobotManager().add(new Robot(getRoleId()));
		} else {
			Server.getRobotManager().remove(role.getId());
		}

		return super.execute();
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public boolean isRobot() {
		return isRobot;
	}

	public void setRobot(boolean isRobot) {
		this.isRobot = isRobot;
	}

}
