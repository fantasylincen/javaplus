package cn.javaplus.game.defender.stage;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.IActor;

public class BirdLayer extends Group implements IActor {

	public class DeathListener implements EventListener {

		@Override
		public boolean handle(Event event) {
			IActor target = event.getTarget();
			removeActor(target);
			System.out.println("BirdLayer.DeathListener.handle() remove");
			return true;
		}

	}

	private static final int Y = 100;

	public BirdLayer() {

		// addActor(Assets.createPhysicalImage("bird", 200, 300));

		// for (int i = 0; i < 30; i++) {

		BirdNew actor = new BirdNew(200, Y);
		actor.addListener(new DeathListener());
		 addActor(actor);
		// }

		// new Thread(){
		// public void run() {
		// while(true) {
		// Animation a = Game.getAssetsManager().getAnimation(0.1f, "bird1.png",
		// "bird2.png", "bird3.png");
		// a.setPlayMode(Animation.LOOP);
		// PhysicalActor sprite =
		// Game.getAssetsManager().getPhysicalActor("bird1", a, 400, 400, 1);
		// addActor(sprite);
		// sprite.addListener(new DeathListener());
		// sprite.setLinearVelocity(0, -3);
		// Util.Thread.sleep(200);
		// }
		// };
		// }.start();
		// addActor(new BirdNew(300, Y));
		// addActor(new BirdNew(400, Y));
		// addActor(new BirdNew(500, Y));
		// addActor(new BirdNew(600, Y));

		// addActor(new Bird());
	}

	@Override
	public void act(float delta) {
		super.act(delta);
	}
}
