package cn.javaplus.shhz.components.buildings;


public class BuildingMoveController {
	private MoveOnGridListener moveListener;
	private TapListener tapListener;

	private BuildingSelectEffect effect;
	private Building building;

	private boolean isStandable;
	private float xSaved;
	private float ySaved;

	public BuildingMoveController(Building building) {
		this.building = building;
		tapListener = new TapListener(building);
		moveListener = new MoveOnGridListener(building);

		building.addListener(tapListener);
		building.addListener(moveListener);
		enable();
		effect = new BuildingSelectEffect(building);
		effect.toStandableEffect();
	}

	public BuildingSelectEffect getEffect() {
		return effect;
	}

	public void updateStandStatus() {
		if (building.getMoveController().isStandable()) {
			effect.toStandableEffect();
		} else {
			effect.toUnStandableEffect();
		}
	}

	public void enable() {
		tapListener.enable();
		moveListener.enable();
	}

	public void disable() {
		tapListener.disable();
		moveListener.disable();
	}

	public MoveOnGridListener getMoveListener() {
		return moveListener;
	}

	public TapListener getTapListener() {
		return tapListener;
	}

	public void showEffect() {
		building.addAction(effect);
	}

	public boolean isStandable() {
		return isStandable;
	}

	public void setStandable(boolean isStandable) {
		this.isStandable = isStandable;
	}

	public void savePosition() {
		xSaved = building.getX();
		ySaved = building.getY();
	}

	public void moveBack() {
		building.setPosition(xSaved, ySaved);
	}
}
