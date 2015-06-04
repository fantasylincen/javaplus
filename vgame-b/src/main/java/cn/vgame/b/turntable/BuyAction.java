package cn.vgame.b.turntable;

import cn.javaplus.excel.Row;
import cn.javaplus.excel.Sheet;
import cn.vgame.b.Server;
import cn.vgame.b.account.JsonActionAfterRoleEnterGame;
import cn.vgame.b.result.ErrorResult;
import cn.vgame.share.Xml;

/**
 * 买东西
 * 
 * -----------------
 * 
 * A.正常情况: { type:购买物品类型, id:购买id  客户端发好多就原样返回, count:数量}
 * 
 * B.错误: 标准错误
 */
public class BuyAction extends JsonActionAfterRoleEnterGame {

	public class BuyResult {

		private final int type;

		public BuyResult(int type) {
			this.type = type;
		}

		/**
		 * 购买的物品类型
		 */
		public int getType() {
			return type;
		}

		/**
		 * 购买后背包里面有多少个该物品
		 */
		public int getCount() {
			return role.getBag().getCount(type);
		}

		/**
		 * 购买id
		 */
		public String getId() {
			return id;
		}
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -6619206071187476891L;

	/**
	 * 对应到 marketplace表中的id
	 */
	private String id;

	@Override
	protected Object run() {

		Xml xml = Server.getXml();
		Sheet sheet = xml.get("marketplace");
		Row row = sheet.get(id);

		if(row == null)
			throw new ErrorResult(10036).toException();
		
		int type = row.getInt("type");
		int priceAll = row.getInt("priceAll");
		int count = row.getInt("count");

		role.reduceCoin(priceAll);
		role.getBag().add(type, count);

		return new BuyResult(type);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
