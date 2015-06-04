//package cn.mxz.mission;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import cn.mxz.util.LocationImpl;
//
//public class TestMissionMap extends AbstractMissionMap {
//
//	private class PersonImpl implements Person {
//
//		private int path;
//		private int index;
//
//		public PersonImpl(int path, int index) {
//			this.path = path;
//			this.index = index;
//		}
//
//		@Override
//		public void moveTo(int path, int index) {
//			this.path = path;
//			this.index = index;
//		}
//
//		@Override
//		public Location getLocation() {
//			return new LocationImpl(path, index);
//		}
//	}
//
//	private int i = 1;
//	private int lastPath = -1;
//	
//	private List<MapNode> allNode = new ArrayList<>();
//	
//	private Person person;
//	
//	public TestMissionMap() {
//		
//		person = new PersonImpl(1, 1);
//		
//		add(new TestMapNode(2,1,0));
//		add(new TestMapNode(1,1,0));
//		add(new TestMapNode(1,1,0));
//		add(new TestMapNode(3,1,2,3));
//
//		
//		add(new TestMapNode(1,2,0));
//		add(new TestMapNode(1,2,0));
//		add(new TestMapNode(2,2,0));
//		add(new TestMapNode(1,2,0));
//		add(new TestMapNode(1,2,0));
//		add(new TestMapNode(2,2,0));
//		add(new TestMapNode(1,2,0));
//		add(new TestMapNode(1,2,0));
//		add(new TestMapNode(3,2,4,5));
//
//		
//		add(new TestMapNode(1,3,0));
//		add(new TestMapNode(1,3,0));
//		add(new TestMapNode(3,3,4,5));
//
//		
//		add(new TestMapNode(1,4,0));
//		add(new TestMapNode(1,4,0));
//		add(new TestMapNode(2,4,0));
//		add(new TestMapNode(1,4,0));
//		add(new TestMapNode(1,4,0));
//		add(new TestMapNode(2,4,6));
//
//		
//		add(new TestMapNode(1,5,0));
//		add(new TestMapNode(1,5,0));
//		add(new TestMapNode(2,5,0));
//		add(new TestMapNode(1,5,0));
//		add(new TestMapNode(1,5,0));
//		add(new TestMapNode(2,5,0));
//		add(new TestMapNode(1,5,0));
//		add(new TestMapNode(1,5,0));
//		add(new TestMapNode(2,5,0));
//		add(new TestMapNode(1,5,0));
//		add(new TestMapNode(1,5,0));
//		add(new TestMapNode(2,5,0));
//		add(new TestMapNode(1,5,0));
//		add(new TestMapNode(1,5,0));
//		add(new TestMapNode(2,5,0));
//		add(new TestMapNode(1,5,0));
//		add(new TestMapNode(1,5,0));
//		add(new TestMapNode(2,5,6));
//
//		
//		add(new TestMapNode(1,6,0));
//		add(new TestMapNode(-2,6,0));
//		add(new TestMapNode(1,6,0));
//	}
//	
//	private void add(TestMapNode n) {
//		if(n.getLocation().getPath() != lastPath) {
//			i = 1;
//		}
//		lastPath = n.getLocation().getPath();
//		n.index = i++;
//		allNode.add(n);
//	}
//
//	private class TestMapNode implements MapNode {
//		
//		private int path;
//		private int index;
//		private boolean isBig;
//		private List<Integer> nexts = new ArrayList<>();
//
//		public TestMapNode(int type, int path, int... next) {
//			isBig = type == 2;
//			this.path = path;
//			for (int i : next) {
//				nexts.add(i);
//			}
//		}
//
//		@Override
//		public boolean hasNext() {
//			return !next().isEmpty();
//		}
//
//		@Override
//		public List<MapNode> next() {
//			
//			List<MapNode> ms = new ArrayList<>();
//			
//			for (MapNode n : allNode) {
//				if(n.getLocation().getPath() == getLocation().getPath() && n.getLocation().getIndex() - 1 == getLocation().getIndex()) {
//					ms.add(n);
//					return ms;
//				}
//			}
//			
//			for (Integer path : nexts) {
//				for (MapNode n : allNode) {
//					if(n.getLocation().getPath() == path) {
//						ms.add(n);
//						break;
//					}
//				}
//			}
//			
//			return ms;
//		}
//
//		@Override
//		public Location getLocation() {
//			return new LocationImpl(path, index);
//		}
//
//		@Override
//		public boolean isBig() {
//			return isBig;
//		}
//
//		@Override
//		public String toString() {
//			return "[" + path + "," + index + "]";
//		}
//
//		@Override
//		public List<MapNode> getNearest() {
//			return getBigStones();
//		}
//	}
//
//	@Override
//	public MapNode getEnd() {
//		MapNode node = getStart();
//
//		while (node.hasNext()) {
//			List<MapNode> next = node.next();
//			node = next.get(0);
//		}
//
//		return node;
//	}
//
//	@Override
//	public MapNode getStart() {
//		return allNode.get(0);
//	}
//
//	@Override
//	public List<DemonBean> getDemons() {
//		List<DemonBean> ls = new ArrayList<>();
//
//		ls.add(new DemonBean(1, 1, 1));
//		ls.add(new DemonBean(4, 2, 1));
//
//		return ls;
//	}
//
//	@Override
//	public List<MapBoxImpl> getBoxes() {
//		List<MapBoxImpl> ls = new ArrayList<>();
//
//		ls.add(new MapBoxImpl(1, 1, 1));
//
//		return ls;
//	}
//
//	@Override
//	public Person getPerson() {
//		return person;
//	}
//
//	@Override
//	public int getId() {
//		return 1;
//	}
//
//	@Override
//	public void remove(MapBox box) {
//		// TODO 林岑
//		
//	}
//
//	@Override
//	public void remove(MapDemon d) {
//		// TODO 林岑
//	}
//
//	@Override
//	public MapDemon getDemon(int path, int index) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//}
