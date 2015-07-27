package cn.javaplus.clickscreen.tank;

public class TankDto {

	private int tankId;
	private int bulletId;
	private boolean isOpen;
	private double speed;
	private int level;
	private boolean isFireByTap;
	private double fireSpace;
	private double attack;

	public double getAttack() {
		return attack;
	}

	public void setAttack(double attack) {
		this.attack = attack;
	}

	public boolean isFireByTap() {
		return isFireByTap;
	}

	public void setFireByTap(boolean isFireByTap) {
		this.isFireByTap = isFireByTap;
	}

	public double getFireSpace() {
		return fireSpace;
	}

	public void setFireSpace(double fireSpace) {
		this.fireSpace = fireSpace;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public int getTankId() {
		return tankId;
	}

	public void setTankId(int tankId) {
		this.tankId = tankId;
	}

	public int getBulletId() {
		return bulletId;
	}

	public void setBulletId(int bulletId) {
		this.bulletId = bulletId;
	}

	public boolean isOpen() {
		return isOpen;
	}

	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}

}
