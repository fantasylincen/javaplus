package com.linekong.platform.protocol.erating;

import java.util.Map;

import cn.javaplus.collections.map.Maps;

public class ErrorCode {

	/** 未知错误 */
	public static final int				E_USER_ACCOUNT_ERROR			= -1406;
	/** 未知错误 */
	public static final int				E_USER_BALANCE_NOT_ENOUGH		= -1441;
	/** 未知错误 */
	public static final int				E_PAY_DUPLICATE					= -1460;
	/** 操作成功 */
	public static final int				S_SUCCESS						= 1;
	/** 操作失败 */
	public static final int				E_ERROR							= 0;
	/** 输入的参数非法 */
	public static final int				E_PARAMETER_ERROR				= -100;
	/** 数据库操作失败 */
	public static final int				E_SYS_DATABASE_ERROR			= -500;
	/** 数据库连接错误 */
	public static final int				E_SYS_DATABASE_CONNECT_ERROR	= -501;
	/** 网关的帐号 */
	public static final int				E_GATEWAY_UID_PWD_ERROR			= -1002;
	/** 网关的状态错误 */
	public static final int				E_GATEWAY_STATE_ERROR			= -1010;
	/** 找不到指定的网关 */
	public static final int				E_GATEWAY_NOT_FOUND				= -1001;
	/** 试图对已经下线的网关进行下线操 */
	public static final int				E_GATEWAY_STATE_IS_OFFLINE		= -1012;
	/** 玩家帐号不存在 */
	public static final int				E_ACCOUNT_NOT_FOUND				= -1201;
	/** 账号已存在 */
	public static final int				E_USER_REG_EXIST_ERROR			= -1407;
	/** 身份证使用次数过多 */
	public static final int				E_IDCODE_TIMES_ERROR			= -1433;
	/** 帐号非法或错误 */
	public static final int				E_ACCOUNT_ERROR					= -1200;
	/** null */
	public static final int				ID								= 1;
	/** 原密码错误 */
	public static final int				E_USER_PASSWORD_ERROR			= -1402;
	/** 创建的角色已经存在 */
	public static final int				E_ROLE_EXIST					= -1251;
	/** 角色已被置删除标志 */
	public static final int				E_ROLE_NOT_EXIST				= -1252;
	/** 试图删除已被置删除标志的角 */
	public static final int				E_ROLE_DELETED					= -1253;
	/** 角色错误 */
	public static final int				E_ROLE_ERROR					= -1250;
	/** 同一流水号重复充值 */
	public static final int				E_CHARGE_DUPLICATE				= -1472;
	/** 充值流水号不存在 */
	public static final int				E_USER_ERROR_CHCAGE_DETAIL_ID	= -1490;
	/** 数据库中的数据非法 */
	public static final int				E_SYS_DATABASE_DATA_INVALID		= -505;
	/** 指定的虚拟物品未找到 */
	public static final int				E_USER_ITEM_NOT_FOUND			= -1461;
	/** null */
	public static final int				E_USER_IB_ITEM_NOT_ENOUGH		= -1457;
	/** null */
	public static final int				E_USER_IB_ITEM_ERROR			= -1450;
	/** 活动条件不满足 */
	public static final int				E_CONDITION_NOT_SATISFIED		= -1553;
	/** 活动未找到 */
	public static final int				E_ACTIVITY_NOT_FOUND			= -1514;
	/** 网络链接错误 */
	public static final int				E_SYS_NET_ERROR					= -300;
	/** 网络操作超时 */
	public static final int				E_SYS_NET_TIMEOUT				= -302;
	/** 指定的 */
	public static final int				E_USER_IB_ITEM_NOT_FOUND		= -1451;
	/** null */
	public static final int				E_USER_IB_ITEM_EXPIRED			= -1459;
	/** null */
	public static final int				E_USER_IB_ITEM_USED				= -1458;
	/** 流水号重复 */
	public static final int				E_IB_PAY_DETAIL_DUPLICATE		= -1282;
	/** 消费详单不存在 */
	public static final int				E_IB_PAY_DETAIL_NOT_EXIST		= -1281;
	/** 登录账号密码不匹配 */
	public static final int				PASSWORD_ERROR					= -1402;
	/** 用户已被冻结 */
	public static final int				E_USER_STATE_FREEZED			= -1411;
	/** 用户已在其它网关登录 */
	public static final int				E_USER_IN_OTHER_GATEWAY			= -1430;
	/** 必须使用密保卡验证 */
	public static final int				E_USER_MATRIX_PASSWD_REQUIRED	= -1421;
	/** 必须使用动态密码验证 */
	public static final int				E_USER_DYNAMIC_PSW_REQUIRED		= -1422;
	/** 玩家主机 */
	public static final int				E_USER_MAC_ILLEGAL				= -1431;
	/** 玩家主机硬件序列号被冻结 */
	public static final int				E_USER_HARDWARE_SN_ILLEGAL		= -1432;
	/** 合作运营通信消息错误 */
	public static final int				E_JOINT_MSG_ERROR				= -1811;
	/** 签名错误 */
	public static final int				E_JOINT_SIGN_ERROR				= -1813;
	/** 玩家的安全码和帐号不匹配 */
	public static final int				E_USER_SECURITYPW_ERROR			= -1403;
	/** null */
	public static final int				IOS								= 2;
	/** 角色不存在 */
	public static final int				E_ROLE_NO_EXIST					= -1252;
	/** 团体已存在 */
	public static final int				E_GROUP_EXIST					= -1901;
	/** 团体被拥有者重复创建 */
	public static final int				E_GROUP_DUPLICATE				= -1902;
	/** 团体已被删除 */
	public static final int				E_GROUP_DELETED					= -1903;
	/** 团体不存在 */
	public static final int				E_GROUP_NOT_EXIST				= -1900;
	/** 非团体拥有者 */
	public static final int				E_NOT_GROUP_OWNER				= -1904;

