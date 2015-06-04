package cn.mxz.mission.old.person;

import cn.mxz.mission.old.Person;

/**
 * 地图上走动的人
 * @author 林岑
 */

public class PersonImpl /*extends EventDispatcherImpl */implements Person {

	private int path;

	private int index;

//	private City	city;

	public PersonImpl(final int path, final int index/*, City city*/) {

		this.path = path;

		this.index = index;
//		this.city = city;
	}

	@Override
	public int getPath() {
		return path;
	}

	@Override
	public int getIndex() {
		return index;
	}

//	@Override
//	public void moveTo(final int path, final int index) {
//
//		dispatchEvent(new PersonBeforeMoveEvent());
//
//		this.path = path;
//
//		this.index = index;
//
//		dispatchEvent(new PersonAfterMoveEvent());
//	}

//	@Override
//	public Location getLocation() {
//
//		return new LocationImpl(path, index);
//	}
//
//	@Override
//	public City getCity() {
//		return city;
//	}
}
