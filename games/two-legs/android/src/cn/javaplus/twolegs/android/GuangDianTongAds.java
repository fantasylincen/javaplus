//package cn.javaplus.twolegs.android;
//
//import android.app.Activity;
//import cn.javaplus.twolegs.Ads;
//import cn.javaplus.twolegs.game.ShowListener;
//
//import com.qq.e.ads.InterstitialAd;
//import com.qq.e.ads.InterstitialAdListener;
//
//public class GuangDianTongAds implements Ads {
//
//	private Activity activity;
//
//	public GuangDianTongAds(Activity activity) {
//		this.activity = activity;
//	}
//
//	@Override
//	public void show(ShowListener listener) {
//		// TODO Auto-generated method stub
//		// 创建插屏广告
//		// appId : 在 http://e.qq.com/dev/ 能看到的app唯一字符串
//		// posId : 在 http://e.qq.com/dev/ 生成的数字串，并非 appid 或者 appkey
//		final InterstitialAd iad = new InterstitialAd(activity, "1103422843", "posid");
//		iad.setAdListener(new InterstitialAdListener() {
//			@Override
//			public void onFail() {
//				// 广告出错时的回调
//			}
//
//			@Override
//			public void onBack() {
//				// 广告关闭时的回调
//			}
//
//			@Override
//			public void onAdReceive() {
//				// 广告数据收到时的回调。在收到广告后可以调用 InterstitialAd.show 方法展示插屏
//			}
//
//			@Override
//			public void onClicked() {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void onExposure() {
//				// TODO Auto-generated method stub
//				
//			}
//		});
//		// 请求插屏广告，每次重新请求都可以调用此方法。
//		iad.loadAd();
//		/*
//		 * 展示插屏广告 仅在adreceive事件发生后调用才有效。 IntersititialAd.show 方法会开启一个透明的activity
//		 * 如广告情景不合适，也可考虑InterstitialAd.showAsPopupWindow配套的关闭方法为closePopupWindow
//		 * 优先建议调用show
//		 */
//		iad.show();
//	}
//
//	@Override
//	public boolean isShowing() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean isInitOver() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public void showPopAds() {
//		// TODO Auto-generated method stub
//
//	}
//
//}
