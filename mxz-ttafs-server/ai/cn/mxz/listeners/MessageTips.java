package cn.mxz.listeners;

import message.S;
import cn.mxz.city.City;
import cn.mxz.events.AfterRequestSuccessEvent;
import cn.mxz.events.Listener;
import cn.mxz.handler.PacketDefine;
import cn.mxz.market.Goods;
import cn.mxz.temp.TempKey;
import define.D;

//操作成功提示
public class MessageTips implements Listener<AfterRequestSuccessEvent> {

	@Override
	public void onEvent(AfterRequestSuccessEvent event) {
		City user = event.getUser();

		if (user == null) {
			return;
		}

		int packetId = event.getPacketId();

		onSuccess(user, packetId);
	}

	public void onSuccess(City user, int packetId) {

		switch (packetId) {

		case PacketDefine.PracticeCareFor:

			sendMessage(user, S.S10145);

			break;

		case PacketDefine.UserShangxiangSendSX:

			int physicalNoon = D.PHYSICAL_NOON;

			sendMessage(user, S.S10146, physicalNoon);

			break;

		case PacketDefine.RegisterRegist:

			sendMessage(user, S.S10148);

			break;

		case PacketDefine.PracticeBysf:
		case PacketDefine.PracticeBnhh:
		case PacketDefine.PracticeQmsa:
		case PacketDefine.PracticeAccept:
		case PacketDefine.PracticeRefuse:
		case PacketDefine.PracticeRequest:
		case PacketDefine.SystemRecharge:
			sendMessage(user, S.S1);

			break;
		case PacketDefine.FriendAddRequest:
			sendMessage(user, S.S10067);
			break;

		case PacketDefine.PvpBuyFuWen:
		case PacketDefine.XianShiBuyPresent:
		case PacketDefine.YunyouBuy:
		case PacketDefine.ShopBuyTool:

			Goods g = (Goods) user.getTempCollection().get(TempKey.BUY_PROP_THIS_TIME);
			if (g != null) {
				sendMessage(user, S.S60042, g.getCount(), g.getName());
			}
			break;
		}

	}

	/**
	 * 发送消息
	 *
	 * @param socket
	 * @param code
	 * @param info
	 * @param user
	 */
	private void sendMessage(City user, int code, Object... info) {
		user.getMessageSender().send(code, info);
	}

}
