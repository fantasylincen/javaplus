//package cn.mxz.mission.old;
//
//import java.util.List;
//
//import cn.mxz.ConsumableTemplet;
//import cn.mxz.ConsumableTempletConfig;
//import cn.mxz.base.prize.Prize;
//import cn.mxz.base.prize.PrizeSender;
//import cn.mxz.user.City;
//import cn.mxz.util.Factory;
//import cn.mxz.util.LocationImpl;
//
//
//public class MapMoneyImpl implements MapMoney {
//
//	private int id;
//
//	private Location location;
//
//	private int startStory;
//
//	private int endStory;
//
//	private City city;
//
//	public MapMoneyImpl(int path, int index, int id, int startStory, int endStory, City city) {
//
//		setLocation(new LocationImpl(path, index));
//
//		this.id = id;
//
//		this.startStory = startStory;
//
//		this.endStory = endStory;
//
//		this.city = city;
//	}
//
//	private void setLocation(Location location) {
//		this.location = location;
//	}
//
//	@Override
//	public int getId() {
//
//		return id;
//	}
//
//	@Override
//	public Location getLocation() {
//
//		return location;
//	}
//
//	@Override
//	public int open() {
//
//		ConsumableTemplet temp = ConsumableTempletConfig.get(140004);
//
//		PrizeSender ps = PrizeSenderFactory.getPrizeSender();
//
//		List<Prize> send = ps.send(city.getPlayer(), temp);
//
//		int count = 0;
//
//		for (Prize prize : send) {
//
//			count += prize.getCount();
//		}
//
//		return count * city.getPlayer().getLevel();
//	}
//
//	@Override
//	public int getStartStory() {
//
//        return startStory;
//	}
//
//	@Override
//	public int getEndStory() {
//
//		return endStory;
//	}
//
//	@Override
//	public String toString() {
//		return "MapMoneyImpl [id=" + id + ", location=" + location
//				+ ", startStory=" + startStory + ", endStory=" + endStory + "]";
//	}
//
//}
