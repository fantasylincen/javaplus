package cn.mxz.prop.equipment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import cn.javaplus.exception.SQLRuntimeException;
import cn.javaplus.math.Scope;
import cn.javaplus.util.Closer;
import cn.javaplus.util.Util;
import cn.mxz.FormationTemplet;
import cn.mxz.FormationTempletConfig;
import cn.mxz.SkillTemplet;
import cn.mxz.SkillTempletConfig;
import cn.mxz.StuffTemplet;
import cn.mxz.StuffTempletConfig;
import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.city.PlayerProperty;
import cn.mxz.newpvp.PvpPlaceImpl;
import cn.mxz.newpvp.PvpPlayer;
import cn.mxz.script.ScriptOld;

import com.google.common.collect.Lists;

import db.GameDB;

public class UserRandom {

	// public List<String> random(int stuffId, int shenJia) {
	//
	// PreparedStatement ps = null;
	//
	// ResultSet rs = null;
	//
	// Connection connection = GameDB.getInstance().getConnection();
	//
	// List<String> ls = new ArrayList<String>();
	//
	// Scope s = ScriptOld.equipment.getSnatchScope(shenJia);
	//
	// try {
	// String field = "v" + (PlayerProperty.SHEN_JIA_CACHE.getValue() + 1);
	//
	// String sql =
	// "SELECT DISTINCT user_data.uname, RAND() AS random FROM user_data LEFT JOIN user_grid ON user_data.uname = user_grid.uname WHERE user_data."
	// + field + " >= ? AND user_data." + field +
	// " <= ? AND user_grid.typeId = ? ORDER BY random LIMIT ?";
	//
	// ps = connection.prepareStatement(sql);
	//
	// // 1 最小值 2 最大值 3 id 4 数量
	// ps.setInt(1, (int) s.getMin());
	// ps.setInt(2, (int) s.getMax());
	// ps.setInt(3, stuffId);
	// ps.setInt(4, 4);
	//
	// rs = ps.executeQuery();
	//
	// while (rs.next()) {
	// ls.add(rs.getString("uname"));
	// }
	//
	// } catch (SQLException e) {
	//
	// throw new SQLRuntimeException(e);
	//
	// } finally {
	//
	// Closer.close(rs, ps);
	//
	// Closer.close(connection);
	// }
	//
	// while(ls.size() > 3) {
	// ls.remove(0);
	// }
	// return ls;
	// }

	public class Cpt implements Comparator<String> {

		private Scope scope;

		public Cpt(Scope scope) {
			this.scope = scope;
		}

		@Override
		public int compare(String u1, String u2) {
			City c1 = CityFactory.getCity(u1);
			City c2 = CityFactory.getCity(u2);
			int s1 = c1.getPlayer().get(PlayerProperty.SHEN_JIA_CACHE);
			int s2 = c2.getPlayer().get(PlayerProperty.SHEN_JIA_CACHE);

			long mid = scope.getMax() / 2 + scope.getMin() / 2;

			s1 = (int) Math.abs(mid - s1);
			s2 = (int) Math.abs(mid - s2);
			return s1 - s2;
		}

	}

	private static final String SQL = Util.File
			.getContent("res/db/select_snatch_users.sql");

	public List<String> random(City city, int stuffId, int shenJia) {

		String sql1 = SQL.replaceAll("TABLE_NAME", "user_grid");
		String sql2 = SQL.replaceAll("TABLE_NAME", "user_pieces_grid");

		// String sql =
		// "SELECT DISTINCT user_data.uname, RAND() AS random FROM user_data LEFT JOIN user_grid ON user_data.uname = user_grid.uname WHERE user_grid.typeId = ? ORDER BY random LIMIT ?";
		// String sql2 =
		// "SELECT DISTINCT user_data.uname, RAND() AS random FROM user_data LEFT JOIN user_pieces_grid ON user_data.uname = user_pieces_grid.uname WHERE user_pieces_grid.typeId = ? ORDER BY random LIMIT ?";

		List<Integer> stuffIds = getIds(stuffId);
		List<String> ls = query(stuffId, stuffIds, sql1, city.getId());
		ls.addAll(query(stuffId, stuffIds, sql2, city.getId()));

		Scope s = ScriptOld.equipment.getSnatchScope(shenJia);
		Collections.sort(ls, new Cpt(s));

		Util.Collection.upset(ls);

		while (ls.size() > 3) {
			ls.remove(0);
		}
		return ls;
	}

