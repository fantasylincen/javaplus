package org.hhhhhh.guess;

import javax.servlet.http.HttpSession;

import org.hhhhhh.guess.hibernate.dao.Daos;
import org.hhhhhh.guess.hibernate.dao.UserDao;
import org.hhhhhh.guess.hibernate.dto.UserDto;
import org.hhhhhh.guess.question.Manager;
import org.hhhhhh.guess.user.User;
import org.hhhhhh.guess.util.ISystemKeyValueDao;
import org.hhhhhh.guess.util.KeyValue;
import org.hhhhhh.guess.util.KeyValueSaveOnly;
import org.hhhhhh.guess.util.SystemKeyValueDaily;
import org.hhhhhh.guess.util.SystemKeyValueDaoForeverAdaptor;
import org.hhhhhh.guess.util.SystemKeyValueForever;

public class Server {

	public static final String KEY = "JJYSB";
	private static Manager manager;

	public static User getUser(HttpSession session) {
		String username = (String) session.getAttribute("username");
		return getUser(username);
	}

	public static User getUser(String username) {

		if (username == null)
			return null;
		UserDao dao = Daos.getUserDao();
		UserDto dto = dao.get(username);
		if (dto == null)
			return null;
		return new User(dto);
	}

	public static Manager getManager() {
		if (manager == null)
			manager = new Manager();
		return manager;
	}

	private final static class KeyValueSaveOnlyImplementation implements
			KeyValueSaveOnly {
		@Override
		public void set(Object key, Object value) {
			getKeyValueDaily().set(key, value);
			getKeyValueForever().set(key, value);
		}

		@Override
		public void add(Object key, long add) {
			getKeyValueDaily().add(key, add);
			getKeyValueForever().add(key, add);
		}
	}

	public static KeyValueSaveOnly getKeyValueSaveOnly() {
		return new KeyValueSaveOnlyImplementation();
	}

	public static KeyValue getKeyValueForever() {
		ISystemKeyValueDao dao = new SystemKeyValueDaoForeverAdaptor();
		return new SystemKeyValueForever(dao);
	}

	public static KeyValue getKeyValueDaily() {
		ISystemKeyValueDao dao = new SystemKeyValueDaoForeverAdaptor();
		return new SystemKeyValueDaily(dao);
	}
}
