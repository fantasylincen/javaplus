package cn.mxz.bag;

import cn.mxz.bag.builder.BagBuilder;
import cn.mxz.messagesender.MessageFactory;
import cn.mxz.util.debuger.Debuger;

public class PiecesBagSnapsort extends BagSnapsort {

	@Override
	public void update() {
		if (!changes.isEmpty()) {
			Debuger.debug("BagSnapsort-- update()" + changes);
			MessageFactory.getBag().piecesBagUpdate(bag.getCity().getSocket(), new BagBuilder().buildChanges(changes));
		}

		if (!removes.isEmpty()) {
			Debuger.debug("BagSnapsort-- remove()" + removes);
			MessageFactory.getBag().piecesBagRemoved(bag.getCity().getSocket(), new BagBuilder().buildRemoves(removes));
		}
	}

}
