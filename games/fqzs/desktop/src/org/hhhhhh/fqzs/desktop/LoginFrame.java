package org.hhhhhh.fqzs.desktop;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.SocketException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.hhhhhh.fqzs.core.App;
import org.hhhhhh.fqzs.desktop.LoginFrame.AutoLoginThread;
import org.hhhhhh.fqzs.login.LoginEvent;
import org.javaplus.game.common.http.HttpComponents.CallBackJson;
import org.javaplus.game.common.http.HttpComponents.CallBackJsonAdaptor;
import org.javaplus.game.common.http.JsonResult;
import org.javaplus.game.common.http.Parameters;
import org.javaplus.game.common.http.Request;
import org.javaplus.game.common.log.Log;
import org.javaplus.game.common.util.Lists;
import org.javaplus.game.common.util.Util;

public class LoginFrame extends JFrame {

	public class AutoLoginThread extends Thread {
		@Override
		public void run() {
			Util.Thread.sleep(500);
			login();
		}
	}

	public class OneKeyRegistCallBack implements CallBackJson {

		@Override
		public void completed(JsonResult result) {
			String id = result.getString("userId");
			String pwd = result.getString("pwd");
			// if(pwd == null) {
			// pwd = result.getString("password");
			// }
			loginUsername.setText(id);
			loginPassword.setText(pwd);
			setStatus("注册成功:" + result);
		}

		private void setStatus(String string) {
			Log.d(string);
			status.setText(string);
		}

		@Override
		public void onTimeOut() {
			setStatus("注册超时");
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

	public class RegistCallBack implements CallBackJson {

		@Override
		public void completed(JsonResult result) {
			setStatus("注册成功:" + result);
		}

		private void setStatus(String string) {
			Log.d(string);
			status.setText(string);
		}

		@Override
		public void onTimeOut() {
			setStatus("注册超时");
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

	public class LoginByIdCallBack extends CallBackJsonAdaptor {

		@Override
		public void completed(JsonResult result) {
			setStatus("登录成功:" + result);
			String id = result.getString("id");
			String token = result.getString("token");
			loginSuccess(id, token);
		}

		private void setStatus(String string) {
			Log.d(string);
			status.setText(string);
		}

		@Override
		public void onTimeOut() {
			setStatus("登录超时");
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

	public class LoginByUsernameCallBack extends CallBackJsonAdaptor {

		@Override
		public void completed(JsonResult result) {
			setStatus("登录成功:" + result);
			String id = result.getString("id");
			String token = result.getString("token");
			loginSuccess(id, token);
		}

		private void setStatus(String string) {
			Log.d(string);
			status.setText(string);
		}

		@Override
		public void onTimeOut() {
			setStatus("登录超时");
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

	/**
	 * 
	 */
	private static final long serialVersionUID = 8658674656803178530L;

	private JPanel contentPane;
	private JTextField loginUsername;
	private JTextField loginPassword;
	private JTextField registUsername;
	private JTextField registPassword;
	private List<LoginFrameListener> listeners = Lists.newArrayList();
	private JLabel status;


	private boolean isEmail(String username) {
		return username.contains("@");
	}
	private void login() {
		final String username = loginUsername.getText();
		final String password = loginPassword.getText();

		if (username.isEmpty() || password.isEmpty()) {
			Log.d("账号密码不能为空");
			return;
		}

		if (isEmail(username)) {
			Request request = new LoginByUsernameRequest(username, password);
			CallBackJson back = new LoginByUsernameCallBack();
			App.getHttp().request(request, back);
		} else {
			Request request = new LoginByIdRequest(username, password);
			CallBackJson back = new LoginByIdCallBack();
			App.getHttp().request(request, back);
		}
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void loginSuccess(String id, String token) {
		for (LoginFrameListener l : listeners) {
			LoginEvent e = new LoginEvent(id, token);
			l.onLoginSuccess(e);
		}
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 218);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		loginUsername = new JTextField("574907580@qq.com");
		loginUsername.setBounds(55, 24, 134, 21);
		contentPane.add(loginUsername);
		loginUsername.setColumns(10);

		JLabel label_1 = new JLabel("用户名");
		label_1.setBounds(10, 27, 54, 15);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("密码");
		label_2.setBounds(10, 59, 54, 15);
		contentPane.add(label_2);

		loginPassword = new JTextField("111111");
		loginPassword.setBounds(55, 56, 134, 21);
		contentPane.add(loginPassword);
		loginPassword.setColumns(10);

		JButton loginButton = new JButton("登录");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();

			}

		});
		loginButton.setBounds(94, 105, 95, 25);
		contentPane.add(loginButton);

		registUsername = new JTextField();
		registUsername.setBounds(268, 24, 134, 21);
		contentPane.add(registUsername);
		registUsername.setColumns(10);

		registPassword = new JTextField();
		registPassword.setBounds(268, 56, 134, 21);
		contentPane.add(registPassword);
		registPassword.setColumns(10);

		JLabel label = new JLabel("用户名");
		label.setBounds(211, 27, 54, 15);
		contentPane.add(label);

		JLabel label_3 = new JLabel("密码");
		label_3.setBounds(211, 59, 54, 15);
		contentPane.add(label_3);

		JButton registButton = new JButton("注册");
		registButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final String username = registUsername.getText();
				final String password = registPassword.getText();

				if (username.isEmpty() || password.isEmpty()) {
					Log.d("账号密码不能为空");
					return;
				}

				Request request = new RegistRequest(username, password);
				CallBackJson back = new RegistCallBack();
				App.getHttp().request(request, back);

			}
		});
		registButton.setBounds(307, 105, 95, 25);
		contentPane.add(registButton);

		final JButton oneKeyRegistButton = new JButton("一键注册");
		oneKeyRegistButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Request request = new OneKeyRegistRequest();
				CallBackJson back = new OneKeyRegistCallBack();
				App.getHttp().request(request, back);
				oneKeyRegistButton.setEnabled(false);
			}
		});
		oneKeyRegistButton.setBounds(307, 140, 95, 25);
		contentPane.add(oneKeyRegistButton);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(410, 167, -398, 156);
		contentPane.add(textArea);

		status = new JLabel("正常");
		status.setBounds(40, 166, 392, 15);
		contentPane.add(status);

		JLabel label_4 = new JLabel("状态:");
		label_4.setBounds(10, 166, 54, 15);
		contentPane.add(label_4);
		
		new AutoLoginThread().start();
	}

	public void addListener(LoginFrameListener l) {
		this.listeners.add(l);
	}
}
