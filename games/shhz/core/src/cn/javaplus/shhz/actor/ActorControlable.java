package cn.javaplus.shhz.actor;

import com.badlogic.gdx.scenes.scene2d.Group;

/**
 * 可由用户控制的角色
 */
public class ActorControlable extends Group {

	private ActorMenu menu;
	ActorPlist actor;

	/**
	 * @param name
	 *            可传入: xxx 或者 xxx.png 或者 xxx.plist
	 */
	public ActorControlable(String name) {
		actor = new ActorPlist(name);
		menu = new ActorMenu(this);
		menu.setScale(0.3f);
		addActor(actor);
		addActor(menu);
		setSize(actor.getWidth(), actor.getHeight());
	}

	public ActorMenu getMenu() {
		return menu;
	}

}
