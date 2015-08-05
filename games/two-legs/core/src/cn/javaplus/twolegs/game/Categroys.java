package cn.javaplus.twolegs.game;

import com.badlogic.gdx.physics.box2d.FixtureDef;

public class Categroys {

	public static final class Categroy {

		private short categroyBits;
		private short maskBits;

		public Categroy(int categroyBits, int maskBits) {
			this.categroyBits = (short) categroyBits;
			this.maskBits = (short) maskBits;
		}

		public void bind(FixtureDef fd) {
			fd.filter.categoryBits = categroyBits;
			fd.filter.maskBits = maskBits;			
		}
	}
	
	static final Categroy A = new Categroy(0x0001, 0x0001);
	static final Categroy B = new Categroy(0x0002, 0x0002);
	static final Categroy C = new Categroy(0x0004, 0x0004);
	static final Categroy D = new Categroy(0x0008, 0x0008);
	static final Categroy E = new Categroy(0x0010, 0x0010);
	static final Categroy F = new Categroy(0x001F, 0x001F);

	public static final Categroy HEAD = A;
	public static final Categroy LEFT_LEG_UP = B;
	public static final Categroy LEFT_LEG_DOWN = C;
	public static final Categroy RIGHT_LEG_UP = D;
	public static final Categroy RIGHT_LEG_DOWN = E;
	public static final Categroy GROUND = F;
}
