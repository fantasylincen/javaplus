package cn.mxz.mission.star;

import java.util.List;

import cn.javaplus.db.exception.FounctionNotOpenException;
import cn.javaplus.random.IntegerFetcher;
import cn.javaplus.util.Util;

public class MissionStarAll implements MissionStar {

	private List<MissionStar>	star;

	public MissionStarAll(List<MissionStar> star) {
		this.star = star;
	}

	@Override
	public int getMissionId() {
		throw new FounctionNotOpenException();
	}

	@Override
	public int getCount() {

		IntegerFetcher<MissionStar> fetcher = new IntegerFetcher<MissionStar>() {

			@Override
			public Integer get(MissionStar t) {
				return t.getCount();
			}
		};

		int[] a = Util.Collection.getArrayByOneFields(fetcher, star);
		return Util.Array.sum(a);
	}

	@Override
	public int getMax() {

		IntegerFetcher<MissionStar> fetcher = new IntegerFetcher<MissionStar>() {

			@Override
			public Integer get(MissionStar t) {
				return t.getMax();
			}
		};

		int[] a = Util.Collection.getArrayByOneFields(fetcher, star);
		return Util.Array.sum(a);
	}

	@Override
	public String toString() {
		return "MissionStarAll getCount()=" + getCount() + ", getMax()=" + getMax() + "]";
	}

}
