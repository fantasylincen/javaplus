package org.hhhhhh.guess.rankinglist;

import java.util.ArrayList;
import java.util.List;

import org.hhhhhh.guess.Server;
import org.hhhhhh.guess.hibernate.dao.Daos;
import org.hhhhhh.guess.hibernate.dto.UserDto;
import org.hhhhhh.guess.user.User;

import com.google.common.collect.Lists;

public class RankingList {

	public List<User> getUsers() {
		List<UserDto> users = Daos.getUserDao().findRankingList();
		ArrayList<User> ls = Lists.newArrayList();
		for (UserDto dto : users) {
			String username = dto.getUsername();
			ls.add(Server.getUser(username));
		}
		return ls;
	}

	public int getRank(User user) {
		String username = user.getUsername();
		List<User> users = getUsers();
		int rank = 1;
		for (User u : users) {
			String un = u.getUsername();
			if (un.equals(username)) {
				return rank;
			}
			rank++;
		}
		return 1000;
	}

}
