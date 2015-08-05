package cn.javaplus.twolegs.handlers;

import cn.javaplus.twolegs.base.AbstractHandler;
import cn.javaplus.twolegs.base.GameServerException;
import cn.javaplus.twolegs.base.Parameters;
import cn.javaplus.twolegs.base.Response;
import cn.javaplus.twolegs.mongo.MongoGen.Daos;
import cn.javaplus.twolegs.mongo.MongoGen.RoleDao;
import cn.javaplus.twolegs.mongo.MongoGen.RoleDto;
import cn.javaplus.twolegs.mongo.MongoGen.ScoreDao;
import cn.javaplus.twolegs.mongo.MongoGen.ScoreDto;
import cn.javaplus.twolegs.rankinglist.RankingList;
import cn.javaplus.util.Util;

public class CommitBestScoreHandler extends AbstractHandler {

	@Override
	protected void handle(Response response, Parameters ps) {
		String bestScore = ps.getString("bestScore");
		String roleId = ps.getString("roleId");
		String passwordMd5 = ps.getString("passwordMd5");
		String key = ps.getString("key");

		check(roleId, passwordMd5, key, bestScore);

		ScoreDto dto = saveMyBestScore(bestScore, roleId);
		pushToRankingList(dto);
	}

	private void pushToRankingList(ScoreDto dto) {
		RankingList.getInstance().push(dto.getId(), dto.getScore());
	}

	private void check(String roleId, String passwordMd5, String key, String bestScore) {
		RoleDao dao = Daos.getRoleDao();
		RoleDto data = dao.get(roleId);
		String pmd5 = data.getPasswordMd5();
		if (!pmd5.equals(passwordMd5)) {
			throw new GameServerException("passwordMd5 error:" + passwordMd5);
		}

		String md5 = Util.Secure.md5("xxxyyy" + bestScore + pmd5);
		if (!md5.equals(key)) {
			throw new GameServerException("key error");
		}
	}

	private ScoreDto saveMyBestScore(String bestScore, String id) {
		ScoreDao dao = Daos.getScoreDao();
		ScoreDto dto = dao.createDTO();
		dto.setId(id);
		dto.setScore(new Float(bestScore));
		dao.save(dto);
		return dto;
	}
}
