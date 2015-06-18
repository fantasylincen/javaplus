package cn.vgame.a.turntable;

import cn.javaplus.log.Log;
import cn.vgame.a.Server;
import cn.vgame.a.account.IRole;
import cn.vgame.a.account.JsonActionAfterRoleEnterGame;
import cn.vgame.a.turntable.swt.ISwitchs;
import cn.vgame.a.zhuang.Zhuang;
import cn.vgame.a.zhuang.ZhuangManager;

/**
 * 获取桌面数据
 * -----------------
 * 
 * A.正常情况:
 * 	{
 * 		cd 开奖CD,
 * 		id 回合id,
 * 		long 彩金数量caiJin,
 * 		zhuangCoin:庄家金豆,
 * 		zhuangNick:庄家昵称,
 * 		switchs: { 压注情况
 * 			a,
 * 			b,
 * 			c,
 * 			d,
 * 			e,
 * 			f,
 * 			g,
 * 			h,
 * 			i,
 * 			j,
 * 			k,
 * 			l
 * 		}
 * 		
 * 		xs: { 所有开关的倍率
 * 			a,
 * 			b,
 * 			c,
 * 			d,
 * 			e,
 * 			f,
 * 			g,
 * 			h,
 * 			i,
 * 			j,
 * 			k,
 * 			l
 * 		}
 * 	}
 * 
 * B.错误:
 *  标准错误
 */
public class GetAllSwitchsAction extends JsonActionAfterRoleEnterGame {

	public interface Xs {

		/**
		 * A 2 飞禽	倍率
		 */
		int getA();

		/**
		 * B 24 银鲨鱼	倍率
		 */
		int getB();

		/**
		 * C 48 金鲨鱼	倍率
		 */
		int getC();

		/**
		 * D 2 走兽	倍率
		 */
		int getD();

		/**
		 * E 6 燕子	倍率
		 */
		int getE();

		/**
		 * F 8 鸽子	倍率
		 */
		int getF();

		/**
		 * G 8 孔雀	倍率
		 */
		int getG();

		/**
		 * H 12 老鹰	倍率
		 */
		int getH();

		/**
		 * I 12 狮子	倍率
		 */
		int getI();

		/**
		 * J 8 熊猫	倍率
		 */
		int getJ();

		/**
		 * K 8 猴子	倍率
		 */
		int getK();

		/**
		 * L 6 兔子	倍率
		 */
		int getL();
	}

	public static class GetTableDataResult {
		
		private final IRole role;

		public long getCd() {
			long cd = Turntable.getInstance().getCd();
			Log.d(role.getId(), role.getNick(), "cd", cd);
			return cd;
		}
		
		public GetTableDataResult(IRole role) {
			this.role = role;
		}

		public ISwitchs getSwitchs() {
			ISwitchs switchs = Turntable.getInstance().getSwitchs();
//			Log.d("back", JSON.toJSONString(switchs));
			return switchs;
		}
		
		/**
		 * 倍率
		 * @return
		 */
		public Xs getXs() {
			return Turntable.getInstance().getXs();
		}
		
		public long getCaiJin () {
			return Turntable.getInstance().getCaiJin();
		}
		
		public String getId() {
			return Turntable.getInstance().getId();
		}
		
		public GetHistoryResult getHistory() {
			return Turntable.getInstance().getHistory();
		}
		
		public long getLaBa() {
			return role.getLaBa();
		}

		/**
		 * 当前庄家金币
		 */
		public long getZhuangCoin() {
			ZhuangManager manager = Server.getZhuangManager();
			Zhuang z = manager.getZhuang();
			if(z == null)
				return 0;
			return z.getCoin();
		}

		/**
		 * 当前庄家昵称
		 */
		public String getZhuangNick() {
			ZhuangManager manager = Server.getZhuangManager();
			Zhuang z = manager.getZhuang();
			if(z == null)
				return null;
			return z.getNick();
		}

		/**
		 * 当前庄家ID
		 */
		public String getZhuangId() {
			ZhuangManager manager = Server.getZhuangManager();
			Zhuang z = manager.getZhuang();
			if(z == null)
				return null;
			return z.getRoleId();
		}
		

		/**
		 * 当前庄家剩余当庄回合数
		 */
		public int getZhuangRemainTimes() {
			ZhuangManager manager = Server.getZhuangManager();
			Zhuang z = manager.getZhuang();
			if(z == null)
				return 0;
			return z.getRemainTimes();
		}
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 2448719226359085389L;

	@Override
	public Object run() {

		return new GetTableDataResult(role);
	}

}
