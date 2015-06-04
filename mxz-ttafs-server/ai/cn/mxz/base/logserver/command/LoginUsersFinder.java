package cn.mxz.base.logserver.command;

import java.util.Date;
import java.util.Set;



/**
 *
 * 查询所有指定时间段登陆过的玩家
 *
 * @author 林岑
 *
 */
class LoginUsersFinder {


	private UserCreateFinder	finder;


	public LoginUsersFinder() {
		
		finder = new UserCreateFinder(new Matcher() {
			
			@Override
			public String getUser(String line) {

				String replaceAll = line.replaceAll("^.{58}", "");

				String id = replaceAll.replaceAll(",[a-zA-Z0-9]{0,32}\\)\\|.*", "");

				return id;
			}


			@Override
			public boolean isMatches(String line) {
				return line.matches("^.{26}\\[Service\\]InitServiceImpl\\.access.*");
			}

		});
	}
	

	Set<String> findUsers(Date start, Date end) {
		return finder.findUsers("logs/all", start, end);
	}
	

}
