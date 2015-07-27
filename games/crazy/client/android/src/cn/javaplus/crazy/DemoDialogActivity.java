package cn.javaplus.crazy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import cn.javaplus.crazy.android.R;

import com.baidu.mobstat.StatService;

public class DemoDialogActivity extends Activity {
	/** Called when the activity is first created. */

	private Button btn_pre;
	private Button btn_next;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		Log.i(Conf.TAG, "DemoDialogActivity.OnCreate()");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout4);

		btn_pre = (Button) findViewById(R.id.layout4_btn1);
		btn_next = (Button) findViewById(R.id.layout4_btn2);

		btn_pre.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(DemoDialogActivity.this, DemoActivity3.class);
				startActivity(intent);
			}
		});
 
		btn_next.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(DemoDialogActivity.this, DemoActivity1.class);
				startActivity(intent);
			}
		});
	}
	
	public void onResume() {
		Log.w(Conf.TAG, "DemoDialogActivity.OnResume()");
		super.onResume();

		/**
		 * 页面起始（每个Activity中都需要添加，如果有继承的父Activity中已经添加了该调用，那么子Activity中务必不能添加）
		 * 与StatService.onResume(this);
		 */
		StatService.onResume(this);
	}

	public void onPause() {
		Log.w(Conf.TAG, "DemoDialogActivity.onPause()");
		super.onPause();

		/**
		 * 页面结束（每个Activity中都需要添加，如果有继承的父Activity中已经添加了该调用，那么子Activity中务必不能添加）
		 * 与StatService.onPause(this);
		 */
		StatService.onPause(this);
	}
}