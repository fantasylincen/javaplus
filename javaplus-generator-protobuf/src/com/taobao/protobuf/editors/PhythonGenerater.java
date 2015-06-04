package com.taobao.protobuf.editors;

import com.taobao.protobuf.generator.PBGeneratorDelegate;
import com.taobao.protobuf.util.Store;

public class PhythonGenerater extends PBGeneratorDelegate {

	public PhythonGenerater() {
		super(D.LanguageArg.PHYTHON, "");
	}

	@Override
	protected String getDestFile() {
		String string = Store.getString(D.Paths.PHYTHON_CODE);
		return string;
	}

}
