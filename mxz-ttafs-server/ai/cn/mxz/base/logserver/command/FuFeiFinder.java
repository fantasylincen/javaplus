package cn.mxz.base.logserver.command;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.javaplus.util.Closer;

import com.google.common.collect.Sets;

class FuFeiFinder {

	Set<String> findAll() {
		BufferedReader br = null;

		try {

			br = new BufferedReader(new FileReader("logs/all"));

			return read(br);

		} catch (FileNotFoundException e) {

			throw new RuntimeException(e);

		} finally {

			Closer.close(br);
		}
	}

	private Set<String> read(BufferedReader br) {
		
		Set<String> set = Sets.newHashSet();

		while(true) {

			try {

				String line = br.readLine();

				if(line == null ) {

					break;
				}

				//[2013-10-09 15:02:29,929] [Service]SystemServiceImpl.recharge(12)|51.386995ms|UserID:u39856700|SocketID:27
				if(line.matches("^.{26}\\[Service\\]SystemServiceImpl\\.recharge.*")) {

					Pattern compile = Pattern.compile("UserID:[a-zA-Z0-9_]{1,32}");
					Matcher matcher = compile.matcher(line);
					if(matcher.find()) {
						String user = matcher.group();
						user = user.replaceAll("UserID:", "");
						set.add(user);
					}
				}

			} catch (Exception e) {

				throw new RuntimeException(e);
			}
		}

		return set;
	}

}
