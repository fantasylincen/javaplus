package org.hhhhhh.fqzs.desktop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.hhhhhh.fqzs.core.App;
import org.hhhhhh.fqzs.login.LoginCallBack;
import org.hhhhhh.fqzs.login.LoginEvent;
import org.hhhhhh.fqzs.login.OneKeyRegistCallBack;
import org.hhhhhh.fqzs.login.RegistCallBack;
import org.javaplus.game.common.util.Lists;

public class LoginFrame extends JFrame {

	public class OneKeyRegistCallBackImpl implements OneKeyRegistCallBack {

		@Override
		public void registFaild(String message) {
			status.setText(message);
		}
		
		@Override
		public void registSuccess(String id, String pwd) {
			loginUsername.setText(id);
			loginPassword.setText(pwd);
			status.setText("一键注册成功");
		}

	}

	public class RegistCallBackImpl implements RegistCallBack {

		@Override
		public void registFaild(String message) {
			status.setText(message);
		}
		
		@Override
		public void registSuccess() {
			status.setText("注册成功");
		}

	}

	public class LoginCallBackImpl implements LoginCallBack {

		@Override
		public void loginSuccess(String id, String token) {
			for (LoginFrameListener l : listeners) {
				LoginEvent e = new LoginEvent(id, token);
				l.onLoginSuccess(e);
			}
			status.setText("登录成功");
		}
		@Override
		public void loginFaild(String message) {
			status.setText(message);
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

	private JButton oneKeyRegistButton;



	private void regist() {
		final String username = registUsername.getText();
		final String password = registPassword.getText();
		
		RegistCallBack callBack = new RegistCallBackImpl();
		
		App.getLoginManager().regist(username, password, callBack);		
	}
	
	void login() {
		final String username = loginUsername.getText();
		final String password = loginPassword.getText();
		LoginCallBack callBack = new LoginCallBackImpl();
		App.getLoginManager().login(username, password, callBack);		
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
				regist();

			}

		});
		registButton.setBounds(307, 105, 95, 25);
		contentPane.add(registButton);

		oneKeyRegistButton = new JButton("一键注册");
		oneKeyRegistButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				oneKeyRegist();
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

		new AutoLoginThread(this).start();
	}

	protected void oneKeyRegist() {
		oneKeyRegistButton.setEnabled(false);
		OneKeyRegistCallBack callBack = new OneKeyRegistCallBackImpl();
		App.getLoginManager().oneKeyRegist(callBack);		
	}

	public void addListener(LoginFrameListener l) {
		this.listeners.add(l);
	}
}
