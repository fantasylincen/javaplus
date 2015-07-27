package cn.javaplus.crazy;

import java.util.HashMap;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import cn.javaplus.crazy.android.R;
import cn.javaplus.crazy.appfragment.AppFragmentDemoActivity;
import cn.javaplus.crazy.supportv4fragment.MainFragmentActivity;

import com.baidu.kirin.CheckUpdateListener;
import com.baidu.kirin.PostChoiceListener;
import com.baidu.kirin.StatUpdateAgent;
import com.baidu.kirin.objects.KirinCheckState;
import com.baidu.mobstat.StatService;

public class DemoActivity1 extends Activity implements CheckUpdateListener,
		PostChoiceListener {
	/** Called when the activity is first created. */

	private Button btn_pre;
	private Button btn_next;
	private Button btn_exception;
	private Button btn_event;
	private Button btn_event_duration;
	private Button btn_event_start;
	private Button btn_event_end;
	private Button btn_FragmentPage;
	private Button btn_app_FragmentPage;
	private Button checkUpdate;

	// 小流量发布相关
	private UpdateDialog utestUpdate;
	private CheckUpdateListener mCheckUpdateResponse;
	private PostChoiceListener mPostUpdateChoiceListener;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		Log.d(Conf.TAG, "DemoActivity2.OnCreate()");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout1);
		// 设置AppKey
		// StatService.setAppKey("abc1234");//
		// appkey必须在mtj网站上注册生成，该设置建议在AndroidManifest.xml中填写，代码中填写容易丢失
		// 设置渠道
		/*
		 * 设置渠道的推荐方法。该方法同setAppChannel（String），
		 * 如果第三个参数设置为true（防止渠道代码设置会丢失的情况），将会保存该渠道，每次设置都会更新保存的渠道，
		 * 如果之前的版本使用了该函数设置渠道
		 * ，而后来的版本需要AndroidManifest.xml设置渠道，那么需要将第二个参数设置为空字符串,并且第三个参数设置为false即可
		 * ，如StatService.setAppChannel(this, "", false);。
		 * appChannel是应用的发布渠道，不需要在mtj网站上注册，直接填写就可以
		 */
		StatService.setAppChannel(this, "testmarket", true);

		// 设置每次启动session的间隔失效时间，可以不设置默认30S
		// 测试时，可以使用1秒钟session过期，这样不断的间隔1S启动退出会产生大量日志。
		// StatService.setSessionTimeOut(30);

		// 打开崩溃收集
		// setOn也可以在AndroidManifest.xml文件中填写，BaiduMobAd_EXCEPTION_LOG，打开崩溃错误收集，默认是关闭的
		StatService.setOn(this, StatService.EXCEPTION_LOG);

		/*
		 * 设置启动时日志发送延时的秒数<br/> 单位为秒，大小为0s到30s之间<br/>
		 * 注：请在StatService.setSendLogStrategy之前调用，否则设置不起作用
		 * 
		 * 如果设置的是发送策略是启动时发送，那么这个参数就会在发送前检查您设置的这个参数，表示延迟多少秒发送。<br/>
		 * 这个参数的设置暂时只支持代码加入， 在您的首个启动的Activity中的onCreate函数中使用就可以。<br/>
		 */
		// StatService.setLogSenderDelayed(5);

		/*
		 * 用于设置日志发送策略<br /> 嵌入位置：Activity的onCreate()函数中 <br />
		 * 
		 * 调用方式：StatService.setSendLogStrategy(this,SendStrategyEnum.
		 * SET_TIME_INTERVAL, 1, false); 第二个参数可选： SendStrategyEnum.APP_START
		 * SendStrategyEnum.ONCE_A_DAY SendStrategyEnum.SET_TIME_INTERVAL 第三个参数：
		 * 这个参数在第二个参数选择SendStrategyEnum.SET_TIME_INTERVAL时生效、
		 * 取值。为1-24之间的整数,即1<=rtime_interval<=24，以小时为单位 第四个参数：
		 * 表示是否仅支持wifi下日志发送，若为true，表示仅在wifi环境下发送日志；若为false，表示可以在任何联网环境下发送日志
		 */
		// StatService.setSendLogStrategy(this, SendStrategyEnum.APP_START, 1,
		// false);

		// 调试百度统计SDK的Log开关，可以在Eclipse中看到sdk打印的日志，发布时去除调用，或者设置为false
		StatService.setDebugOn(true);

		checkUpdate = (Button) findViewById(R.id.checkupdate);
		// 小流量发布相关---------------------start------------------------------------------------------
		// 这些设置以及检查更新的代码需要在StatService的系列设置调用之后才行（如果使用了setAppChannel来设置渠道
		// ，起码必须在setAppChannel之后）
		mCheckUpdateResponse = this;
		mPostUpdateChoiceListener = this;
		utestUpdate = new UpdateDialog(this, "TestAppName",
				mPostUpdateChoiceListener);
		StatUpdateAgent.setTestMode(); // 打开小流量调试模式，在该模式下，不受更新频率设置的影响。如果不设置测试模式，那么请求间隔默认每天会请求一次

		// 小流量检查是否有更新，该调用必须在setAppChannel之后调用才可以。启动调用的时候，第二个参数设置true，此时每天启动只提示一次
		StatUpdateAgent.checkUpdate(DemoActivity1.this, true,
				mCheckUpdateResponse);

		checkUpdate.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// 小流量检查是否有更新，该调用必须在setAppChannel之后调用才可以。设置里面调用的时候，第二个参数使用false，此时每次点击都会提示更新
				StatUpdateAgent.checkUpdate(DemoActivity1.this, false,
						mCheckUpdateResponse);
			}
		});
		// 小流量发布相关-----------------------over----------------------------------------------------
		btn_pre = (Button) findViewById(R.id.layout1_btn1);
		btn_next = (Button) findViewById(R.id.layout1_btn2);
		btn_exception = (Button) findViewById(R.id.layout1_btn_excep);
		btn_event = (Button) findViewById(R.id.layout1_btn_event);
		btn_event_duration = (Button) findViewById(R.id.layout1_btn_event_duration);

		btn_event_start = (Button) findViewById(R.id.layout1_btn_event_start);
		btn_event_end = (Button) findViewById(R.id.layout1_btn_event_end);

		btn_FragmentPage = (Button) findViewById(R.id.layout1_fragment);

		btn_app_FragmentPage = (Button) findViewById(R.id.layout1_app_fragment);

		btn_pre.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				Intent intent = new Intent();
				intent.setClass(DemoActivity1.this, DemoActivity3.class);

				DemoActivity1.this.startActivity(intent);
			}
		});

		btn_next.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(DemoActivity1.this, DemoActivity2.class);
				DemoActivity1.this.startActivity(intent);
			}
		});

		btn_exception.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Log.w(Conf.TAG, 10 / 0 + "");
			}
		});

		btn_event.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				// "registered id"必须在mtj网站的自定义事件中添加， “pass”是该注册事件下的事件
				StatService.onEvent(DemoActivity1.this, "registered id",
						"pass", 1);

			}
		});

		/**
		 * 自定义事件的第一种方法，写入某个事件的持续时长
		 */
		btn_event_duration.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				// 事件id（"registered id"）的事件pass，其时长持续100毫秒
				StatService.onEventDuration(DemoActivity1.this,
						"registered id", "pass", 100);

			}
		});

		/*
		 * 自定义事件的第二种方法，自己定义该事件的起始时间和结束时间
		 */
		btn_event_start.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				
				// 事件id（"registered id"）的事件pass，其时长持续10毫秒
				StatService.onEventStart(DemoActivity1.this, "registered id",
						"pass");// 必须和onEventEnd共用才行

			}
		});

		/*
		 * 自定义事件的第二种方法，自己定义该事件的起始时间和结束时间
		 */
		btn_event_end.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				// 事件id（"registered id"）的事件pass，其时长持续10毫秒
				StatService.onEventEnd(DemoActivity1.this, "registered id",
						"pass");// 必须和onEventStart共用才行

			}
		});

		btn_FragmentPage.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent in = new Intent();
				in.setClass(DemoActivity1.this, MainFragmentActivity.class);
				startActivity(in);
			}
		});

		btn_app_FragmentPage.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent in = new Intent();
				in.setClass(DemoActivity1.this, AppFragmentDemoActivity.class);
				startActivity(in);
			}
		});

	}

	public void onResume() {
		Log.w(Conf.TAG, "Activity1.OnResume()");
		super.onResume();

		/**
		 * 页面起始（每个Activity中都需要添加，如果有继承的父Activity中已经添加了该调用，那么子Activity中务必不能添加）
		 * 不能与StatService.onPageStart一级onPageEnd函数交叉使用
		 */
		StatService.onResume(this);
	}

	public void onPause() {
		Log.w(Conf.TAG, "Activity1.onPause()");
		super.onPause();

		/**
		 * 页面结束（每个Activity中都需要添加，如果有继承的父Activity中已经添加了该调用，那么子Activity中务必不能添加）
		 * 不能与StatService.onPageStart一级onPageEnd函数交叉使用
		 */
		StatService.onPause(this);
	}

	@Override
	public void PostUpdateChoiceResponse(JSONObject response) {
		// TODO Auto-generated method stub
	}

	/**
	 * 检查更新的结果
	 */
	@Override
	public void checkUpdateResponse(KirinCheckState state,
			HashMap<String, String> dataContainer) {
		// TODO Auto-generated method stub
		if (state == KirinCheckState.ALREADY_UP_TO_DATE) {
			Log.d("demodemo", "stat == KirinCheckState.ALREADY_UP_TO_DATE");
			// KirinAgent.postUserChoice(getApplicationContext(),
			// choice);//choice 几种升级类型：0-未更新，1-不更新，2-稍后更新，3-手动更新，4-强制更新
		} else if (state == KirinCheckState.ERROR_CHECK_VERSION) {
			Log.d("demodemo", "KirinCheckState.ERROR_CHECK_VERSION");
		} else if (state == KirinCheckState.NEWER_VERSION_FOUND) {
			Log.d("demodemo", "KirinCheckState.NEWER_VERSION_FOUND"
					+ dataContainer.toString());

			String isForce = dataContainer.get("updatetype");
			String noteInfo = dataContainer.get("note");
			String publicTime = dataContainer.get("time");
			String appUrl = dataContainer.get("appurl");
			String appName = dataContainer.get("appname");
			String newVersionName = dataContainer.get("version");
			String newVersionCode = dataContainer.get("buildid");
			String attachInfo = dataContainer.get("attach");

			// 这些信息都是在mtj.baidu.com上您选择的小流量定制信息
			utestUpdate.doUpdate(appUrl, noteInfo);
		}
	}
}

// /~ 