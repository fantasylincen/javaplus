package cn.vgame.a.events;
		add(cn.vgame.a.turntable.KuCunToZeroEvent.class, new cn.vgame.a.turntable.KuCunToZeroResetHuiBaoLv());
		add(cn.vgame.a.turntable.KuCunUpdateEvent.class, new cn.vgame.a.turntable.KuCunUpdateChuFaShouFen());
		add(cn.vgame.a.account.SelectRoleEnterGameEvent.class, new cn.vgame.a.account.SelectRoleEnterGameCheckHasFengHao());
		add(cn.vgame.a.account.SelectRoleEnterGameEvent.class, new cn.vgame.a.account.SelectRoleEnterGameCheckIsRobot());
		add(cn.vgame.a.account.SelectRoleEnterGameEvent.class, new cn.vgame.a.account.SelectRoleEnterGameMarkOnline());
		add(cn.vgame.a.account.SelectRoleEnterGameEvent.class, new cn.vgame.a.account.SelectRoleEnterGameSaveLastLoginTime());
		add(cn.vgame.a.account.SelectRoleEnterGameEvent.class, new cn.vgame.a.account.SelectRoleEnterGameSaveRole());
