package cn.mxz.base.handler;

import java.util.Set;

import cn.mxz.base.task.Environment;
import cn.mxz.handler.PacketDefine;

import com.google.common.collect.Sets;
import com.lemon.commons.socket.ISocket;

public class EnvironmentImpl implements Environment {

	private int packetId;
	private ISocket	socket;
	private static Set<Integer>	set;

	public EnvironmentImpl(ISocket socket, int packetId) {
		this.socket = socket;
		this.packetId = packetId;

		initPacketExcept();
	}

	/**
	 * 任务检查排除列表
	 */
	private void initPacketExcept() {
		if(set == null) {

			set = Sets.newHashSet();

			set.add(PacketDefine.GuideMark);
			set.add(PacketDefine.ActivityGetData);
			set.add(PacketDefine.BagGetData);
			set.add(PacketDefine.BagGetTempBagData);
			set.add(PacketDefine.BossGetMissionData);
			set.add(PacketDefine.FishGetData);
			set.add(PacketDefine.LevelUpRewardGetData);
			set.add(PacketDefine.LoginRewardGetContinuousDay);
			set.add(PacketDefine.NoticeGetData);
			set.add(PacketDefine.PracticeGetData);
			set.add(PacketDefine.RegisterGetData);
			set.add(PacketDefine.ShopGetData);
			set.add(PacketDefine.UserGetData);
			set.add(PacketDefine.CornucopiaGetData);
			set.add(PacketDefine.CoronaGetData);
			set.add(PacketDefine.DajiGetData);

			set.add(PacketDefine.InitAccess);
			set.add(PacketDefine.InitCreate);
			set.add(PacketDefine.InitCreateUser);
			set.add(PacketDefine.InitGetRandomNick);
			set.add(PacketDefine.InitResetUser);
			set.add(PacketDefine.InitSetNick);
			set.add(PacketDefine.InitSetUserType);
			set.add(PacketDefine.MissionGetMissionData);
			set.add(PacketDefine.CornucopiaGetData);
			set.add(PacketDefine.CoronaGetData);
			set.add(PacketDefine.DajiGetData);
			set.add(PacketDefine.FishGetData);
			set.add(PacketDefine.InviteGetData);
			set.add(PacketDefine.LevelUpRewardGetData);
			set.add(PacketDefine.NewFormationGetData);
			set.add(PacketDefine.NoticeGetData);
			set.add(PacketDefine.PracticeGetData);
			set.add(PacketDefine.RechargeFeedbackGetData);
			set.add(PacketDefine.RegisterGetData);
			set.add(PacketDefine.ShopGetData);
			set.add(PacketDefine.UserGetData);
			set.add(PacketDefine.VipCardGetData);
			set.add(PacketDefine.VipGetData);
			set.add(PacketDefine.YunyouGetData);
			set.add(PacketDefine.ZanGetData);
			set.add(PacketDefine.BagGetTempBagData);
			set.add(PacketDefine.BossGetMissionData);
			set.add(PacketDefine.FighterGetXianShiData);
			set.add(PacketDefine.MissionGetChallengeData);
			set.add(PacketDefine.MissionGetMissionData);
			set.add(PacketDefine.PvpGetMyData);
			set.add(PacketDefine.ShenMoGetMyData);
			set.add(PacketDefine.UserGetUserLevelUpData);
		}
	}

//	public static void main(String[] args) {
//		Field[] fields = PacketDefine.class.getFields();
//		for (Field field : fields) {
//			if(field.getName().matches(".*[g|G]et.*Data")) {
//				System.out.println("set.add(PacketDefine." + field.getName() + ");");
//			}
//		}
//	}

	@Override
	public int getPacketId() {

		return packetId;
	}

	@Override
	public ISocket getSocket() {
		return socket;
	}

	@Override
	public boolean needCheck() {

		return !set.contains(packetId);
	}
}
