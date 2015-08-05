package cn.javaplus.game.defender.json;
//package cn.javaplus.game.defender.json;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//import cn.javaplus.game.defender.base.AssetsFactory;
//
//public class FrameFactory extends AssetsFactory {
//
//
//	private static FrameFactory instance;
//
//	public static final FrameFactory getInstance() {
//		if (instance == null) {
//			instance = new FrameFactory();
//		}
//		return instance;
//	}
//
//	private FrameFactory() {
//	}
//
//	/**
//	 * 获取游戏帧序列
//	 * @param activity 
//	 */
//	public List<Frame> getFrames(String name) {
//		String fileName = getFilePath(name) + ".json";
//		JSONObject json = JSONFactory.getInstance().getJSONObject(fileName);
//
//		return get(json);
//	}
//
//	private List<Frame> get(JSONObject json) {
//		List<Frame> ls = new ArrayList<Frame>();
//
//		try {
//
//			JSONArray ja = json.getJSONArray("frames");
//
//			int i = 0;
//			while (i < ja.length()) {
//
//				JSONObject o = ja.getJSONObject(i);
//				ls.add(new Frame(o));
//				i = i + 1;
//			}
//
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
//
//		return ls;
//	}
//
//	public List<Frame> getFrames(int jsonId) {
//		JSONObject json = JSONFactory.getInstance().getJSONObject(jsonId);
//		return get(json);
//	}
//}
