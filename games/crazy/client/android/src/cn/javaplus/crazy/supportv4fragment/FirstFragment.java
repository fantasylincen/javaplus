package cn.javaplus.crazy.supportv4fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.baidu.mobstat.StatService;

import cn.javaplus.crazy.Conf;
import cn.javaplus.crazy.android.R;

public class FirstFragment extends Fragment {

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_first, container, false);
		Button bt_first = (Button) view.findViewById(R.id.bt_first);
		bt_first.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				FragmentTransaction ft = getFragmentManager()
						.beginTransaction();
				SecondFragment second = new SecondFragment();
				ft.replace(R.id.container, second);
				ft.commit();
			}
		});
		return view;
	}

	public void onResume() {
		super.onResume();
		Log.d(Conf.TAG, "First Fragment resume");
		/**
		 * Fragment页面起始 (注意： 如果有继承的父Fragment中已经添加了该调用，那么子Fragment中务必不能添加）
		 */
		StatService.onResume(this);
	}

	public void onPause() {
		super.onPause();
		Log.d(Conf.TAG, "First Fragment pause");
		/**
		 * Fragment 页面结束（注意：如果有继承的父Fragment中已经添加了该调用，那么子Fragment中务必不能添加）
		 */
		StatService.onPause(this);
	}
}
