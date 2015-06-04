package cn.mxz.base.logserver.command;

import java.util.Date;
import java.util.Set;


class CreatedUsersFinder {

	private UserCreateFinder	finder;


	public CreatedUsersFinder() {
		finder = new UserCreateFinder(new Matcher() {
			
			@Override
			public String getUser(String line) {

				String replaceAll = line.replaceAll("^.{38}", "");

				String[] split = replaceAll.split(";");

				return split[1];
			}


			@Override
			public boolean isMatches(String line) {
//				[2013-05-28 17:34:05,677] [CreateUser]7020;lc1;lc1;0;127.0.0.1
				return line.matches("^.{26}\\[CreateUser\\].*");
			}

		});
	}
	

	Set<String> findUsers(Date start, Date end) {
		return finder.findUsers("logs/all", start, end);
	}
}
