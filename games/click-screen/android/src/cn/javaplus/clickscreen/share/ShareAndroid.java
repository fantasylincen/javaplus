package cn.javaplus.clickscreen.share;

import org.javaplus.game.common.Share;

import android.app.Activity;
import android.content.Intent;
import cn.javaplus.clickscreen.App;

public class ShareAndroid implements Share {

	private Activity activity;

	public ShareAndroid(Activity activity) {
		this.activity = activity;
	}

	public Activity getActivity() {
		return activity;
	}

	@Override
	public void share() {
		
		String shareText = App.getConfigs().getConfig("SHARE_TEXT", "游戏推荐:奔走吧兄弟");
		
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
		intent.putExtra(Intent.EXTRA_TEXT, shareText);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		activity.startActivity(Intent.createChooser(intent, "分享"));
	}

	@Override
	public void init() {
		
	}
}
