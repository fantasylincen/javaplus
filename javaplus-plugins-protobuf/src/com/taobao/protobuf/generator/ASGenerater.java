package com.taobao.protobuf.generator;

import com.taobao.protobuf.editors.D;
import com.taobao.protobuf.util.Store;

public class ASGenerater extends PBGeneratorDelegate {

	public ASGenerater() {
		super(D.LanguageArg.AS, buildPlugin());
	}

	private static String buildPlugin() {

		String protocDir = Store.getString(D.Paths.PROTOC);

		return "--plugin=" + "protoc-gen-as3" + "=\"" + D.ASPlugin.BAT_NAME + "\" ";
	}

	@Override
	protected String getDestFile() {
		String string = Store.getString(D.Paths.AS_CODE);
		return string;
	}
}
