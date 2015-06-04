package cn.mxz.bag;

import cn.mxz.PropTemplet;
import cn.mxz.prop.AbstractProp;
import cn.mxz.prop.Prop;
import cn.mxz.prop.PropTempletFactory;
import db.domain.UserTemporaryGrid;


public class TempStuffImpl extends AbstractProp implements Prop {

	private UserTemporaryGrid tempGrid;

	public TempStuffImpl(UserTemporaryGrid userTemporaryGrid) {
		this.tempGrid = userTemporaryGrid;
	}

	@Override
	public int getTypeId() {
		return tempGrid.getTypeid();
	}

	@Override
	public int getAddUp() {
		return getTemplet().getAddUp();
	}

	private PropTemplet getTemplet() {
		return PropTempletFactory.get(getTypeId());
	}

	@Override
	public String getName() {
		return getTemplet().getName();
	}

	@Override
	public int getLevel() {
		return 1;
	}
}
