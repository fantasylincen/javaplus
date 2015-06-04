package cn.mxz.bag;

import cn.mxz.city.City;
import cn.mxz.prop.PropTempletFactory;
import db.domain.UserTemporaryGrid;

class TemporaryGrid implements Grid {

	private UserTemporaryGrid userTemporaryGrid;

	private City city;

	@Override
	public int getFree() {

		if(isEmpty()) {
			return 0;
		} else {
			return PropTempletFactory.get(userTemporaryGrid.getTypeid()).getAddUp() - getCount();
		}
	}

	private boolean isEmpty() {

		return userTemporaryGrid.getCount() == 0;
	}

	@Override
	public boolean isFull() {

		if(isEmpty()) {
			return false;
		}
		return getFree() == 0;
	}

	@Override
	public int getCount() {

		return userTemporaryGrid.getCount();
	}

	public UserTemporaryGrid getUserTemporaryGrid() {

		return userTemporaryGrid;
	}

	public City getCity() {
		return city;
	}

	public void setUserTemporaryGrid(UserTemporaryGrid userTemporaryGrid) {

		this.userTemporaryGrid = userTemporaryGrid;

	}

	public void setCity(City city) {
		this.city = city;
	}

	@Override
	public int getGridId() {
		return userTemporaryGrid.getGridId();
	}

	@Override
	public int getTempletId() {

		return userTemporaryGrid.getTypeid();
	}

	@Override
	public int getPropType() {

		return PropTempletFactory.getPropType(getTempletId());
	}


}