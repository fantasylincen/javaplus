package cn.vgame.a.turntable;

import cn.vgame.a.turntable.swt.SwitchAll;

public interface ResultGenerator {

	Result generateReward(SwitchAll switchs);

	int getRandomXNumber();

}
