package com.cnbizmedia.account4games;

import cn.javaplus.util.Util;

import com.cnbizmedia.JsonAction;
import com.cnbizmedia.Server;
import com.cnbizmedia.error.ErrorResult;
import com.cnbizmedia.gen.dto.MongoGen.Daos;
import com.cnbizmedia.gen.dto.MongoGen.UserDto;
import com.cnbizmedia.user.User;

/**
 * 绑定邮箱
 */
public class BindEmailAction extends JsonAction {

	public class BindEmailResult {
		private final User user;

		public BindEmailResult(User user) {
			this.user = user;
		}

		public String getEmail() {
			return user.getEmail();
		}
		
		public String getPassword() {
			return password;
		}
	}

	private static final long serialVersionUID = -3677888535102593502L;
	private String email;
	private String password;

	@Override
	protected Object exec() {

		User user = getUser();
		if (user == null)
			return new ErrorResult("please login");

		if (isAreadyBindEmail(user))
			return new ErrorResult("aready bind email");

		if (!Util.Str.isEmail(getEmail()))
			return new ErrorResult("email format error");

		
		if (getPassword() == null) {
			return new ErrorResult("please type password");
		} else if (getPassword().length() < 6) {
			return new ErrorResult("password lenth < 6");
		}
		
		
		bindEmailAndPassword(user);

		return new BindEmailResult(user);
	}

	private void bindEmailAndPassword(User user) {
		UserDto dto = user.getDto();
		dto.setEmail(getEmail());
		dto.setPwdMD5(Util.Secure.md5(getPassword()));
		Daos.getUserDao().save(dto);
	}

	private User getUser() {
		User user = Server
				.loadUserById((String) session.getAttribute("userId"));
		return user;
	}

	private boolean isAreadyBindEmail(User user) {

		String email = user.getEmail();
		return email != null && !email.isEmpty();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