	private static final String			DOC								= "[\u4e00-\u9fa5\\s]{1,100}";

	private static final String			VALUE							= " +\\-*[0-9]+";

	private static final String			NAME							= "[A-Z_]+";

	private static Map<Integer, String>	docs							= Maps.newIntegerHashMap();

	static {

		docs.put(E_USER_ACCOUNT_ERROR, "未知错误");
		docs.put(E_USER_BALANCE_NOT_ENOUGH, "未知错误");
		docs.put(E_PAY_DUPLICATE, "未知错误");
		docs.put(S_SUCCESS, "操作成功");
		docs.put(E_ERROR, "操作失败");
		docs.put(E_PARAMETER_ERROR, "输入的参数非法");
		docs.put(E_SYS_DATABASE_ERROR, "数据库操作失败");
		docs.put(E_SYS_DATABASE_CONNECT_ERROR, "数据库连接错误");
		docs.put(E_GATEWAY_UID_PWD_ERROR, "网关的帐号");
		docs.put(E_GATEWAY_STATE_ERROR, "网关的状态错误");
		docs.put(E_GATEWAY_NOT_FOUND, "找不到指定的网关");
		docs.put(E_GATEWAY_STATE_IS_OFFLINE, "试图对已经下线的网关进行下线操");
		docs.put(E_ACCOUNT_NOT_FOUND, "玩家帐号不存在");
		docs.put(E_USER_REG_EXIST_ERROR, "账号已存在");
		docs.put(E_IDCODE_TIMES_ERROR, "身份证使用次数过多");
		docs.put(E_ACCOUNT_ERROR, "帐号非法或错误");
		docs.put(ID, "null");
		docs.put(E_USER_PASSWORD_ERROR, "原密码错误");
		docs.put(E_ROLE_EXIST, "创建的角色已经存在");
		docs.put(E_ROLE_NOT_EXIST, "角色已被置删除标志");
		docs.put(E_ROLE_DELETED, "试图删除已被置删除标志的角");
		docs.put(E_ROLE_ERROR, "角色错误");
		docs.put(E_CHARGE_DUPLICATE, "同一流水号重复充值");
		docs.put(E_USER_ERROR_CHCAGE_DETAIL_ID, "充值流水号不存在");
		docs.put(E_SYS_DATABASE_DATA_INVALID, "数据库中的数据非法");
		docs.put(E_USER_ITEM_NOT_FOUND, "指定的虚拟物品未找到");
		docs.put(E_USER_IB_ITEM_NOT_ENOUGH, "null");
		docs.put(E_USER_IB_ITEM_ERROR, "null");
		docs.put(E_CONDITION_NOT_SATISFIED, "活动条件不满足");
		docs.put(E_ACTIVITY_NOT_FOUND, "活动未找到");
		docs.put(E_SYS_NET_ERROR, "网络链接错误");
		docs.put(E_SYS_NET_TIMEOUT, "网络操作超时");
		docs.put(E_USER_IB_ITEM_NOT_FOUND, "指定的");
		docs.put(E_USER_IB_ITEM_EXPIRED, "null");
		docs.put(E_USER_IB_ITEM_USED, "null");
		docs.put(E_IB_PAY_DETAIL_DUPLICATE, "流水号重复");
		docs.put(E_IB_PAY_DETAIL_NOT_EXIST, "消费详单不存在");
		docs.put(PASSWORD_ERROR, "登录账号密码不匹配");
		docs.put(E_USER_STATE_FREEZED, "用户已被冻结");
		docs.put(E_USER_IN_OTHER_GATEWAY, "用户已在其它网关登录");
		docs.put(E_USER_MATRIX_PASSWD_REQUIRED, "必须使用密保卡验证");
		docs.put(E_USER_DYNAMIC_PSW_REQUIRED, "必须使用动态密码验证");
		docs.put(E_USER_MAC_ILLEGAL, "玩家主机");
		docs.put(E_USER_HARDWARE_SN_ILLEGAL, "玩家主机硬件序列号被冻结");
		docs.put(E_JOINT_MSG_ERROR, "合作运营通信消息错误");
		docs.put(E_JOINT_SIGN_ERROR, "签名错误");
		docs.put(E_USER_SECURITYPW_ERROR, "玩家的安全码和帐号不匹配");
		docs.put(IOS, "null");
		docs.put(E_ROLE_NO_EXIST, "角色不存在");
		docs.put(E_GROUP_EXIST, "团体已存在");
		docs.put(E_GROUP_DUPLICATE, "团体被拥有者重复创建");
		docs.put(E_GROUP_DELETED, "团体已被删除");
		docs.put(E_GROUP_NOT_EXIST, "团体不存在");
		docs.put(E_NOT_GROUP_OWNER, "非团体拥有者");

	}

	public static String toString(int resultCode) {
		return docs.get(resultCode);
	}

//	public static void main(String[] args) {
//
//		String content = Util.File.getContent("src/main/resources/errorcode.txt");
//
//		content = content.replaceAll("\\s", " ");
//		Pattern compile = Pattern.compile(NAME + VALUE + DOC);
//
//		Set<String> set = Sets.newHashSet();
//
//		Matcher m = compile.matcher(content);
//		while (m.find()) {
//			String g = m.group();
//
//			String name = Util.Str.find(g, 1, NAME);
//			String value = Util.Str.find(g, 1, VALUE);
//			String doc = Util.Str.find(g, 1, "[\u4e00-\u9fa5]+");
//
//			if (set.contains(name)) {
//				continue;
//			}
//
//			// System.out.println("/**" + doc + "*/");
//			// System.out.println("public static final int " + name + " = " +
//			// value + ";");
//			System.out.println("docs.put(" + name + ", \"" + doc + "\");");
//
//			set.add(name);
//
//		}
//	}
}
