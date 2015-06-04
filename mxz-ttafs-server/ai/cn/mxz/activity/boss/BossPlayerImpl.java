//package cn.mxz.activity.boss;
//
//import java.util.List;
//
//import cn.mxz.user.City;
//
//public class BossPlayerImpl implements BossPlayer {
//
//	private City	city;
//
//	public BossPlayerImpl(City city) {
//		this.city = city;
//	}
//
//	@Override
//	public int getBossCountInvitedMe() {
//		List<Boss> ls = city.getBossMission().getBossList();
//		int count = 0;
//		for (Boss boss : ls) {
//			int status = boss.getStatus(city);
//			if (status == 2) {
//				count++;
//			}
//		}
//		return count;
//	}
//}
