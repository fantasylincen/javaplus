package cn.javaplus.clickscreen.tank;

public class TankConfig {

	double attack;
	int tankId;
	double hp;
	private double coin;
	private double bulletSpeed;
	private double fireSpace;

	public double getAttack() {
		return attack;
	}

	public void setAttack(double attack) {
		this.attack = attack;
	}

	public int getTankId() {
		return tankId;
	}

	public void setTankId(int tankId) {
		this.tankId = tankId;
	}

	public double getHp() {
		return hp;
	}

	public void setHp(double hp) {
		this.hp = hp;
	}

	public void setCoin(double coin) {
		this.coin = coin;
	}

	public double getCoin() {
		return coin;
	}

	public void setBulletSpeed(double bulletSpeed) {
		this.bulletSpeed = bulletSpeed;
	}
	
	public double getBulletSpeed() {
		return bulletSpeed;
	}

	public double getFireSpace() {
		return fireSpace;
	}

	public void setFireSpace(double fireSpace) {
		this.fireSpace = fireSpace;
	}

}
