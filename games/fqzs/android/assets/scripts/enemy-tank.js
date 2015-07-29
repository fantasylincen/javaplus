function createEnemyTank(config) {
	if (app.getDb().isBoss()) {
		createBossTankConfig(config);
	} else {
		createNormalTankConfig(config);
	}
}

function createNormalTankConfig(config) {
	var mission = app.getDb().getSelectedMission();
	var secondaryMission = app.getDb().getSecondaryMission();
	
	config.setAttack(java.lang.Math.pow(1.15, mission + 35));
	config.setHp(java.lang.Math.pow(1.15, mission + 35));
	config.setCoin(java.lang.Math.pow(1.15, mission + 35));
	config.setTankId(app.getDb().getSecondaryMission());
	config.setBulletSpeed(30);
	config.setFireSpace(0.8);
}

function createBossTankConfig(config) {
	var mission = app.getDb().getSelectedMission();
	var secondaryMission = app.getDb().getSecondaryMission();

	config.setAttack(java.lang.Math.pow(1.15, mission + 35));
	config.setHp(java.lang.Math.pow(1.15, mission + 35));
	config.setCoin(java.lang.Math.pow(1.15, mission + 35));
	config.setTankId(app.getDb().getSecondaryMission());
	config.setBulletSpeed(43);
	config.setFireSpace(0.5);
}
