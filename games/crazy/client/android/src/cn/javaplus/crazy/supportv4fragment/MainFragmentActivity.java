package cn.javaplus.crazy.supportv4fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import cn.javaplus.crazy.Conf;
import cn.javaplus.crazy.android.R;

import com.baidu.mobstat.StatService;

public class MainFragmentActivity extends FragmentActivity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_activity_main);
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		FirstFragment first = new FirstFragment();
		ft.add(R.id.container, first);
		ft.commit();
	}
	
	public void onResume() {
		Log.w(Conf.TAG, "Activity1.OnResume()");
		super.onResume();

		/**
		 * 页面起始（注意： 每个Activity中都需要添加，如果有继承的父Activity中已经添加了该调用，那么子Activity中务必不能添加）
		 * 如果该FragmentActivity包含了几个全页面的fragment，那么可以在fragment里面加入就可以了，这里可以不加入。
		 */
		StatService.onResume(this);
	}

	public void onPause() {
		Log.w(Conf.TAG, "Activity1.onPause()");
		super.onPause();

		/**
		 * 页面结束（注意： 每个Activity中都需要添加，如果有继承的父Activity中已经添加了该调用，那么子Activity中务必不能添加）
		 * 如果该FragmentActivity包含了几个全页面的fragment，那么可以在fragment里面加入就可以了，这里可以不加入。
		 */
		StatService.onPause(this);
	}
}
