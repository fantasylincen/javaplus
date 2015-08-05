package org.hhhhhh.fqzs.login;

import java.net.SocketException;
import java.util.ArrayList;

import org.hhhhhh.fqzs.config.GameConfig;
import org.hhhhhh.fqzs.core.App;
import org.hhhhhh.fqzs.result.RoleData;
import org.javaplus.game.common.http.HttpComponents.CallBackJson;
import org.javaplus.game.common.http.HttpComponents.CallBackJsonAdaptor;
import org.javaplus.game.common.http.JsonResult;
import org.javaplus.game.common.http.Parameters;
import org.javaplus.game.common.http.Request;
import org.javaplus.game.common.util.Lists;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class LoginManager {

	public class RoleItemImpl implements RoleItem {

		private String nick;
		private String id;

		public RoleItemImpl(String nick, String id) {
			this.nick = nick;
			this.id = id;
		}

		@Override
		public String getId() {
			return id;
		}

		@Override
		public String getNick() {
			return nick;
		}

	}

	public void login(String username, String password, LoginCallBack callBack) {

		if (username.isEmpty() || password.isEmpty()) {
			callBack.loginFaild("账号密码不能为空");
			return;
		}

		if (isEmail(username)) {
			Request request = new LoginByUsernameRequest(username, password);
			CallBackJson back = new LoginByUsernameCallBackJson(callBack);
			App.getHttp().request(request, back);
		} else {
			Request request = new LoginByIdRequest(username, password);
			CallBackJson back = new LoginByIdCallBackJson(callBack);
			App.getHttp().request(request, back);
		}
	}

	public void regist(String username, String password, RegistCallBack callBack) {

		if (username.isEmpty() || password.isEmpty()) {
			callBack.registFaild("账号密码不能为空");
			return;
		}

		Request request = new RegistRequest(username, password);
		CallBackJson back = new RegistCallBackJson(callBack);
		App.getHttp().request(request, back);
	}

	private final class LoginByIdRequest implements Request {
		private final String userId;
		private final String password;

		private LoginByIdRequest(String userId, String password) {
			this.userId = userId;
			this.password = password;
		}

		@Override
		public String getUrl() {
			String root = App.getProperties().get("gateUrl");
			return root + "account4games/loginById";
		}

		@Override
		public Parameters getParameters() {
			Parameters p = new Parameters();
			p.put("userId", userId);
			p.put("pwd", password);
			p.put("tokenKey", App.getConfig().getTokenKey());
			return p;
		}
	}

	public class LoginByIdCallBackJson extends CallBackJsonAdaptor {

		private LoginCallBack callBack;

		public LoginByIdCallBackJson(LoginCallBack callBack) {
			this.callBack = callBack;
		}

		@Override
		public void completed(JsonResult result) {
			String id = result.getString("id");
			String token = result.getString("token");
			callBack.loginSuccess(id, token);
		}

		@Override
		public void onTimeOut() {
			callBack.loginFaild("超时");
		}

		@Override
		public void failed(String error) {
			callBack.loginFaild(error);
		}

		@Override
		public void onNetError(SocketException ex) {
			callBack.loginFaild("网络错误");
		}

		@Override
		public void httpError(String error) {
			callBack.loginFaild("网络错误:" + error);
		}

		@Override
		public void jsonParseError(String result) {
			callBack.loginFaild("返回值异常:" + result);
		}

		@Override
		public void cancelled() {
			callBack.loginFaild("中断");
		}

	}

	private final class LoginByUsernameRequest implements Request {
		private final String username;
		private final String password;

		private LoginByUsernameRequest(String username, String password) {
			this.username = username;
			this.password = password;
		}

		@Override
		public String getUrl() {
			String root = App.getProperties().get("gateUrl");
			return root + "account4games/login";
		}

		@Override
		public Parameters getParameters() {
			Parameters p = new Parameters();
			p.put("username", username);
			p.put("password", password);
			p.put("tokenKey", App.getConfig().getTokenKey());
			return p;
		}
	}

	public class LoginByUsernameCallBackJson extends CallBackJsonAdaptor {

		private LoginCallBack callBack;

		public LoginByUsernameCallBackJson(LoginCallBack callBack) {
			this.callBack = callBack;
		}

		@Override
		public void completed(JsonResult result) {
			String id = result.getString("id");
			String token = result.getString("token");
			callBack.loginSuccess(id, token);
		}

		@Override
		public void onTimeOut() {
			callBack.loginFaild("超时");
		}

		@Override
		public void failed(String error) {
			callBack.loginFaild(error);
		}

		@Override
		public void onNetError(SocketException ex) {
			callBack.loginFaild("网络错误");
		}

		@Override
		public void httpError(String error) {
			callBack.loginFaild("网络错误:" + error);
		}

		@Override
		public void jsonParseError(String result) {
			callBack.loginFaild("返回值异常:" + result);
		}

		@Override
		public void cancelled() {
			callBack.loginFaild("中断");
		}

	}

	public class OneKeyRegistCallBackJson implements CallBackJson {

		private OneKeyRegistCallBack callBack;

		public OneKeyRegistCallBackJson(OneKeyRegistCallBack callBack) {
			this.callBack = callBack;
		}

		@Override
		public void completed(JsonResult result) {
			String id = result.getString("userId");
			String pwd = result.getString("pwd");
			callBack.registSuccess(id, pwd);
		}

		@Override
		public void onTimeOut() {
			callBack.registFaild("注册超时");
		}

		@Override
		public void failed(String error) {
			callBack.registFaild(error);
		}

		@Override
		public void onNetError(SocketException ex) {
			callBack.registFaild("网络错误");
		}

		@Override
		public void httpError(String error) {
			callBack.registFaild("网络错误:" + error);
		}

		@Override
		public void jsonParseError(String result) {
			callBack.registFaild("返回值异常:" + result);
		}

		@Override
		public void cancelled() {
			callBack.registFaild("中断");
		}

	}

	public class OneKeyRegistRequest implements Request {

		@Override
		public String getUrl() {
			String root = App.getProperties().get("gateUrl");
			return root + "account4games/oneKeyRegist";
		}

		@Override
		public Parameters getParameters() {
			return new Parameters();
		}

	}

	public class RegistCallBackJson implements CallBackJson {

		private RegistCallBack callBack;

		public RegistCallBackJson(RegistCallBack callBack) {
			this.callBack = callBack;
		}

		@Override
		public void completed(JsonResult result) {
		}

		@Override
		public void onTimeOut() {
			callBack.registFaild("注册超时");
		}

		@Override
		public void failed(String error) {
			callBack.registFaild(error);
		}

		@Override
		public void onNetError(SocketException ex) {
			callBack.registFaild("网络错误");
		}

		@Override
		public void httpError(String error) {
			callBack.registFaild("网络错误:" + error);
		}

		@Override
		public void jsonParseError(String result) {
			callBack.registFaild("返回值异常:" + result);
		}

		@Override
		public void cancelled() {
			callBack.registFaild("中断");
		}

	}

	public class RegistRequest implements Request {
		private final String username;
		private final String password;

		private RegistRequest(String username, String password) {
			this.username = username;
			this.password = password;
		}

		@Override
		public String getUrl() {
			String root = App.getProperties().get("gateUrl");
			return root + "account4games/regist";
		}

		@Override
		public Parameters getParameters() {
			Parameters p = new Parameters();
			p.put("username", username);
			p.put("password", password);
			return p;
		}
	}

	public class SelectRoleCallBackJson implements CallBackJson {

		private SelectRoleCallBack callBack;

		public SelectRoleCallBackJson(SelectRoleCallBack callBack) {
			this.callBack = callBack;
		}

		@Override
		public void completed(JsonResult result) {

			JSONObject obj = result.toJsonObject();
			JSONObject r = obj.getJSONObject("role");

			RoleData roleData = JSON.parseObject(r.toJSONString(), RoleData.class);
			callBack.onSuccess(roleData);
		}

		@Override
		public void onTimeOut() {
			callBack.faild("选定角色超时");
		}

		@Override
		public void failed(String error) {
			callBack.faild(error);
		}

		@Override
		public void onNetError(SocketException ex) {
			callBack.faild("网络错误");
		}

		@Override
		public void httpError(String error) {
			callBack.faild("网络错误:" + error);
		}

		@Override
		public void jsonParseError(String result) {
			callBack.faild("返回值异常:" + result);
		}

		@Override
		public void cancelled() {
			callBack.faild("中断");
		}

	}

	public class SelectRoleRequest implements Request {

		private String selectedRoleId;

		public SelectRoleRequest(String selectedRoleId) {
			this.selectedRoleId = selectedRoleId;
		}

		@Override
		public String getUrl() {
			String path = "account/selectRole";
			return buildFullPath(path);
		}

		private String buildFullPath(String path) {
			GameConfig config = App.getConfig();
			String rootPath = config.getRootPath();
			String url = rootPath + path;
			return url;
		}

		@Override
		public Parameters getParameters() {
			Parameters p = new Parameters();
			p.put("roleId", selectedRoleId);
			return p;
		}

	}

	public class CreateRoleCallBackJson implements CallBackJson {

		private CreateRoleCallBack callBack;

		public CreateRoleCallBackJson(CreateRoleCallBack callBack) {
			this.callBack = callBack;
		}

		@Override
		public void completed(JsonResult result) {
			JSONObject obj = result.toJsonObject();
			JSONObject role = obj.getJSONObject("role");
			String n = role.getString("nick");
			String roleId = role.getString("id");
			RoleItemImpl r = new RoleItemImpl(n, roleId);
			callBack.onSuccess(r);
		}

		@Override
		public void onTimeOut() {
			callBack.faild("创建角色超时");
		}

		@Override
		public void failed(String error) {
			callBack.faild(error);
		}

		@Override
		public void onNetError(SocketException ex) {
			callBack.faild("网络错误");
		}

		@Override
		public void httpError(String error) {
			callBack.faild("网络错误:" + error);
		}

		@Override
		public void jsonParseError(String result) {
			callBack.faild("返回值异常:" + result);
		}

		@Override
		public void cancelled() {
			callBack.faild("中断");
		}

	}

	public class CreateRoleRequest implements Request {

		private String newNick;

		public CreateRoleRequest(String newNick) {
			this.newNick = newNick;
		}

		@Override
		public String getUrl() {
			GameConfig config = App.getConfig();
			String rootPath = config.getRootPath();
			String url = rootPath + "account/createRole";
			return url;
		}

		@Override
		public Parameters getParameters() {
			Parameters p = new Parameters();
			p.put("nick", newNick);
			return p;
		}

	}

	public class GetRoleListCallBackJson implements CallBackJson {

		private RoleListCallBack callBack;

		public GetRoleListCallBackJson(RoleListCallBack callBack) {
			this.callBack = callBack;
		}

		@Override
		public void completed(JsonResult result) {
			JSONObject oo = result.toJsonObject();
			JSONArray roles = oo.getJSONArray("roles");

			ArrayList<RoleItem> ls = Lists.newArrayList();
			for (Object object : roles) {
				JSONObject obj = (JSONObject) object;
				ls.add(parseRoleItem(obj));
			}
			callBack.onGetRoleList(ls);
		}

		private RoleItem parseRoleItem(JSONObject obj) {
			String nick = obj.getString("nick");
			String id = obj.getString("id");
			return new RoleItemImpl(nick, id);
		}

		@Override
		public void onTimeOut() {
			callBack.faild("获取角色列表超时");
		}

		@Override
		public void failed(String error) {
			callBack.faild(error);
		}

		@Override
		public void onNetError(SocketException ex) {
			callBack.faild("网络错误");
		}

		@Override
		public void httpError(String error) {
			callBack.faild("网络错误:" + error);
		}

		@Override
		public void jsonParseError(String result) {
			callBack.faild("返回值异常:" + result);
		}

		@Override
		public void cancelled() {
			callBack.faild("中断");
		}

	}

	public class GetRoleListRequest implements Request {

		@Override
		public String getUrl() {
			GameConfig config = App.getConfig();
			String rootPath = config.getRootPath();
			String url = rootPath + "account/getRoleList2";
			return url;
		}

		@Override
		public Parameters getParameters() {
			return new Parameters();
		}

	}

	public class EnterServerRequest implements Request {

		private String token;
		private String id;

		public EnterServerRequest(String token, String id) {
			this.token = token;
			this.id = id;
		}

		@Override
		public String getUrl() {
			GameConfig config = App.getConfig();
			String rootPath = config.getRootPath();
			String url = rootPath + "account/enterServer";
			return url;
		}

		@Override
		public Parameters getParameters() {
			Parameters p = new Parameters();
			p.put("token", token);
			p.put("userId", id);
			p.put("zoneId", App.getConfig().getZoneId());
			return p;
		}

	}

	public class EnterServerCallBackJson implements CallBackJson {

		private EnterServerCallBack callBack;

		public EnterServerCallBackJson(EnterServerCallBack callBack) {
			this.callBack = callBack;
		}

		@Override
		public void completed(JsonResult result) {
			String sessionId = result.getString("sessionId");
			String id = result.getString("id");
			App.getUserData().setJsessionid(sessionId);
			App.getUserData().setUserId(id);
			callBack.onSuccess();
		}


		@Override
		public void onTimeOut() {
			callBack.faild("进入服务器超时");
		}

		@Override
		public void failed(String error) {
			callBack.faild(error);
		}

		@Override
		public void onNetError(SocketException ex) {
			callBack.faild("网络错误");
		}

		@Override
		public void httpError(String error) {
			callBack.faild("网络错误:" + error);
		}

		@Override
		public void jsonParseError(String result) {
			callBack.faild("返回值异常:" + result);
		}

		@Override
		public void cancelled() {
			callBack.faild("中断");
		}

	}

	private boolean isEmail(String username) {
		return username.contains("@");
	}

	public void oneKeyRegist(OneKeyRegistCallBack callBack) {
		Request request = new OneKeyRegistRequest();
		CallBackJson back = new OneKeyRegistCallBackJson(callBack);
		App.getHttp().request(request, back);
	}

	public void requestRoleList(RoleListCallBack callBack) {
		CallBackJson back = new GetRoleListCallBackJson(callBack);
		App.getHttp().request(new GetRoleListRequest(), back);
	}

	public void enterServer(String token, String id, EnterServerCallBack callBack) {
		CallBackJson back = new EnterServerCallBackJson(callBack);
		Request request = new EnterServerRequest(token, id);
		App.getHttp().request(request, back);		
	}

	public void createRole(String nick, CreateRoleCallBack callBack) {
		CallBackJson back = new CreateRoleCallBackJson(callBack);
		Request request = new CreateRoleRequest(nick);
		App.getHttp().request(request, back);
	}

	public void selectRoleAndEnterGame(String selectedRoleId, SelectRoleCallBack callBack) {
		CallBackJson back = new SelectRoleCallBackJson(callBack);
		Request request = new SelectRoleRequest(selectedRoleId);
		App.getHttp().request(request, back);
	}

}