	private List<Integer> getIds(int stuffId) {
		StuffTemplet temp = StuffTempletConfig.get(stuffId);
		int id = temp.getArticleId();

		{
			FormationTemplet t = FormationTempletConfig.get(id);
			if (t != null) {
				return getIds(t.getChip());
			}
		}
		{
			SkillTemplet tt = SkillTempletConfig.get(id);
			return getIds(tt.getChip());
		}
	}

	private List<Integer> getIds(String chip) {
		String[] all = chip.split("\\|");
		List<Integer> ls = Lists.newArrayList();
		for (String s : all) {
			ls.add(getId(s));
		}
		fill5(ls);
		return ls;
	}

	private void fill5(List<Integer> ls) {
		while(ls.size() < 5 ) {
			ls.add(-3);
		}
	}

	private Integer getId(String s) {
		String[] ss = s.split(",");
		return new Integer(ss[0]);
	}

	private List<String> query(int stuffId, List<Integer> stuffIds, String sql, String uname) {
		PreparedStatement ps = null;

		ResultSet rs = null;

		Connection connection = GameDB.getInstance().getConnection();

		List<String> ls = new ArrayList<String>();

		try {

			ps = connection.prepareStatement(sql);
			int i = 1;
			for (Integer id : stuffIds) {
				ps.setInt(i, id);
				i++;
			}
			ps.setString(6, uname);
			ps.setInt(7, stuffId);

			rs = ps.executeQuery();

			while (rs.next()) {
				ls.add(rs.getString("uname"));
			}

		} catch (SQLException e) {

			throw new SQLRuntimeException(e);

		} finally {

			Closer.close(rs, ps);

			Closer.close(connection);
		}
		return ls;
	}

	public List<String> randomByCount(int shenJia, int robotCount) {
		Scope s = ScriptOld.equipment.getSnatchScope(shenJia);
		int min = (int) s.getMin();
		int max = (int) s.getMax();

		return random(min, max, robotCount);
	}

	// public static void main(String[] args) {
	// ArrayList<Integer> ls = Lists.newArrayList(1,2,3,4);
	//
	// System.out.println(ls.subList(0, 1));
	// }

	/**
	 * @param min
	 *            最小战斗力
	 * @param max
	 *            最大战斗力
	 * @param robotCount
	 * @return
	 */
	public List<String> random(int min, int max, int robotCount) {

		List<PvpPlayer> ps = PvpPlaceImpl.getInstance().getAll();

		ps = Lists.newArrayList(ps);

		Iterator<PvpPlayer> it = ps.iterator();

		while (it.hasNext()) {
			PvpPlayer p = it.next();

			if (p.getShenJia() < min || p.getShenJia() > max) {
				it.remove();
			}
		}

		Util.Collection.upset(ps);

		return parse(ps.subList(0, robotCount));

		// PreparedStatement ps = null;
		//
		// ResultSet rs = null;
		//
		// Connection connection = GameDB.getInstance().getConnection();
		//
		// List<String> ls = new ArrayList<String>();
		//
		// try {
		// String field = "v" + (PlayerProperty.SHEN_JIA_CACHE.getValue() + 1);
		//
		// String sql =
		// "SELECT uname, RAND() AS random FROM user_data WHERE user_data." +
		// field + " >= ? AND user_data." + field +
		// " <= ? ORDER BY random LIMIT ?";
		//
		// ps = connection.prepareStatement(sql);
		//
		// // 1 最小值 2 最大值 34 数量
		// ps.setInt(1, min);
		// ps.setInt(2, max);
		// ps.setInt(3, robotCount);
		//
		// rs = ps.executeQuery();
		//
		// while (rs.next()) {
		// ls.add(rs.getString("uname"));
		// }
		//
		// } catch (SQLException e) {
		//
		// throw new SQLRuntimeException(e);
		//
		// } finally {
		//
		// Closer.close(rs, ps);
		//
		// Closer.close(connection);
		// }
		//
		// return ls;
	}

	private List<String> parse(List<PvpPlayer> subList) {
		List<String> ls = Lists.newArrayList();
		for (PvpPlayer p : subList) {
			ls.add(p.getCity().getId());
		}
		return ls;

	}

}
