package cn.mxz.bag;

import cn.mxz.city.City;
import cn.mxz.city.CityFactory;
import cn.mxz.prop.PropTempletFactory;
import db.domain.UserPiecesGrid;

public class PiecesGridImpl implements Grid {
	private UserPiecesGrid	grid;

	@Override
	public int getFree() {
		if (isEmpty()) {
			return 0;
		} else {
			return PropTempletFactory.get(grid.getTypeid()).getAddUp() - getCount();
		}
	}

	private boolean isEmpty() {

		return grid.getCount() == 0;
	}

	@Override
	public boolean isFull() {

		if (isEmpty()) {
			return false;
		}
		return getFree() == 0;
	}

	@Override
	public int getCount() {
		return grid.getCount();
	}

	@Override
	public int getGridId() {
		return grid.getGridId();
	}

	@Override
	public int getTempletId() {
		return grid.getTypeid();
	}

	@Override
	public int getPropType() {

		return PropTempletFactory.getPropType(getTempletId());
	}

	public void setDto(UserPiecesGrid grid) {
		this.grid = grid;
	}
	
	public UserPiecesGrid getDto() {
		return grid;
	}

	public City getCity() {
		String uname = grid.getUname();
		return CityFactory.getCity(uname);
	}

}
