package cn.javaplus.clickscreen.welcome;

import java.util.List;

import org.javaplus.clickscreen.welcome.TextLabel;
import org.javaplus.game.common.GameAssets;
import org.javaplus.game.common.IPreferences;
import org.javaplus.game.common.assets.Assets;
import org.javaplus.game.common.cache.ChangedFileList;
import org.javaplus.game.common.cache.RemoteResources;
import org.javaplus.game.common.cache.ChangedFileList.FileUpdate;
import org.javaplus.game.common.cache.RemoteResources.MergeFilesCallBack;
import org.javaplus.game.common.cache.RemoteResources.RemoteResourcesCallBack;
import org.javaplus.game.common.log.Log;
import org.javaplus.game.common.share.ButtonListener;
import org.javaplus.game.common.stage.AbstractStage;
import org.javaplus.game.common.stage.GameUI;
import org.javaplus.game.common.util.Lists;

import cn.javaplus.clickscreen.App;
import cn.javaplus.clickscreen.define.D;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;

public class WelcomeStage extends AbstractStage {

	public WelcomeStage() {
		super(D.STAGE_W, D.STAGE_H);
	}

	protected void initOver() {
		for (DownloadOverListener d : listeners) {
			d.downLoadOver();
		}
	}

	private final class MergeFilesCallBackImpl implements MergeFilesCallBack {

		@Override
		public void onDownloadOverAll() {
			initOver();
			App.getPreferences().put("has-download-all-resources", "true");
		}

		@Override
		public void onNetError() {
			netError();
		}

		@Override
		public void onDownloadOver(int remainFileCount) {
			textLabel.setText("还剩" + remainFileCount + "个文件...");
		}
	}

	public class RemoteFilesCallBack implements RemoteResourcesCallBack {

		@Override
		public void onBack(ChangedFileList list) {
			List<FileUpdate> all = list.getAll();
			if (all.isEmpty()) {
				initOver();
				return;
			}
			for (FileUpdate need : all) {
				Log.d("需要更新的文件:" + need.getName() + " size = " + need.getSize());
			}
			textLabel.setText("需要更新" + list.getCount() + "个资源, 总计" + list.getKB() + "KB, 是否更新?");
			addButtons();
		}

		private void addButtons() {
			addOkButton();
			addCancelButton();
		}

		private void addCancelButton() {

			addActor(cancel);
		}

		private void addOkButton() {

			addActor(ok);
		}

		@Override
		public void onNetError() {
			netError();
		}

	}

	private void addRetryButton() {
		addActor(retry);
	}

	void netError() {
		textLabel.setText("网络错误");
		addRetryButton();
	}

	@Override
	public void loadAssets() {
		App.getAssets().loadFontInternal("simhei.ttf");
	}

	@Override
	public void unloadAssets() {
		App.getAssets().unloadFont("simhei.ttf");
	}

	private List<DownloadOverListener> listeners = Lists.newArrayList();
	private TextLabel textLabel;

	private Button retry;
	private Button ok;
	private Button cancel;
	private RemoteResources remote;

	@Override
	public void onLoadingOver() {

		addCheckUpdateTip();

		retry = new Button(Assets.getDefaultSkin());
		retry.setName("retry");
		retry.setPosition(200, 200);
		retry.setSize(150, 80);
		retry.addListener(new ButtonListener() {
			@Override
			public void click(ChangeEvent event, Actor actor) {
				remote.mergeFiles(new MergeFilesCallBackImpl());
				retry.remove();
			}
		});

		ok = new Button(Assets.getDefaultSkin());
		ok.setName("ok");
		ok.setPosition(200, 200);
		ok.setSize(150, 80);
		ok.addListener(new ButtonListener() {
			@Override
			public void click(ChangeEvent event, Actor actor) {
				remote.mergeFiles(new MergeFilesCallBackImpl());
				cancel.remove();
				ok.remove();
			}
		});

		cancel = new Button(Assets.getDefaultSkin());
		cancel.setName("cancel");
		cancel.setPosition(400, 200);
		cancel.setSize(150, 80);
		cancel.addListener(new ButtonListener() {
			@Override
			public void click(ChangeEvent event, Actor actor) {
				if (hasNeverUpdateSuccess()) {// 从来没有更新成功过
					App.getOs().exit();
				} else {
					initOver();
				}
				cancel.remove();
				ok.remove();
			}

			private boolean hasNeverUpdateSuccess() {
				String string = App.getPreferences().getString("has-download-all-resources");
				return string == null;
			}
		});
		remote = App.getRemoteResources();
		remote.requestRemoteResourcesHasChanged(new RemoteFilesCallBack());
	}

	@Override
	public boolean keyTyped(char c) {

		if ('d' == c) {
			IPreferences preferences = App.getPreferences();
			preferences.clear();
			Log.d("clear preferences");
		}

		return true;
	}

	private void addCheckUpdateTip() {
		GameAssets a = App.getAssets();
		FreeTypeFontGenerator generator = a.getGenerator("simhei.ttf");
		FreeTypeFontParameter p = new FreeTypeFontParameter();
		p.size = 32;
		p.characters = D.CHARACTERS;

		BitmapFont font = generator.generateFont(p);
		font.setColor(Color.WHITE);
		textLabel = new TextLabel();
		textLabel.setSpace(0.3);
		textLabel.setText("正在检查更新", "正在检查更新.", "正在检查更新..", "正在检查更新...");
		textLabel.setPosition(D.STAGE_W / 2 - 100, D.STAGE_H / 2);
		textLabel.setFont(font);
		addActor(textLabel);
	}

	@Override
	public GameUI getGameUI() {
		return new GameUI() {

			@Override
			public void unload() {

			}

			@Override
			public void load() {

			}

			@Override
			public void buildComponents() {

			}
		};
	}

	public void addInitOverListener(DownloadOverListener listener) {
		listeners.add(listener);
	}

}
