package cn.javaplus.twolegs.handlers;

import java.util.List;

import cn.javaplus.twolegs.base.AbstractHandler;
import cn.javaplus.twolegs.base.Parameters;
import cn.javaplus.twolegs.base.Response;
import cn.javaplus.twolegs.mongo.MongoGen.TopListDto;
import cn.javaplus.twolegs.rankinglist.RankingList;

public class RankingListHandler extends AbstractHandler {

	static String temp;

	@Override
	protected void handle(Response response, Parameters ps) {

		if (temp == null)
			temp = "{\"roleId\":\"ROLE_ID\", \"nick\":\"NICK\", \"score\":\"SCORE\"}";

		String roleId = ps.getString("roleId");
		response.put("roleId", roleId);

		RankingList list = RankingList.getInstance();
		List<TopListDto> users = list.getUsers();
		int rank = 1;
		for (TopListDto dto : users) {
			put(rank, response, dto);
			rank++;
		}
	}

	private void put(int rank, Response response, TopListDto dto) {
		response.put(rank + "", build(dto.getId(), dto.getNick(), dto.getScore()));
	}

	private String build(String id, String nick, String score) {
		String a = temp.replaceFirst("ROLE_ID", id);
		a = a.replaceFirst("NICK", nick);
		a = a.replaceFirst("SCORE", score);
		return a;
	}
}
