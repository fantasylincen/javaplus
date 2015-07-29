function updateTankAttack(tank) {
	tank.setAttack(java.lang.Math.pow(1.15, tank.getLevel() + 35));
}