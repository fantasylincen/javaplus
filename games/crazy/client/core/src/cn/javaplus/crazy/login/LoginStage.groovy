package cn.javaplus.crazy.login;

import cn.javaplus.crazy.D
import cn.javaplus.crazy.R
import cn.javaplus.crazy.App.AppContext
import cn.javaplus.crazy.R.CocosUI
import cn.javaplus.crazy.R.Uis.LoginUI
import cn.javaplus.crazy.R.Uis.LoginUI.Panel
import cn.javaplus.crazy.events.Events
import cn.javaplus.crazy.http.JsonResult
import cn.javaplus.crazy.log.Log
import cn.javaplus.crazy.main.ButtonListener
import cn.javaplus.crazy.stage.AbstractStage

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Preferences
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.TextField
import com.badlogic.gdx.utils.Scaling
import com.badlogic.gdx.utils.viewport.ScalingViewport

public class LoginStage extends AbstractStage {

	public class MessageBoxImpl implements MessageBox {

		@Override
		public void showMessage(String text) {
			Log.d(text);
			Panel p = loginUi.getPanel();
			Label messagebox = p.getMessagebox();
			messagebox.setText(text);
		}
	}

	private LoginUI loginUi;
	private MessageBox messageBox;

	public class RegistListener extends ButtonListener {

		@Override
		protected void onClick() {
			getRandomUserName();
		}
	}

	@Override
	public void onLoadingOver() {

		Panel p = loginUi.getPanel();
		addActor(p.toGroup());

		p.getNametextfield().setText(getPreference("uname"));
		p.getPasswordtextfield().setText(getPreference("password"));

		p.getLoginbutton().addListener(new LoginListener());
		p.getRegistbutton().addListener(new RegistListener());
		getRandomUserName();

		messageBox = new MessageBoxImpl();
	}

	void getRandomUserName() {
		Panel p = loginUi.getPanel();
		p.getMessagebox().setText("please wait...");
		OneKeyRegistRequest r = new OneKeyRegistRequest();
		AppContext.getHttpClient().excute(r, new OneKeyRegistCallBack(this));
	}

	private final class LoginListener extends ButtonListener {

		@Override
		protected void onClick() {
			login();
			save();
		}
	}

	class OneKeyRegistCallBack extends AbstractCallBack {

		LoginStage stage;

		public OneKeyRegistCallBack(LoginStage stage) {
			this.stage = stage;
		}

		@Override
		public void completed(JsonResult result) {
			messageBox.showMessage("regist success");
			String uname = result.getString("uname");
			String password = result.getString("password");
			stage.setPassword(password);
			stage.setUname(uname);
			login();
		}
	}

	private void login() {
		Panel p = loginUi.getPanel();
		Label box = p.getMessagebox();
		box.setText("please wait...");
		LoginRequest r = new LoginRequest(getUname(), getPassword());
		AppContext.getHttpClient().excute(r, new LoginCallBack());
	}

	class LoginCallBack extends AbstractCallBack {

		@Override
		public void completed(JsonResult result) {
			String token = result.getString("token");
			getMessageBox().showMessage("login success");
			String roleData = result.getString("roleData");
			Events.dispatch(new LoginSuccessEvent(LoginStage.this, token,
					getUname(), roleData));
		}
	}

	public LoginStage() {
		super(new ScalingViewport(Scaling.stretch, D.STAGE_W, D.STAGE_H,
		new OrthographicCamera()), null);
	}

	public String getPassword() {
		Panel p = loginUi.getPanel();
		TextField ff = p.getPasswordtextfield();
		return ff.getText();
	}

	public String getUname() {
		Panel p = loginUi.getPanel();
		TextField ff = p.getNametextfield();
		return ff.getText().toLowerCase();
	}

	private String getPreference(String key) {
		Preferences prefs = getPerference();
		String pwd = prefs.getString(key);
		if (pwd == null) {
			return "";
		}
		return pwd;
	}

	private Preferences getPerference() {
		String name = this.getClass().getName();
		Preferences prefs = Gdx.app.getPreferences(name);
		return prefs;
	}

	@Override
	public void draw() {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.draw();
	}

	public void setPassword(String password) {
		Panel p = loginUi.getPanel();
		TextField ff = p.getPasswordtextfield();
		ff.setText(password);
		save();
	}

	public void setUname(String uname) {
		uname = uname.toLowerCase();
		Panel p = loginUi.getPanel();
		p.getNametextfield().setText(uname);
		save();
	}

	private void save() {

		Preferences p = getPerference();
		String text = getUname();
		p.putString("uname", text);
		String text2 = getPassword();
		p.putString("password", text2);

		p.flush();
	}

	@Override
	public MessageBox getMessageBox() {
		return messageBox;
	}

	@Override
	public CocosUI getCocosUI() {
		if(loginUi == null) {
			loginUi = R.Uis.createLoginUI();
		}
		return loginUi;
	}
}
