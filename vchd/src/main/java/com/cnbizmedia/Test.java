package com.cnbizmedia;

import java.io.File;
import java.io.IOException;

import com.cnbizmedia.gen.dto.MongoGen.Daos;
import com.cnbizmedia.gen.dto.MongoGen.SystemKeyValueDao;
import com.cnbizmedia.gen.dto.MongoGen.SystemKeyValueDto;

public class Test {
	public static void main(String[] args) {
		File f = null;
		String time = getTime(f);
	}

	private static String getTime(File f) {
		SystemKeyValueDao dao = Daos.getSystemKeyValueDao();
		try {
			SystemKeyValueDto dto = dao.get("uploadTime:" + f.getCanonicalPath());
			return dto.getValue();
		} catch (IOException e) {
			return "Exception";
		}
	}

}
