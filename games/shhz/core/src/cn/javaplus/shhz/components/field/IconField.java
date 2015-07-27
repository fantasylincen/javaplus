package cn.javaplus.shhz.components.field;

import cn.javaplus.shhz.Game;
import cn.javaplus.shhz.user.Player;
import cn.javaplus.shhz.user.User;
import cn.javaplus.shhz.user.Value;

import com.badlogic.gdx.scenes.scene2d.Group;

public class IconField extends Group {
	public IconField(String imgPath, Value value) {
	}

	protected static Value getGold() {
		User user = Game.getUser();
		Player player = user.getPlayer();
		return player.getGold();
	}
}
