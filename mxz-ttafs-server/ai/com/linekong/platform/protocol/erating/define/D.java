package com.linekong.platform.protocol.erating.define;

import cn.javaplus.util.Util;
import cn.mxz.config.ConfigProperties;


public class D {

	public static final long	GAME_ID		= Util.Property.getProperties(ConfigProperties.PATH).getInt("GAME_ID");

	public static final short VERSION = 0x20;
	public static final short remain_packages = 0;
	
}
