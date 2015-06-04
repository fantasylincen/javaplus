package com.taobao.protobuf.util;

import java.io.File;

import com.taobao.protobuf.editors.D;

public class PathFetcher {

	public static String getProtocExePath() {

		String string = Store.getString(D.Paths.PROTOC);

		if (string == null || string.isEmpty()) {
			return "";
		}

		return string + File.separator + D.FileName.PROTOC;
	}

}
