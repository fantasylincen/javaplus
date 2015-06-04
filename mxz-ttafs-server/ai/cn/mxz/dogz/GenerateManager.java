package cn.mxz.dogz;

import java.util.List;

import message.S;
import cn.javaplus.util.Util;
import cn.mxz.Chipable;
import cn.mxz.bag.Bag;
import cn.mxz.bag.Grid;
import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.base.exception.SureIllegalOperationException;
import cn.mxz.city.City;

import com.google.common.collect.Lists;

/**
 * 可进行合成操作的管理器
 *
 * @author 林岑
 *
 * @param <T>
 */
public abstract class GenerateManager<T> {

	public class Prop {

		private int	count;
		private int	typeId;

		public Prop(int typeId, int count) {
			this.typeId = typeId;
			this.count = count;
		}

		public int getTypeId() {
			return typeId;
		}

		public int getCount() {
			return count;
		}
	}

	protected City	city;

	public GenerateManager(City city) {
		this.city = city;
	}

	public T generate(int id) {
		check(id);
		reduce(id);
		return add(id);
	}

	public boolean canLevelUp(int id) {
		List<Prop> props = getPropsNeed(id);
		for (Prop prop : props) {
			if (!isEnouph(prop.getTypeId(), prop.getCount())) {
				return false;
			}
		}
		return true;
	}

	protected abstract T add(int id);

	private void reduce(int id) {

		List<Prop> props = getPropsNeed(id);

		for (Prop prop : props) {
			remove(prop.getTypeId(), prop.getCount());
		}
	}

	private List<Prop> getPropsNeed(int id) {

		List<Prop> props = Lists.newArrayList();
		Chipable temp = getTemplet(id);
		String chip = temp.getChip();
		String[] split;
		try {
			split = chip.split("\\|");
		} catch (Exception e) {
			System.err.println("----------" + id + ", " + chip);
			throw Util.Exception.toRuntimeException(e);
		}
		for (String s : split) {
			String[] split2 = s.split(",");
			int tId = new Integer(split2[0]);
			int cnt = new Integer(split2[1]);

			props.add(new Prop(tId, cnt));
		}
		return props;
	}

	protected abstract Chipable getTemplet(int id);

	private void remove(int tId, int cnt) {
		Bag<Grid> bag = city.getPiecesBag();
		bag.remove(tId, cnt);
	}

	private void check(int id) {
		checkExist(id);
		checkEnouph(id);
	}

	private void checkEnouph(int id) {
		Chipable temp = getTemplet(id);
		String chip = temp.getChip();
		String[] split = chip.split("\\|");
		for (String s : split) {
			String[] split2 = s.split(",");
			int tId = new Integer(split2[0]);
			int cnt = new Integer(split2[1]);

			checkEnouph(tId, cnt);
		}
	}

	private void checkEnouph(int tId, int cnt) {
		if (!isEnouph(tId, cnt)) {
			throw new OperationFaildException(S.S10236);
		}
	}

	private boolean isEnouph(int tId, int cnt) {
		Bag<Grid> bag = city.getPiecesBag();
		int count = bag.getCount(tId);
		return count >= cnt;
	}

	private void checkExist(int id) {
		if (getTemplet(id) == null) {
			throw new SureIllegalOperationException("-对象不存在!" + id);
		}
	}

}