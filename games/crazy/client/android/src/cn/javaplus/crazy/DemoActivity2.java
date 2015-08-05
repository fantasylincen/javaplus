package cn.javaplus.crazy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.baidu.mobstat.StatService;

import cn.javaplus.crazy.android.R;
public class DemoActivity2 extends Activity {
	/** Called when the activity is first created. */

	private Button btn_pre;
	private Button btn_next;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		Log.w(Conf.TAG, "DemoActivity2.OnCreate()");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout2);

		btn_pre = (Button) findViewById(R.id.layout2_btn1);
		btn_next = (Button) findViewById(R.id.layout2_btn2);

		btn_pre.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(DemoActivity2.this, DemoActivity1.class);
				startActivity(intent);
			}
		});

		btn_next.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(DemoActivity2.this, DemoActivity3.class);
				startActivity(intent);
			}
		});
	}

	public void onResume() {
		Log.w(Conf.TAG, "Activity2.OnResume()");
		super.onResume();

		StatWrapper.onResume(this);
	}

	public void onPause() {
		Log.w(Conf.TAG, "Activity2.onPause()");
		super.onPause();

		StatWrapper.onPause(this);
	}
}

class StatWrapper {
	public static void onResume(Context context) {

		/**
		 * 此处调用基本统计代码
		 */
		StatService.onResume(context);
	}

	public static void onPause(Context context) {

		/**
		 * 此处调用基本统计代码
		 */
		StatService.onPause(context);
	}
}