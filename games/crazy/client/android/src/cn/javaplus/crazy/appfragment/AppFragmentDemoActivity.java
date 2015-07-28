package cn.javaplus.crazy.appfragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mobstat.StatService;
import cn.javaplus.crazy.android.R;

public class AppFragmentDemoActivity extends Activity {

	public static String[] array = { "text1,", "text2", "text3", "text4",
			"text5,", "text6", "text7", "text8" };

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.appfragmentmain);
	}
	
	public void onResume() {
		super.onResume();
		System.out.println("FragmentDemoActivity-->onResume");
		/**
		 * 页面起始（注意： 每个Activity中都需要添加，如果有继承的父Activity中已经添加了该调用，那么子Activity中务必不能添加）
		 * 如果该FragmentActivity包含了几个全页面的fragment，那么可以在fragment里面加入就可以了，这里可以不加入。如果不加入将不会记录该Activity页面。
		 */
		StatService.onResume(this);
	}

	public void onPause() {
		super.onPause();
		System.out.println("FragmentDemoActivity-->onPause");
		/**
		 * 页面结束（注意： 每个Activity中都需要添加，如果有继承的父Activity中已经添加了该调用，那么子Activity中务必不能添加）
		 * 如果该FragmentActivity包含了几个全页面的fragment，那么可以在fragment里面加入就可以了，这里可以不加入。如果不加入将不会记录该Activity页面。
		 */
		StatService.onPause(this);
	}

	static int mCurCheckPosition = 0;
	public static class TitlesFragment extends ListFragment {

		boolean mDualPane;
		
		
		@Override
		public void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState); 
			System.out.println("Fragment-->onCreate");
		}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			System.out.println("Fragment-->onCreateView");
			return super.onCreateView(inflater, container, savedInstanceState);
		}
		
		@Override
		public void onPause() {
			// TODO Auto-generated method stub
			super.onPause();
			StatService.onPause(this);
			System.out.println("Fragment-->onPause");
		}
		
		
		@Override
		public void onStop() {
			// TODO Auto-generated method stub
			super.onStop();
			
			System.out.println("Fragment-->onStop");
		}
		
		@Override
		public void onAttach(Activity activity) {
			// TODO Auto-generated method stub
			super.onAttach(activity);
			System.out.println("Fragment-->onAttach");
		}
		
		@Override
		public void onStart() {
			// TODO Auto-generated method stub
			super.onStart();
			System.out.println("Fragment-->onStart");
		}
		
		@Override
		public void onResume() {
			// TODO Auto-generated method stub
			super.onResume();
			StatService.onResume(this);
			System.out.println("Fragment-->onResume");
		}
		
		@Override
		public void onDestroy() {
			// TODO Auto-generated method stub
			super.onDestroy();
			System.out.println("Fragment-->onDestroy");
		}
		
		

		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onActivityCreated(savedInstanceState);
			System.out.println("Fragment-->onActivityCreted");
			setListAdapter(new ArrayAdapter<String>(getActivity(),
					android.R.layout.simple_list_item_1, array));

			View detailsFrame = getActivity().findViewById(R.id.details);

			mDualPane = detailsFrame != null
					&& detailsFrame.getVisibility() == View.VISIBLE;

			if (savedInstanceState != null) {
				mCurCheckPosition = savedInstanceState.getInt("curChoice", 0); //从保存的状态中取出数据
			}

			if (mDualPane) {
				getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);

				showDetails(mCurCheckPosition);
			}
		}

		@Override
		public void onSaveInstanceState(Bundle outState) {
			// TODO Auto-generated method stub
			super.onSaveInstanceState(outState);

			outState.putInt("curChoice", mCurCheckPosition);//保存当前的下标
		}

		@Override
		public void onListItemClick(ListView l, View v, int position, long id) {
			// TODO Auto-generated method stub
			super.onListItemClick(l, v, position, id);
			showDetails(position);
		}

		void showDetails(int index) {
			mCurCheckPosition = index; 
			if (mDualPane) {
				getListView().setItemChecked(index, true);
				DetailsFragment details = (DetailsFragment) getFragmentManager()
						.findFragmentById(R.id.details); 
				if (details == null || details.getShownIndex() != index) {
					details = DetailsFragment.newInstance(mCurCheckPosition); 

					//得到一个fragment 事务（类似sqlite的操作）
					FragmentTransaction ft = getFragmentManager()
							.beginTransaction();
					ft.replace(R.id.details, details);//将得到的fragment 替换当前的viewGroup内容，add则不替换会依次累加
					ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);//设置动画效果
					ft.commit();//提交
				}
			} else {
				new AlertDialog.Builder(getActivity()).setTitle(
						android.R.string.dialog_alert_title).setMessage(
						array[index]).setPositiveButton(android.R.string.ok,
						null).show();
			}
		}
	}

	/**
	 * 作为界面的一部分，为fragment 提供一个layout 
	 * @author terry
	 *
	 */
	public static class DetailsFragment extends Fragment {
		private static int indexs = 0;
		@Override
		public void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			setHasOptionsMenu(true);
		}
		public static DetailsFragment newInstance(int index) { 
			DetailsFragment details = new DetailsFragment();
			Bundle args = new Bundle();
			indexs = index;
			args.putInt("index", index);
			details.setArguments(args);
			return details;
		}

		public int getShownIndex() {
			return getArguments().getInt("index", 0);
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			if (container == null)
				return null;

			ScrollView scroller = new ScrollView(getActivity());
			TextView text = new TextView(getActivity());

			int padding = (int) TypedValue.applyDimension(
					TypedValue.COMPLEX_UNIT_DIP, 4, getActivity()
							.getResources().getDisplayMetrics());
			text.setPadding(padding, padding, padding, padding);
			scroller.addView(text);

			text.setText(array[getShownIndex()]);
			return scroller;
		}
		
		@Override
		public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
			// TODO Auto-generated method stub
			super.onCreateOptionsMenu(menu, inflater);
			 menu.add("Menu 1a").setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
	            menu.add("Menu 1b").setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		}
		
		@Override
		public boolean onOptionsItemSelected(MenuItem item) {
			// TODO Auto-generated method stub
			Toast.makeText(getActivity(), "index is"+getShownIndex()+" && menu text is "+item.getTitle(), 1000).show();
			return super.onOptionsItemSelected(item);
		}
		
		@Override
		public void onAttach(Activity activity) {
			// TODO Auto-generated method stub
			super.onAttach(activity);
			System.out.println("Fragmentdd-->onAttach");
		}
		
		@Override
		public void onStart() {
			// TODO Auto-generated method stub
			super.onStart();
			System.out.println("Fragmentdd-->onStart");
		}
		
		@Override
		public void onResume() {
			// TODO Auto-generated method stub
			super.onResume();
			//此时由于此Fragment嵌套在ListFragment中，同时使用onPause／onResume这对统计函数，需要onPageStart／onPageEnd代替
			StatService.onPageStart(getActivity(), "details" + array[getShownIndex()]);
//			StatService.onResume(this);
			System.out.println("Fragmentdd-->onResume");
		}
		
		@Override
		public void onPause() {
			// TODO Auto-generated method stub
			super.onPause();
			//此时由于此Fragment嵌套在ListFragment中，同时使用onPause／onResume这对统计函数，需要onPageStart／onPageEnd代替
//			StatService.onPause(this);
			StatService.onPageEnd(getActivity(), "details" + array[getShownIndex()]);
			System.out.println("Fragmentdd-->onPause");
		}
		
		@Override
		public void onStop() {
			// TODO Auto-generated method stub
			super.onStop();
			System.out.println("Fragmentdd-->onStop");
		}
		
		
		@Override
		public void onDestroy() {
			// TODO Auto-generated method stub
			super.onDestroy();
			System.out.println("Fragmentdd-->onDestroy");
		}
	}
}