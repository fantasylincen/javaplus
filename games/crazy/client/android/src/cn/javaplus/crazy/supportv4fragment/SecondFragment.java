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
import cn.javaplus.crazy.Conf;
import cn.javaplus.crazy.android.R;

import com.baidu.mobstat.StatService;

public class SecondFragment extends Fragment {

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater
				.inflate(R.layout.fragment_second, container, false);
		Button bt_second = (Button) view.findViewById(R.id.bt_second);
		bt_second.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				FragmentTransaction ft = getFragmentManager()
						.beginTransaction();
				FirstFragment first = new FirstFragment();
				ft.replace(R.id.container, first);
				ft.commit();
			}
		});
		
		Button bt_onPageDialog = (Button) view.findViewById(R.id.bt_onPageDialog);
		bt_onPageDialog.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				SecondFragment.this.getActivity().finish();
			}
		});
		return view;
	}
//	
	public void onResume() {
		super.onResume();
		Log.d(Conf.TAG, "Second Fragment resume");
		/**
		 * Fragment页面起始 (注意： 如果有继承的父Fragment中已经添加了该调用，那么子Fragment中务必不能添加）
		 */
		StatService.onResume(this);
	}

	public void onPause() {
		super.onPause();
		Log.d(Conf.TAG, "Second Fragment pause");
		/**
		 *Fragment 页面结束（注意：如果有继承的父Fragment中已经添加了该调用，那么子Fragment中务必不能添加）
		 */
		StatService.onPause(this);
	}
}
