function updateAddHpController(stone) {
	var space = new org.javaplus.game.common.util.Space(0.3);
	stone.setAddHpSpace(space);
	stone.setAddEverySpace(stone.getLevel() * 1000);
}