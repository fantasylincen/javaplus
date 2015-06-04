package cn.mxz.bag;

import java.util.ArrayList;
import java.util.List;

import mongo.gen.MongoGen.Daos;
import mongo.gen.MongoGen.PropAddLogDao;
import mongo.gen.MongoGen.PropAddLogDao.PropAddLogDtoCursor;
import mongo.gen.MongoGen.PropAddLogDto;
import mongo.gen.MongoGen.PropConsumeLogDao;
import mongo.gen.MongoGen.PropConsumeLogDao.PropConsumeLogDtoCursor;
import mongo.gen.MongoGen.PropConsumeLogDto;
import cn.mxz.city.City;

import com.google.common.collect.Lists;

/**
 * 道具日志
 * 
 * @author 林岑
 * 
 */
public class PropLogs {

	/**
	 * 所有道具消耗日志
	 * 
	 * @param start
	 *            起始时间
	 * @param end
	 *            结束时间
	 */
	public static List<PropConsumeLogDto> getComsumeLogs(long start, long end,
			String uname, Integer propId) {
		PropConsumeLogDao dao = Daos.getPropConsumeLogDao();
		PropConsumeLogDtoCursor find = dao.find();
		ArrayList<PropConsumeLogDto> ls = Lists.newArrayList();
		for (PropConsumeLogDto dto : find) {
			if (!uname.equals(dto.getUname())) {
				continue;
			}
			if (dto.getTime() < start) {
				continue;
			}
			if (dto.getTime() > end) {
				continue;
			}

			if (propId == null || propId.equals(-1)
					|| propId.equals(dto.getPropId())) {
				ls.add(dto);
			}
		}
		return ls;
	}

	/**
	 * 所有道具增加日志
	 * 
	 * @param start
	 *            起始时间
	 * @param end
	 *            结束时间
	 */
	public static List<PropAddLogDto> getAddLogs(long start, long end, String uname, Integer propId) {
		PropAddLogDao dao = Daos.getPropAddLogDao();
		PropAddLogDtoCursor find = dao.find();
		ArrayList<PropAddLogDto> ls = Lists.newArrayList();
		for (PropAddLogDto dto : find) {
			if (!uname.equals(dto.getUname())) {
				continue;
			}
			if (dto.getTime() < start) {
				continue;
			}
			if (dto.getTime() > end) {
				continue;
			}

			if (propId == null || propId.equals(-1)
					|| propId.equals(dto.getPropId())) {
				ls.add(dto);
			}
		}
		return ls;
	}

	public static void logComsume(int typeId, int count, City city) {
		PropConsumeLogDao dao = Daos.getPropConsumeLogDao();
		PropConsumeLogDto dto = dao.createDTO();
		dto.setCount(count);
		dto.setTime(System.currentTimeMillis());
		dto.setPropId(typeId);
		dto.setUname(city.getId());
		dao.save(dto);
	}

	public static void logAdd(int typeId, int count, City city) {
		PropAddLogDao dao = Daos.getPropAddLogDao();
		PropAddLogDto dto = dao.createDTO();
		dto.setCount(count);
		dto.setTime(System.currentTimeMillis());
		dto.setPropId(typeId);
		dto.setUname(city.getId());
		dao.save(dto);
	}
}
