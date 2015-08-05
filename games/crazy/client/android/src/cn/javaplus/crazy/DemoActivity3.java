package cn.javaplus.crazy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.baidu.mobstat.StatActivity;

import cn.javaplus.crazy.android.R;
public class DemoActivity3 extends StatActivity {
	/** Called when the activity is first created. */

	private Button btn_pre;
	private Button btn_next;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		Log.i(Conf.TAG, "DemoActivity3.OnCreate()");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout3);

		btn_pre = (Button) findViewById(R.id.layout3_btn1);
		btn_next = (Button) findViewById(R.id.layout3_btn2);

		btn_pre.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(DemoActivity3.this, DemoActivity2.class);
				startActivity(intent);
			}
		});
 
		btn_next.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(DemoActivity3.this, DemoDialogActivity.class);
				startActivity(intent);
			}
		});
	}

}