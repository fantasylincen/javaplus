package cn.javaplus.game.power.record;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.IActor;

public class RecordImpl implements Record {

//	private Properties prop;

	public RecordImpl() {
//		File f = new File("/store/defender/game.db");
//		try {
//			if (!f.exists()) {
//				f.createNewFile();
//			}
//
//			prop = new Properties();
//			prop.load(new FileInputStream(f));
//			
//		} catch (IOException e) {
//			throw Util.toRuntimeException(e);
//		}
	}

	@Override
	public Vector2 getPosition(IActor actor) {
		return new Vector2(100,100);
	}

}
