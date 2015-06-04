package com.taobao.protobuf.generator;

import com.taobao.protobuf.editors.D;
import com.taobao.protobuf.util.Store;

public class CPPGenerater extends PBGeneratorDelegate {

	public CPPGenerater() {
		super(D.LanguageArg.CPP, "");
	}

	@Override
	protected String getDestFile() {
		String string = Store.getString(D.Paths.CPP_CODE);
		return string;
	}

}
