package cn.javaplus.twolegs.game;

import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.javaplus.game.common.IPreferences;
import org.javaplus.game.common.http.JsonResult;
import org.javaplus.game.common.http.TwoLegsHttpClient;
import org.javaplus.game.common.http.HttpComponents.CallBackJsonAdaptor;
import org.javaplus.game.common.log.Log;
import org.javaplus.game.common.util.Lists;
import org.javaplus.game.common.util.NumberUtil;
import org.javaplus.game.common.util.Sets;
import org.javaplus.game.common.util.Util;

import cn.javaplus.twolegs.App;
import cn.javaplus.twolegs.define.D;
import cn.javaplus.twolegs.regist.Register;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

public class RankingListRequest {

	private static final String LAST_TIME_KEY = "last-load-ranking-list-time";

	// 本地存放排行榜记录的键
	private static final String RANKING_LIST_STORE = "ranking-list-store";

	public class RankingListCallBack extends CallBackJsonAdaptor {

		@Override
		public void completed(JsonResult result) {
			saveRankingList(result);
			showRankingListFromLocal();
			saveLastUpdateTime();
			Log.d("RankingListRequest.RankingListCallBack", "completed");
		}

		private void saveLastUpdateTime() {
			App.getPreferences().put(LAST_TIME_KEY, System.currentTimeMillis());
		}

		private void saveRankingList(JsonResult result) {
			App.getPreferences().put(RANKING_LIST_STORE, result);
		}

		@Override
		public void failed(String error) {
			super.failed(error);
			showRankingListFromLocal();
			Log.d("RankingListRequest.RankingListCallBack", "failed");
		}

		@Override
		public void onNetError(SocketException ex) {
			super.onNetError(ex);
			showRankingListFromLocal();
			Log.d("RankingListRequest.RankingListCallBack", "onNetError");
		}

		@Override
		public void onTimeOut() {
			super.onTimeOut();
			showRankingListFromLocal();
			Log.d("RankingListRequest.RankingListCallBack", "onTimeOut");
		}

	}

	private Window window;
	private Table table;
	private ScrollPane pane;

	public RankingListRequest(Window window) {
		this.window = window;
		table = new Table();
		pane = new ScrollPane(table);
		pane.setSize(window.getWidth() + 75, 500);
		window.setLayoutEnabled(false);
	}

	public void request() {
		Register r = new Register();
		if (!r.hasRegist()) {
			r.regist(null);
		}

		showRankingListFromLocal();

		if (isTimeOut()) { // 本地排行榜记录是否超时
			loadFromServer();// 从服务器获取
			Log.d("download ranking list from server");
		} else {
			showRankingListFromLocal();
			Log.d("show ranking list from local");
		}
	}

	// 本地加载排行榜并显示
	private void showRankingListFromLocal() {

		IPreferences p = App.getPreferences();
		String result = p.getString(RANKING_LIST_STORE);

		final List<ItemModel> items = getList(JSON.parseObject(result));

		App.getApp().runInMainThread(new Runnable() {
			public void run() {
				BitmapFont font = createFont(items);
				table.clearChildren();
				window.clearChildren();
				boolean isWhite = false;
				for (ItemModel item : items) {
					addItem(item, font, isWhite);
					isWhite = !isWhite;
				}
				window.add(pane);
			}
		});
	}

	private List<ItemModel> getList(JSONObject jo) {
		ArrayList<ItemModel> ls = Lists.newArrayList();
		if (jo != null) {
			for (String k : jo.keySet()) {
				JSONObject obj = JSON.parseObject(jo.getString(k));
				ls.add(build(k, obj));
			}
		}
		tidy(ls);
		return ls;
	}

	private void tidy(ArrayList<ItemModel> ls) {
		replaceMyData(ls);
		Collections.sort(ls, new Comparator<ItemModel>() {

			@Override
			public int compare(ItemModel o1, ItemModel o2) {
				return (int) (new Float(o2.getScore()) * 100 - new Float(o1
						.getScore()) * 100);
			}
		});
		resetRanks(ls);
	}

	private void resetRanks(ArrayList<ItemModel> ls) {
		int minRank = getMinRank(ls);
		for (ItemModel item : ls) {
			item.setRank(minRank + "");
			minRank++;
		}
	}

