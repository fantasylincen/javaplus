package cn.mxz.bag;

import cn.mxz.PropTemplet;
import cn.mxz.city.City;
import cn.mxz.prop.PropTempletFactory;
import db.domain.UserGrid;

class GridImpl implements Grid {


	private UserGrid dto;

	private City city;

	public UserGrid getDto() {
		return dto;
	}

	public void setDto(UserGrid dto) {
		this.dto = dto;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return getTempletId() + ":" + getCount();
	}

	@Override
	public boolean isFull() {
		return getFree() == 0;
	}

	@Override
	public int getCount() {
		return dto.getCount();
	}

	@Override
	public int getGridId() {
		return dto.getGridId();
	}

	@Override
	public int getFree() {

		if(getCount() == 0) {

			return 0;
		}

		PropTemplet temp = PropTempletFactory.get(getTempletId());

		return temp.getAddUp() - getCount();
	}

	@Override
	public int getTempletId() {

		return dto.getTypeid();
	}

	@Override
	public int getPropType() {

		return PropTempletFactory.getPropType(dto.getTypeid());
	}
}