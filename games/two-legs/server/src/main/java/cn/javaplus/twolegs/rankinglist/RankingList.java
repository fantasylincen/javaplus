package cn.javaplus.twolegs.rankinglist;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import cn.javaplus.log.Log;
import cn.javaplus.twolegs.mongo.MongoGen.Daos;
import cn.javaplus.twolegs.mongo.MongoGen.RoleDao;
import cn.javaplus.twolegs.mongo.MongoGen.RoleDto;
import cn.javaplus.twolegs.mongo.MongoGen.TopListDao;
import cn.javaplus.twolegs.mongo.MongoGen.TopListDao.TopListDtoCursor;
import cn.javaplus.twolegs.mongo.MongoGen.TopListDto;

import com.google.common.collect.Lists;

public class RankingList {

	private static final int SIZE = 30;
	private static RankingList rankingList;

	public static RankingList getInstance() {
		if (rankingList == null)
			rankingList = new RankingList();
		return rankingList;
	}

	private List<TopListDto> users;
	private boolean isPushOver = true;

	private RankingList() {
	}

	public void push(String id, float score) {
		if (!isPushOver) {
			Log.e("not push over please wait...");
			return;
		}
		isPushOver = false;
		add(id, score);
		if (users.size() > SIZE) {
			removeLast();
		}
		isPushOver = true;
	}

	public List<TopListDto> getUsers() {
		return users;
	}

	/**
	 * 截取2位小数的成绩
	 * 
	 * @return
	 */
	public String sub(float score) {
		if (score <= 0) {
			return "0.00";
		}
		return String.format("%.2f", score);
	}

	private void removeLast() {
		if (!users.isEmpty())
			users.remove(users.size() - 1);
	}

	private void add(String id, float score) {
		RoleDao dao = Daos.getRoleDao();
		RoleDto roleDto = dao.get(id);

		TopListDto dto = new TopListDto();
		dto.setId(id);
		dto.setScore(sub(score));
		dto.setNick(roleDto.getNick());
		Daos.getTopListDao().save(dto);

		users.add(dto);
		sort();
	}

	private void sort() {
		Comparator<TopListDto> c = new Comparator<TopListDto>() {

			@Override
			public int compare(TopListDto o1, TopListDto o2) {
				float s = new Float(o2.getScore()) - new Float(o1.getScore());
				s *= 10000;
				return (int) s;
			}
		};
		Collections.sort(users, c);
	}

	public void loadUsers() {
		users = Lists.newArrayList();
		TopListDao dao = Daos.getTopListDao();
		TopListDtoCursor a = dao.find();
		for (TopListDto dto : a) {
			users.add(dto);
		}
		sort();
	}

}
