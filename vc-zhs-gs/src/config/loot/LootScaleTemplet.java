package config.loot;

public class LootScaleTemplet {

	private final int 			id;
	
	private final float			scale;
	
	public LootScaleTemplet(int nid, float scale) {
		id 			= nid;
		this.scale 	= scale;
	}

	public int getId(){
		return id;
	}
	
	public float getScale(){
		return scale;
	}
}
