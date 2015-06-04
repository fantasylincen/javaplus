package cn.mxz.mission.old;

import cn.mxz.util.LocationImpl;


public class MapStoryImpl implements MapStory {

	private Location location;

	private int startStory;

	private int endStory;
	
	public MapStoryImpl(int path, int index, int startStory, int endStory) {
		
		setLocation(new LocationImpl(path, index));
			
		this.startStory = startStory;
		
		this.endStory = endStory;
	}
	
	private void setLocation(Location location) {
		
		this.location = location;
	}
	
	@Override
	public Location getLocation() {

		return location;
	}

	@Override
	public int getStartStory() {
		
		return startStory;
	}

	@Override
	public int getEndStory() {
	
		return endStory;
	}

}
