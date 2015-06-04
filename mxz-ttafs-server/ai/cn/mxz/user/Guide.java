package cn.mxz.user;

import mongo.gen.MongoGen.Daos;
import mongo.gen.MongoGen.UserGuideDao;
import mongo.gen.MongoGen.UserGuideDto;
import cn.mxz.city.City;
import define.D;

public class Guide {

	private City	city;

	public Guide(City city) {
		this.city = city;
	}

	public String getStatus() {
		return getStatus(0) + "|" + getStatus(1);
	}

	public int getStatus(int id, int index) {

		String statusAll = getStatus(id);

		char c = statusAll.charAt(index);

		return new Integer(c + "");
	}

	public String getStatus(int id) {

		String status = status(id, D.GUIDE_SIZE).getGuideStatus();

		// Debuger.debug("GuideServiceImpl.getStatusAll() " + status);

		return status;
	}

	private UserGuideDto status(int id, int guideSize) {

		String uname = city.getId();
		UserGuideDao DAO = Daos.getUserGuideDao();
		UserGuideDto ug = DAO.get(uname, id);
		if (ug == null) {
			ug = new UserGuideDto();
			ug.setUname(uname);
			ug.setGuideId(id);
			ug.setGuideStatus(buildZero(guideSize));
			DAO.save(ug);
		} else {
			String s = ug.getGuideStatus();
			if (s.length() != guideSize) {
				s = repair(s, guideSize);
				ug.setGuideStatus(s);
				DAO.save(ug);
			}
		}
		return ug;
	}

	private String repair(String s, int guideSize) {
		if (guideSize < 0) {
			throw new IllegalArgumentException("guideSize = " + guideSize);
		}
		while (s.length() != guideSize) {
			if (s.length() > guideSize) {
				s = s.substring(0, s.length() - 1);
			} else {
				s += "0";
			}
		}
		return s;
	}

	private String buildZero(int guideSize) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < guideSize; i++) {
			if (city.isTester()) {
				sb.append("1");
			} else {
				sb.append("0");
			}
		}
		return sb.toString();
	}

	public void mark(int id, int index, int status) {

		UserGuideDto ug = status(id, D.GUIDE_SIZE);

		String statusAll = ug.getGuideStatus();

		if (getStatus(id, index) == status) {
			return;
		}

		StringBuilder sb = new StringBuilder(statusAll);
		sb.replace(index, index + 1, "" + status);

		UserGuideDao DAO = Daos.getUserGuideDao();

		ug.setGuideStatus(sb.toString());

		DAO.save(ug);
	}
}
