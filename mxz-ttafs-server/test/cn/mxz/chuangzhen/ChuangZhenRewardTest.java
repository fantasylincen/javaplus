package cn.mxz.chuangzhen;

import java.util.List;

import org.junit.Test;

import com.alibaba.fastjson.JSON;

public class ChuangZhenRewardTest {

	private final class ChuangZhenRewardImpl implements ChuangZhenReward {

		@Override
		public boolean getHasBtReward() {
			return false;
		}

		@Override
		public boolean getHasBtAddition() {
			return false;
		}

		@Override
		public BattleReward getBattleReward() {
			return new BattleReward() {

				@Override
				public int getStar() {
					return 0;
				}

				@Override
				public List<PropReward> getPropReward() {
					return null;
				}

				@Override
				public int getFloorMin() {
					return 0;
				}

				@Override
				public int getFloorMax() {
					return 0;
				}

				@Override
				public int getCashEveryStar() {
					return 0;
				}
			};
		}

		@Override
		public BattleAddition getBattleAddition() {
			return new BattleAddition() {

				@Override
				public List<Addition2> getAdditions() {
					return null;
				}
			};
		}
	}

	@Test
	public void test() {
		ChuangZhenReward c = new ChuangZhenRewardImpl();

		Object o = JSON.toJSON(c);
		System.out.println(o);
	}

}
