package com.qq;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.javaplus.game.power.AndroidApp;
import cn.javaplus.game.tank.R;

import com.tencent.tauth.Constants;
import com.tencent.tauth.IRequestListener;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

public class MainActivity extends BaseActivity {

	private final class StarGameActivityListener extends BaseUiListener {

		@Override
		public void onComplete(JSONObject arg0) {
			Intent intent = new Intent(getApplicationContext(), AndroidApp.class);
			startActivity(intent);
		}
	}

	private final class MyRequestAdaptor extends RequestAdaptor {
		@Override
		public void onComplete(final JSONObject response, Object state) {
	
			Message msg = new Message();
			msg.obj = response;
			msg.what = 0;
			mHandler.sendMessage(msg);
			new Thread() {
	
				@Override
				public void run() {
					if (response.has("figureurl")) {
						Bitmap bitmap = null;
						try {
							bitmap = Util.getbitmap(response.getString("figureurl_qq_2"));
						} catch (JSONException e) {
	
						}
						Message msg = new Message();
						msg.obj = bitmap;
						msg.what = 1;
						mHandler.sendMessage(msg);
					}
				}
	
			}.start();
		}
	}

	private final class MyHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 0) {
				JSONObject response = (JSONObject) msg.obj;
				if (response.has("nickname")) {
					try {
						mUserInfo.setVisibility(android.view.View.VISIBLE);
						mUserInfo.setText(response.getString("nickname"));
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			} else if (msg.what == 1) {
				Bitmap bitmap = (Bitmap) msg.obj;
				mUserLogo.setImageBitmap(bitmap);
				mUserLogo.setVisibility(android.view.View.VISIBLE);
			}
		}
	}

	private final class ClickListener implements DialogInterface.OnClickListener {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			final Context context = MainActivity.this;
			final Context ctxContext = context.getApplicationContext();
			String appId = AppConstants.APP_ID;
			switch (which) {
			case DialogInterface.BUTTON_POSITIVE:
				// 用输入的appid
				String editTextContent = mEtAppid.getText().toString().trim();
				if (!TextUtils.isEmpty(editTextContent)) {
					appId = editTextContent;
	
				}
				break;
			case DialogInterface.BUTTON_NEGATIVE:
				// 默认appid
				break;
			}
			mTencent = Tencent.createInstance(appId, ctxContext);
			updateLoginButton();
			updateUserInfo();
		}
	}

	private class BaseUiListener implements IUiListener {
	
		@Override
		public void onComplete(JSONObject response) {
			Util.showResultDialog(MainActivity.this, response.toString(), "登录成功");
		}
	
		@Override
		public void onError(UiError e) {
			Util.toastMessage(MainActivity.this, "onError: " + e.errorDetail);
			Util.dismissDialog();
		}
	
		@Override
		public void onCancel() {
			Util.toastMessage(MainActivity.this, "onCancel: ");
			Util.dismissDialog();
		}
	}

	private class LoginListener implements OnClickListener {
	
		@Override
		public void onClick(View v) {
			Context context = v.getContext();
			Animation shake = AnimationUtils.loadAnimation(context, R.anim.shake);
			onClickLogin();
			v.startAnimation(shake);
		}
	}

	private Button							mNewLoginButton;
	private TextView						mUserInfo;
	private ImageView						mUserLogo;
	private Tencent							mTencent;
	private EditText						mEtAppid				= null;
	private DialogInterface.OnClickListener	mAppidCommitListener	= new ClickListener();
	private Handler							mHandler				= new MyHandler();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_new);
		initViews();
		setBarTitle("Tank Login");
		mEtAppid = new EditText(this);
		mEtAppid.setText("100584465");

		AlertDialog.Builder d = new AlertDialog.Builder(this);
		d.setTitle("APP_ID");
		d.setCancelable(false);
		d.setIcon(android.R.drawable.ic_dialog_info);
		d.setView(mEtAppid);
		d.setPositiveButton("Commit", mAppidCommitListener);
		d.setNegativeButton("Use Default", mAppidCommitListener);
		d.show();
	}

	private void initViews() {
		mNewLoginButton = (Button) findViewById(R.id.new_login_btn);

		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.main_container);
		mNewLoginButton.setOnClickListener(new LoginListener());
		mUserInfo = (TextView) findViewById(R.id.user_nickname);
		mUserLogo = (ImageView) findViewById(R.id.user_logo);
		updateLoginButton();
	}

	private void updateLoginButton() {
		if (mTencent != null && mTencent.isSessionValid()) {
			mNewLoginButton.setTextColor(Color.RED);
			mNewLoginButton.setText("Exit");
		} else {
			mNewLoginButton.setTextColor(Color.BLUE);
			mNewLoginButton.setText("Login");
		}
	}

	private void updateUserInfo() {
		if (mTencent != null && mTencent.isSessionValid()) {
			IRequestListener r = new MyRequestAdaptor();
			mTencent.requestAsync(Constants.GRAPH_SIMPLE_USER_INFO, null, Constants.HTTP_GET, r, null);
		} else {
			mUserInfo.setText("");
			mUserInfo.setVisibility(android.view.View.GONE);
			mUserLogo.setVisibility(android.view.View.GONE);
		}
	}

	private void onClickLogin() {
		if (!mTencent.isSessionValid()) {
			IUiListener listener = new StarGameActivityListener();
			mTencent.login(this, "all", listener);
		} else {
			mTencent.logout(this);
			update();
		}
	}

	private void update() {
		updateUserInfo();
		updateLoginButton();
	}
}
