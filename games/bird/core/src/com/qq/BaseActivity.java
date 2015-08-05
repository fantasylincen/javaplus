package com.qq;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.javaplus.game.tank.R;

public class BaseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.setContentView(R.layout.base_activity_with_titlebar);
		setTitle(null);
	}

	@Override
	public void setContentView(int layoutResID) {
		LayoutInflater inflater = LayoutInflater.from(this);
		((LinearLayout) findViewById(R.id.layout_content)).addView(inflater.inflate(layoutResID, null));
	}

	public void setBarTitle(String title) {
		((TextView) findViewById(R.id.base_title)).setText(title);
	}
}
