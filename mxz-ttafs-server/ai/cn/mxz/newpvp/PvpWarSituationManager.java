package cn.mxz.newpvp;

import cn.mxz.protocols.user.battle.WarSituationP.WarSituationPro;

interface PvpWarSituationManager {


	int save(WarSituationPro situation, String defenderId, String challengerId);

}