	private int getMinRank(ArrayList<ItemModel> ls) {
		if (ls.isEmpty()) {
			return 1;
		}
		int min = Integer.MAX_VALUE;
		for (ItemModel item : ls) {
			String rk = item.getRank();
			Integer k = new Integer(rk);
			if (k < min) {
				min = k;
			}
		}
		return min;
	}

	private void replaceMyData(ArrayList<ItemModel> ls) {
		ItemModel item = findMe(ls);
		boolean isFindMe = item != null;
		IPreferences p = App.getPreferences();
		if (isFindMe) {
			item.setName("我(" + item.getName() + ")");
			item.setFontColor(Color.GREEN);
			float best = p.getFloat("best-score");
			if (best > new Float(item.getScore())) {
				item.setScore(String.format("%.2f", best));
			}
			new ScoreCommit().commitBestScore(item.getScore());
		}

		// Register register = new Register();
		// if (!register.hasRegist()) {
		// return;
		// }
		if (!isFindMe/* && isHigherThanLast(ls) */) {
			insertMe(ls);
		}
	}

	private void insertMe(ArrayList<ItemModel> ls) {
		IPreferences p = App.getPreferences();
		String roleId = p.getString("roleId");
		String nick = p.getString("nick");
		String sub = NumberUtil.sub(getMyScore());
		if (nick == null || nick.isEmpty()) {
			nick = "我";
		} else {
			nick = "我(" + nick + ")";
		}

		String rank = "999999999";

		if (ls.isEmpty()) {
			rank = "1";
		}

		ItemModel item = new ItemModel(nick, sub, rank, roleId);
		item.setFontColor(Color.GREEN);
		ls.add(item);
		Log.d("add my item");
	}

	// private boolean isHigherThanLast(ArrayList<ItemModel> ls) {
	// if (ls.isEmpty()) {
	// return false;
	// }
	// float last = getLast(ls);
	// return getMyScore() > last;
	// }

	// private float getLast(ArrayList<ItemModel> ls) {
	// if (ls.isEmpty()) {
	// return 0;
	// }
	// float min = Float.MAX_VALUE;
	// for (ItemModel item : ls) {
	// String s = item.getScore();
	// Float f = new Float(s);
	// if (f < min) {
	// min = f;
	// }
	// }
	// return min;
	//
	// }

	private float getMyScore() {
		IPreferences p = App.getPreferences();
		String s = p.getString("best-score");
		if (s == null || s.isEmpty()) {
			return 0;
		}
		return new Float(s);
	}

	private ItemModel findMe(ArrayList<ItemModel> ls) {
		IPreferences p = App.getPreferences();
		String roleId = p.getString("roleId");

		for (ItemModel item : ls) {
			String id = item.getRoleId();
			if (id.equals(roleId)) {
				return item;
			}
		}
		return null;
	}

	private ItemModel build(String rank, JSONObject obj) {
		String score = obj.getString("score");
		String name = obj.getString("nick");
		String roleId = obj.getString("roleId");
		ItemModel m = new ItemModel(name, score, rank, roleId);
		return m;
	}

	private void loadFromServer() {

		TwoLegsHttpClient c = App.getHttp();
		c.excute(new TwoLegsRequestAdaptor() {

			@Override
			public String getDomain() {
				return "rankingList";
			}

		}, new RankingListCallBack());
	}

	private boolean isTimeOut() {
		String t = App.getPreferences().getString(LAST_TIME_KEY);
		long time;
		if (t == null || t.isEmpty()) {
			time = 0;
		} else {
			time = new Long(t);
		}
		long tt = System.currentTimeMillis() - time;
		return tt > D.RLIST_UPDATE_MILLIS;
	}

	private BitmapFont createFont(List<ItemModel> items) {
		Set<String> set = Sets.newHashSet();
		for (ItemModel item : items) {
			String s = getS(item);
			String[] split = s.split("");
			for (String sss : split) {
				set.add(sss);
			}
		}
		FreeTypeFontParameter p = new FreeTypeFontParameter();
		p.characters = Util.Collection.linkWith("", set);
		p.size = 24;
		BitmapFont f = App.getAssets().getGenerator().generateFont(p);
		f.setColor(Color.BLACK);
		return f;
	}

	private String getS(ItemModel item) {
		return item.getName() + item.getScore() + item.getRank();
	}

	private void addItem(ItemModel item, BitmapFont font, boolean isWhite) {
		table.row();
		table.add(new RankingListItem(item, font, isWhite));
	}

}
