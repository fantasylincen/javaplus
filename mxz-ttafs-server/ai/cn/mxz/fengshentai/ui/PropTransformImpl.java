package cn.mxz.fengshentai.ui;

import cn.mxz.city.City;
import cn.mxz.fengshentai.IPropTransform;
import cn.mxz.fengshentai.PropItem;

public class PropTransformImpl implements IPropTransform {

	private PropItem	item;
	/**
	 * 背包里面已经拥有的数量
	 */
	private int			count;		
	
	public PropTransformImpl(PropItem propItem, City user) {
		this.item = propItem;
		count = user.getBagAuto().getCount( item.getPropId() );
	}

	@Override
	public int getBuyCount() {
		return item.getBuyCount();
	}

	@Override
	public int getPropId() {
		return item.getPropId();
	}

	@Override
	public int getRemainSec() {
		return item.getRemainSec();
	}

	@Override
	public int getCount() {
		return count;
	}

}
