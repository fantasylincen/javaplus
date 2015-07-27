package cn.javaplus.twolegs.wxapi;

import java.util.Set;

import org.javaplus.game.common.OS;
import org.javaplus.game.common.Share;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import cn.javaplus.twolegs.App;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.ShareType;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.sso.QZoneSsoHandler;

public class ShareAndroid implements Share {

	private Activity activity;
	private UMSocialService mController;

	public ShareAndroid(Activity activity) {
		this.activity = activity;
		String s = "com.umeng.share";
		mController = UMServiceFactory.getUMSocialService(s);
	}

	public Activity getActivity() {
		return activity;
	}

	@Override
	public void share() {

		activity.runOnUiThread(new Runnable() {
			public void run() {

				try {
					shareSafety();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			private void shareSafety() {
				UMImage img = getShareImage();
				String text = getShareContent();

				mController.setAppWebSite(getTargetUrl());
				mController.setShareContent(text);
				mController.setShareImage(img);
				mController.setShareType(ShareType.NORMAL);
				mController.directShare(activity, SHARE_MEDIA.QZONE, null);
			}

			private String getTargetUrl() {
				return App.getConfigs().getConfig("SHARE_TARGET_URL",
						"www.baidu.com");
			}

			private UMImage getShareImage() {
				FileHandle f = Gdx.files.internal("data/logo256.png");
				byte[] data = f.readBytes();
				Bitmap bmp = BitmapFactory
						.decodeByteArray(data, 0, data.length);
				UMImage img = new UMImage(activity, bmp);
				return img;
			}

			private String getShareContent() {
				String content = App.getConfigs().getConfig("SHARE_CONTENT",
						"{}");
				JSONObject obj = JSON.parseObject(content);
				Set<String> ks = obj.keySet();

				String best = App.getPreferences().getString("best-score");
				if (best.equals("")) {
					best = "0.00";
				}

				for (String k : ks) {
					if (isIn(k, best)) {
						return getShareContent(k, best, obj);
					}
				}
				return " ";
			}

			private boolean isIn(String k, String best) {

				String[] ss = k.split("\\-");
				int min = Integer.parseInt(ss[0]);
				int max = Integer.parseInt(ss[1]);
				float b = Float.parseFloat(best);
				return b <= max && min <= b;
			}

			private String getShareContent(String key, String best,
					JSONObject obj) {
				Object text = obj.get(key);
				String t = text.toString().replaceAll("BEST_SCORE", best);
				t = t.replaceAll("&quot", "\"");
				return t;
			}

		});

	}

	@Override
	public void init() {

		OS os = App.getOs();
		String qqAppId = os.getValue("qqAppId");
		String qqAppKey = os.getValue("qqAppKey");
		{
			QZoneSsoHandler q = new QZoneSsoHandler(activity, qqAppId, qqAppKey);
			q.addToSocialSDK();
		}
//		{
//			String wxAppId = os.getValue("wxAppId");
//			String wxSecret = os.getValue("wxSecret");
//			UMWXHandler wx = new UMWXHandler(activity, wxAppId, wxSecret);
//			wx.setToCircle(true);
//			wx.addToSocialSDK();
//		}

	}
}
