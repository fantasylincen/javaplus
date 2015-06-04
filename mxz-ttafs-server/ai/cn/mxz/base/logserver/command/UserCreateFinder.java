package cn.mxz.base.logserver.command;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

import cn.javaplus.file.FileScanner;
import cn.javaplus.file.FileScannerImpl;
import cn.javaplus.time.Time;
import cn.javaplus.util.Closer;

import com.google.common.collect.Sets;

class UserCreateFinder {
	private Collection<? extends String> getUserAll(Date start, Date end, File file) {

		BufferedReader br = null;

		try {

			br = new BufferedReader(new FileReader(file));

			return read(file, br, start, end);

		} catch (FileNotFoundException e) {

			throw new RuntimeException(e);

		} finally {

			Closer.close(br);
		}
	}

	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private Matcher	matcher;

	private String getLogTime(String line) {
		return line.substring(1, 20);
	}
	
	UserCreateFinder(Matcher matcher) {
		this.matcher = matcher;
	}
	
	private Collection<? extends String> read(File file, BufferedReader br, Date start, Date end) {

		Set<String> set = Sets.newHashSet();

		while(true) {

			try {

				String line = br.readLine();

				if(line == null ) {

					break;
				}

				//[2013-10-09 15:02:29,929] [Service]InitServiceImpl.access(u39856700,784905)|51.386995ms|UserID:u39856700|SocketID:27
				if(matcher.isMatches(line)) {

					String time = getLogTime(line);

					Date logTime = format.parse(time);

					if(isTooOld(logTime, start, end)) {

						break;
					}


					if(logTime.after(end) || logTime.before(start)) {

						continue;
					}


					String user = matcher.getUser(line);

					set.add(user);
				}

			} catch (Exception e) {

				throw new RuntimeException(e);
			}
		}

		return set;
	}

	private boolean isTooOld(Date logTime, Date start, Date end) {

		if(logTime.before(start)) {

			long t = start.getTime() - logTime.getTime();

			return t > Time.MILES_ONE_HOUR * 10;
		}

		if(logTime.after(end)) {

			long t2 = logTime.getTime() - end.getTime();

			return t2 > Time.MILES_ONE_HOUR * 10;
		}

		return false;
	}

	/**
	 * @param dir	exp. "logs/all"
	 * @param start
	 * @param end
	 * @return
	 */
	Set<String> findUsers(String dir, Date start, Date end) {

		FileScanner scanner = new FileScannerImpl(dir);

		Iterable<File> logIterator = scanner.iterator();

		Set<String> allUser = Sets.newHashSet();

		for (File file : logIterator) {

			allUser.addAll(getUserAll(start, end, file));
		}

		return allUser;
	}
}
