package cn.javaplus.game.defender.json;
//package cn.javaplus.game.defender.json;
//
//import java.io.InputStream;
//
//import org.json.JSONObject;
//
//import cn.javaplus.game.defender.AndroidApp;
//import cn.javaplus.game.defender.base.AssetsFactory;
//
///**
// * JSON文件读取器，专门用来读取包含帧动画
// */
//public class JSONFactory extends AssetsFactory {
//
//	private static JSONFactory	instance;
//
//	private JSONFactory() {
//	}
//
//	public static final JSONFactory getInstance() {
//		if (instance == null) {
//			instance = new JSONFactory();
//		}
//		return instance;
//	}
//
//	public JSONObject getJSONObject(String name) {
//		String fName = getFilePath(name);
//		InputStream is = null;
//		try {
//			is = AndroidApp.getDefault().getAssets().open(name);
//
//			return getJSONObject(is);
//
//		} catch (Exception e) {
//			Debug.e("json文件编码对不对? UTF-8");
//			throw new RuntimeException(e);
//		} finally {
//		}
//	}
//
//	public JSONObject getJSONObject(int id) {
//		return getJSONObject(AndroidApp.getDefault().getResources().openRawResource(id));
//	}
//
//	public JSONObject getJSONObject(InputStream is) {
//		try {
//			int length = is.available();
//			byte[] data = new byte[length];
//			is.read(data);
//			String s = new String(data);
//			return new JSONObject(s);
//		} catch (Exception e) {
//		}
//		return null;
//	}
//
//}
