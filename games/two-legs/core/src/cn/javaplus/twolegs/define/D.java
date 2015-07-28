package cn.javaplus.twolegs.define;

import org.javaplus.game.common.Configs;

import cn.javaplus.twolegs.App;

public class D {

	public static float MOVE_FRONT_F; // 上关节
										// 逆时针旋转
										// 最大力度
	public static float MOVE_FRONT_S; // 上关节
										// 逆时针旋转速度
	public static float MOVE_BIHIND_F; // 上关节
										// 顺时针旋转
										// 最大力度
	public static float MOVE_BIHIND_S; // 上关节
										// 顺时针旋转速度

	public static float HEAD_DENSITY; // 头的密度
	public static float LEG_DENSITY; // 腿的密度
	public static float STOP_FORCE; // 上关节
									// 静止时
									// 旋转力度
	public static double MAX_ANGLE; // 上关节旋转角度范围
									// +28
									// 至
									// -28度,
									// 超过这个角度,
	// 不用力

	public static float XI_GAI_F; // 打直腿时
									// ,膝盖所用的力
	public static float XI_GAI_S; // 打直腿时
									// ,膝盖转速

	public static double MIN_ANGLE;
	public static float LEG_FRICTION; // 腿的摩擦力

	public static float GRAVITY_SCALE; // 重力加速度

	public static float GRAVITY_Y; // 重力加速度
									// Y
	public static float GRAVITY_X; // 重力加速度

	// 地面摩擦力
	public static float GROUND_FRICTION;

	// 地面弹性
	public static float GROUND_RESTITUTION;

	// 本地排行榜记录超时时间
	public static long RLIST_UPDATE_MILLIS;

	public static int STAGE_H = 640;
	public static int STAGE_W = 1136;
	public static boolean IS_SHOW_ERROR_MESSAGE = true;
	public static boolean IS_SHOW_DEBUG_MESSAGE = true;
	public static String GROUND = "ground";
	public static boolean IS_SHOW_ACTOR_TEXTURE_REGION_BACKGROUND = false;
	public static float BOX_2D_STAGE_SCALE = D.STAGE_W / 10f;// 摄像机

//	public static void init() {
	static {

		MOVE_FRONT_F = getFloat("MOVE_FRONT_F", 2); // 上关节
													// 逆时针旋转
													// 最大力度
		MOVE_FRONT_S = getFloat("MOVE_FRONT_S", 1.3f); // 上关节
														// 逆时针旋转速度
		MOVE_BIHIND_F = getFloat("MOVE_BIHIND_F", 2); // 上关节
														// 顺时针旋转
														// 最大力度
		MOVE_BIHIND_S = getFloat("MOVE_BIHIND_S", 1.5f); // 上关节
															// 顺时针旋转速度

		HEAD_DENSITY = getFloat("HEAD_DENSITY", 2.5f); // 头的密度
		LEG_DENSITY = getFloat("LEG_DENSITY", 0.9f); // 腿的密度
		STOP_FORCE = getFloat("STOP_FORCE", 0.3f); // 上关节
													// 静止时
													// 旋转力度
		MAX_ANGLE = getFloat("MAX_ANGLE", 60); // 上关节旋转角度范围
												// +28
												// 至
												// -28度,
												// 超过这个角度,
		// 不用力

		XI_GAI_F = getFloat("XI_GAI_F", 8); // 打直腿时
											// ,膝盖所用的力
		XI_GAI_S = getFloat("XI_GAI_S", 5); // 打直腿时
											// ,膝盖转速

		MIN_ANGLE = getFloat("MIN_ANGLE", -30);
		LEG_FRICTION = getFloat("LEG_FRICTION", 0.6f); // 腿的摩擦力

		GRAVITY_SCALE = getFloat("GRAVITY_SCALE", 1.4f); // 重力加速度
															// 比例
		// public static String GROUND = "ground-test";
		// GRAVITY_SCALE = 0f;

		GRAVITY_Y = getFloat("GRAVITY_Y", -9.8f) * GRAVITY_SCALE; // 重力加速度
																	// Y
		GRAVITY_X = getFloat("GRAVITY_X", 1.4f) * GRAVITY_SCALE; // 重力加速度

		// 地面摩擦力
		GROUND_FRICTION = getFloat("GROUND_FRICTION", 0.5f);

		// 地面弹性
		GROUND_RESTITUTION = getFloat("GROUND_RESTITUTION", 0.0f);

		// 本地排行榜记录超时时间
		RLIST_UPDATE_MILLIS = getLong("RLIST_UPDATE_MILLIS", 2 * 60 * 60 * 1000);

	}
	
	private static float getFloat(String string, float defaultValue) {
		try {
			String string2 = get(string, defaultValue);
			return new Float(string2);
		} catch (Throwable e) {
			return defaultValue;
		}
	}

	private static String get(String key, Object defaultValue) {
		try {
			Configs c = App.getConfigs();
			String config = c.getConfig(key, defaultValue.toString());
			return config;
		} catch (Throwable e) {
			return defaultValue.toString();
		}
	}

	private static long getLong(String string, long defaultValue) {
		try {
			return new Long(get(string, defaultValue));
		} catch (Throwable e) {
			return defaultValue;
		}
	}
}
