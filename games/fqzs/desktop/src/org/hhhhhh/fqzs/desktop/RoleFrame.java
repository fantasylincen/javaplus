package org.hhhhhh.fqzs.desktop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.SocketException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.hhhhhh.fqzs.config.GameConfig;
import org.hhhhhh.fqzs.core.App;
import org.hhhhhh.fqzs.login.RoleSelectedEvent;
import org.hhhhhh.fqzs.result.RoleData;
import org.javaplus.game.common.http.HttpComponents.CallBackJson;
import org.javaplus.game.common.http.JsonResult;
import org.javaplus.game.common.http.Parameters;
import org.javaplus.game.common.http.Request;
import org.javaplus.game.common.log.Log;
import org.javaplus.game.common.util.Lists;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class RoleFrame extends JFrame {

	public class SelectRoleCallBack implements CallBackJson {

		@Override
		public void completed(JsonResult result) {
			setStatus("选定角色成功:" + result);
			
			JSONObject obj = result.toJsonObject();
			JSONObject r = obj.getJSONObject("role");
			
			RoleData roleData = JSON.parseObject(r.toJSONString(), RoleData.class);
			
			App.setRoleData(roleData);
			
			dispatchEvent(new RoleSelectedEvent(roleData));
		}

		private void dispatchEvent(RoleSelectedEvent e) {
			for (RoleFrameListener l : listeners) {
				l.onRoleSelectedSuccess(e);
			}
		}

		@Override
		public void onTimeOut() {
			setStatus("选定角色超时");
		}

		@Override
		public void failed(String error) {
			setStatus(error);
		}

		@Override
		public void onNetError(SocketException ex) {
			setStatus("网络错误");
		}

		@Override
		public void httpError(String error) {
			setStatus("网络错误:" + error);
		}

		@Override
		public void jsonParseError(String result) {
			setStatus("返回值异常:" + result);
		}

		@Override
		public void cancelled() {
			setStatus("中断");
		}

	}

	public class SelectRoleRequest implements Request {

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
			p.put("roleId", App.getUserData().getRoleId());
			return p;
		}

	}

	public class CreateRoleCallBack implements CallBackJson {

		@Override
		public void completed(JsonResult result) {
//			result.getString(key)
			JSONObject obj = result.toJsonObject();
			JSONObject role = obj.getJSONObject("role");
			String n = role.getString("nick");
			String roleId = role.getString("id");
			App.getUserData().setRoleId(roleId);
			nick.setText(n);
			
			createRolePanel.setVisible(false);
			selectRolePanel.setVisible(true);
		}

		@Override
		public void onTimeOut() {
			setStatus("创建角色超时");
		}

		@Override
		public void failed(String error) {
			setStatus(error);
		}

		@Override
		public void onNetError(SocketException ex) {
			setStatus("网络错误");
		}

		@Override
		public void httpError(String error) {
			setStatus("网络错误:" + error);
		}

		@Override
		public void jsonParseError(String result) {
			setStatus("返回值异常:" + result);
		}

		@Override
		public void cancelled() {
			setStatus("中断");
		}

	}

	public class CreateRoleRequest implements Request {

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
			String text = newNick.getText();
			p.put("nick", text.trim());
			return p;
		}

	}

	public class GetRoleListCallBack implements CallBackJson {

		@Override
		public void completed(JsonResult result) {
			setStatus("获取角色列表成功:" + result);
			JSONObject obj = result.toJsonObject();
			JSONArray roles = obj.getJSONArray("roles");
			if (roles.isEmpty())
				createRolePanel.setVisible(true);
			else {
				selectRolePanel.setVisible(true);
				String nick = getNick(roles);
				String id = getId(roles);
				App.getUserData().setRoleId(id);
				
				RoleFrame.this.nick.setText(nick);
			}
		}

		private String getId(JSONArray roles) {
			JSONObject o = (JSONObject) roles.get(0);
			return o.getString("id");
		
		}

		private String getNick(JSONArray roles) {
			JSONObject o = (JSONObject) roles.get(0);
			return o.getString("nick");
		}

		@Override
		public void onTimeOut() {
			setStatus("获取角色列表超时");
		}

		@Override
		public void failed(String error) {
			setStatus(error);
		}

		@Override
		public void onNetError(SocketException ex) {
			setStatus("网络错误");
		}

		@Override
		public void httpError(String error) {
			setStatus("网络错误:" + error);
		}

		@Override
		public void jsonParseError(String result) {
			setStatus("返回值异常:" + result);
		}

		@Override
		public void cancelled() {
			setStatus("中断");
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

	/**
	 * 
	 */
	private static final long serialVersionUID = 6181652666548268217L;

	public class EnterServerCallBack implements CallBackJson {

		@Override
		public void completed(JsonResult result) {
			String sessionId = result.getString("sessionId");
			String id = result.getString("id");
			App.getUserData().setJsessionid(sessionId);
			App.getUserData().setUserId(id);
			setStatus("进入服务器成功:" + result);

			requestRoleList();
		}

		private void setStatus(String string) {
			Log.d(string);
			status.setText(string);
		}

		@Override
		public void onTimeOut() {
			setStatus("进入服务器超时");
		}

		@Override
		public void failed(String error) {
			setStatus(error);
		}

		@Override
		public void onNetError(SocketException ex) {
			setStatus("网络错误");
		}

		@Override
		public void httpError(String error) {
			setStatus("网络错误:" + error);
		}

		@Override
		public void jsonParseError(String result) {
			setStatus("返回值异常:" + result);
		}

		@Override
		public void cancelled() {
			setStatus("中断");
		}

	}

	private JPanel contentPane;
	private List<RoleFrameListener> listeners = Lists.newArrayList();
	private JTextField newNick;
	private JButton enterGameButton;
	private JPanel createRolePanel;
	private JPanel selectRolePanel;
	private JLabel nick;
	private String token;
	private String id;
	private JLabel label_2;
	private JLabel status;

	private void setStatus(String string) {
		Log.d(string);
		status.setText(string);
	}

	public void addListener(RoleFrameListener l) {
		this.listeners.add(l);
	}

	/**
	 * Create the frame.
	 * 
	 * @param token
	 * @param id
	 */
	public RoleFrame(String id, String token) {
		this.id = id;
		this.token = token;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		selectRolePanel = new JPanel();
		selectRolePanel.setBounds(52, 24, 325, 195);
		contentPane.add(selectRolePanel);
		selectRolePanel.setLayout(null);
		selectRolePanel.setVisible(false);

		JLabel label_1 = new JLabel("您的角色是:");
		label_1.setBounds(83, 76, 66, 15);
		selectRolePanel.add(label_1);

		nick = new JLabel("xx");
		nick.setBounds(163, 76, 54, 15);
		selectRolePanel.add(nick);

		enterGameButton = new JButton("进入游戏");
		enterGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectRoleAndEnterGame();
			}
		});
		enterGameButton.setBounds(122, 127, 95, 25);
		selectRolePanel.add(enterGameButton);

		createRolePanel = new JPanel();
		createRolePanel.setBounds(52, 37, 325, 177);
		contentPane.add(createRolePanel);
		createRolePanel.setLayout(null);

		createRolePanel.setVisible(false);

		JLabel label = new JLabel("请输入角色昵称:");
		label.setBounds(61, 75, 95, 15);
		createRolePanel.add(label);

		newNick = new JTextField();
		newNick.setBounds(166, 72, 66, 21);
		createRolePanel.add(newNick);
		newNick.setColumns(10);

		JButton createButton = new JButton("创建角色");
		createButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (nick.getText().isEmpty()) {
					setStatus("昵称不能为空");
					return;
				}
				createRole();
			}
		});
		createButton.setBounds(112, 103, 95, 25);
		createRolePanel.add(createButton);

		label_2 = new JLabel("状态:");
		label_2.setBounds(10, 248, 54, 15);
		contentPane.add(label_2);

		status = new JLabel("正常");
		status.setBounds(52, 248, 380, 15);
		contentPane.add(status);

		enterServer();
	}

	protected void selectRoleAndEnterGame() {
		CallBackJson back = new SelectRoleCallBack();
		Request request = new SelectRoleRequest();
		App.getHttp().request(request, back);
	}

	protected void createRole() {
		CallBackJson back = new CreateRoleCallBack();
		Request request = new CreateRoleRequest();
		App.getHttp().request(request, back);
	}

	private void enterServer() {
		CallBackJson back = new EnterServerCallBack();
		Request request = new EnterServerRequest();
		App.getHttp().request(request, back);
	}

	private void requestRoleList() {
		CallBackJson back = new GetRoleListCallBack();
		App.getHttp().request(new GetRoleListRequest(), back);
	}
}
