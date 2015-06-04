package cn.mxz.loganalysis;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Set;

import com.google.common.collect.Sets;

public class LoginUserCount {

	/**
	 * @param createOn13 
	 * @param date   格式  "2014-08-13"
	 * @return
	 */
	public Set<String> getUsers(Set<String> createOn13, String date) {
		Set<String> s = Sets.newHashSet();
		Iterator<LogData> it = new LogDataIterator();
//		int count = 0;
		while (it.hasNext()) {
			LogData logData = (LogData) it.next();
			String log = logData.getLog();

			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat f2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if (log.contains("InitServiceImpl.access(") && log.contains("nick:")) {

				Date time = logData.getTime();
				String format = f.format(time);
				String nick = log.replaceAll(".*nick:", "").replaceAll(
						"\\|.*", "");
				if(nick.equals("纳喇裹弋")) {
					System.out.println(time);
				}
				if (format.equals(date)) {
					
					if(createOn13.contains(nick)) {
						if(!s.contains(nick)) {
							System.out.println(nick + " " + f2.format(time));
						}
						s.add(nick);
					}
//					count++;
				}
			}
		}
		// System.out.println("13 日注册人数" + count);
		return s;

	}
	
}
