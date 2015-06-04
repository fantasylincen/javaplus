//package cn.mxz.mission;
//
//import java.util.List;
//
//import com.google.common.collect.Lists;
//
//import cn.mxz.base.prize.Prize;
//import cn.mxz.mission.old.MapBox;
//import cn.mxz.mission.old.MapRandomEvent;
//import cn.mxz.mission.old.Person;
//import cn.mxz.user.Player;
//
//public class MissionManagerImplTest implements IMissionManager {
//
//	private static final int[]	TEMP	= new int[]{100020, 1, 100021, 3, 100022, 7};
//
//	@Override
//	public Mission enter(int missionId) {
//		return null;
//	}
//
//	@Override
//	public int getMissionId() {
//		return 1;
//	}
//
//	@Override
//	public Object run(int path) {
//		return null;
//	}
//
//	@Override
//	public int[] getDemonCamp(int path) {
//		return TEMP;
//	}
//
//	@Override
//	public Person getPerson() {
//		return new Person() {
//			
//			@Override
//			public int getPath() {
//				return 1;
//			}
//			
//			@Override
//			public int getIndex() {
//				return 1;
//			}
//		};
//	}
//
//	@Override
//	public List<MapDemon> getDemons() {
//		return Lists.newArrayList(buildDemon());
//	}
//
//	private MapDemon buildDemon() {
//		return new MapDemon() {
//			
//			@Override
//			public int getPath() {
//				return 1;
//			}
//			
//			@Override
//			public int getIndex() {
//				return 4;
//			}
//			
//			@Override
//			public int getId() {
//				return TEMP[0];
//			}
//		};
//	}
//
//	@Override
//	public List<MapBox> getBoxes() {
//		return Lists.newArrayList(buildBox());
//	}
//
//	private MapBox buildBox() {
//		return new MapBox() {
//			
//			@Override
//			public void open(Player player) {
//			}
//			
//			@Override
//			public int getPath() {
//				return 1;
//			}
//			
//			@Override
//			public int getIndex() {
//				return 7;
//			}
//			
//			@Override
//			public int getId() {
//				return 140002;
//			}
//			
//			@Override
//			public List<Prize> getAll() {
//				return Lists.newArrayList();
//			}
//		};
//	}
//
//	@Override
//	public List<MapRandomEvent> getMapRandomEvents() {
//		return Lists.newArrayList(buildRandom());
//	}
//
//	private MapRandomEvent buildRandom() {
//		return new MapRandomEvent() {
//			
//			@Override
//			public String responseEvent() {
//				return "";
//			}
//			
//			@Override
//			public int getPath() {
//				return 1;
//			}
//			
//			@Override
//			public int getIndex() {
//				return 10;
//			}
//			
//			@Override
//			public int getId() {
//				return 9;
//			}
//		};
//	}
//
//}
