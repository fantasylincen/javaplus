package org.hhhhhh.fqzs.desktop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.hhhhhh.fqzs.core.App;
import org.hhhhhh.fqzs.login.CreateRoleCallBack;
import org.hhhhhh.fqzs.login.EnterServerCallBack;
import org.hhhhhh.fqzs.login.RoleItem;
import org.hhhhhh.fqzs.login.RoleListCallBack;
import org.hhhhhh.fqzs.login.RoleSelectedEvent;
import org.hhhhhh.fqzs.login.SelectRoleCallBack;
import org.hhhhhh.fqzs.result.RoleData;
import org.javaplus.game.common.log.Log;
import org.javaplus.game.common.util.Lists;

public class RoleFrame extends JFrame {

	public class SelectRoleCallBackImpl implements SelectRoleCallBack {

		@Override
		public void onSuccess(RoleData role) {

			App.setRoleData(role);

			dispatchEvent(new RoleSelectedEvent(role));
			
			RoleFrame.this.selectedRoleId = role.getId();
			nick.setText(role.getNick());

			createRolePanel.setVisible(false);
			selectRolePanel.setVisible(true);
			
			setStatus("选定角色成功:" + role);
		}

		@Override
		public void faild(String message) {
			setStatus(message);
		}
	}
	
	private void dispatchEvent(RoleSelectedEvent e) {
		for (RoleFrameListener l : listeners) {
			l.onRoleSelectedSuccess(e);
		}
	}


	public class CreateRoleCallBackImpl implements CreateRoleCallBack {

		@Override
		public void onSuccess(RoleItem role) {
			App.getUserData().setRoleId(role.getId());
			
			
			RoleFrame.this.selectedRoleId = role.getId();
			nick.setText(role.getNick());

			createRolePanel.setVisible(false);
			selectRolePanel.setVisible(true);
			
			setStatus("创建角色成功");
		}

		@Override
		public void faild(String message) {
			setStatus(message);
		}
	}

	public class EnterServerCallBackImpl implements EnterServerCallBack {

		@Override
		public void onSuccess() {
			requestRoleList();
			setStatus("进入服务器成功");
		}

		@Override
		public void faild(String message) {
			setStatus(message);
		}
	}

	private String selectedRoleId;

	public class RoleListCallBackImpl implements RoleListCallBack {

		@Override
		public void onGetRoleList(List<RoleItem> roles) {

			if (roles.isEmpty())
				createRolePanel.setVisible(true);
			else {
				selectRolePanel.setVisible(true);
				RoleItem role = roles.get(0);
				String nick = role.getNick();
				String id = role.getId();
				RoleFrame.this.selectedRoleId = id;
				RoleFrame.this.nick.setText(nick);
			}
			setStatus("获取角色列表成功:" + roles);
		}

		@Override
		public void faild(String message) {
			setStatus(message);
		}
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 6181652666548268217L;

	private JPanel contentPane;
	private List<RoleFrameListener> listeners = Lists.newArrayList();
	private JTextField newNick;
	private JButton enterGameButton;
	private JPanel createRolePanel;
	private JPanel selectRolePanel;
	private JLabel nick;
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

		enterServer(token, id);

		new AutoEnterThread(this).start();
	}

	protected void selectRoleAndEnterGame() {
		SelectRoleCallBack callBack = new SelectRoleCallBackImpl();
		App.getLoginManager().selectRoleAndEnterGame(selectedRoleId, callBack);
	}

	protected void createRole() {
		CreateRoleCallBack callBack = new CreateRoleCallBackImpl();
		App.getLoginManager().createRole(newNick.getText().trim(), callBack);
	}

	private void enterServer(String token, String id) {
		EnterServerCallBack callBack = new EnterServerCallBackImpl();
		App.getLoginManager().enterServer(token, id, callBack);
	}

	private void requestRoleList() {
		RoleListCallBack callBack = new RoleListCallBackImpl();
		App.getLoginManager().requestRoleList(callBack);
	}
}
