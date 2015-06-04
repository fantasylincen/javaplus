package cn.mxz.util;

import java.util.List;

import cn.mxz.mission.old.Location;
import cn.mxz.mission.old.LocationAble;

public class LocationImpl implements Location {

	private int index;

	private int path;

	public LocationImpl(int path, int index) {
		this.path = path;
		this.index = index;
	}



	public void setIndex(int index) {
		this.index = index;
	}

	public void setPath(int path) {
		this.path = path;
	}

	@Override
	public int getPath() {
		return path;
	}

	@Override
	public int getIndex() {
		return index;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + index;
		result = prime * result + path;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final LocationImpl other = (LocationImpl) obj;
		if (index != other.index)
			return false;
		if (path != other.path)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return path + "," + index;
	}



	@Override
	public boolean isIn(List<? extends LocationAble> ls) {

		for (LocationAble l : ls) {

			if(l.equals(this)) {

				return true;
			}
		}

		return false;
	}
}
