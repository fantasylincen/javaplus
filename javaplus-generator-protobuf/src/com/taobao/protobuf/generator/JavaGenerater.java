package com.taobao.protobuf.generator;

import com.taobao.protobuf.editors.D;
import com.taobao.protobuf.util.Store;

public class JavaGenerater extends PBGeneratorDelegate {

	public JavaGenerater() {
		super(D.LanguageArg.JAVA, "");
	}

	@Override
	protected String getDestFile() {
		String string = Store.getString(D.Paths.JAVA_CODE);
		return string;
	}
}
