package cn.mxz.dogz;

import java.util.List;

import message.S;
import cn.mxz.base.exception.OperationFaildException;
import cn.mxz.city.City;
import cn.mxz.messagesender.MessageFactory;
import cn.mxz.protocols.user.UserP.UserPro;
import cn.mxz.user.builder.UserBuilder;

public class DogzTransformImpl implements DogzTransform {

	private City	user;

	@Override
	public DogzOpenUI getDogzOpenUI() {
//		user.getDogzManager().ensureHasFirst();
		return new DogzOpenUIImpl(user);
	}

	@Override
	public DogzOpenUI open(int dogzId) {
		check(dogzId);
		DogzFactory.createNewDogz(user, dogzId);
		return new DogzOpenUIImpl(user);
	}

	private void check(int dogzId) {
		DogzOpenUI ui = getDogzOpenUI();
		List<DogzUI> h = ui.getHeads();
		for (DogzUI d : h) {
			if (d.getStatus() == 2 && d.getTempletId() == dogzId) {
				return;
			}
		}
		throw new OperationFaildException(S.S10238);
	}

	@Override
	public void setUser(City user) {
		this.user = user;
	}

	@Override
	public DogzOpenUI setFighting(int dogzId) {

		takeBackFighting();

		DogzManager m = user.getDogzManager();

		Dogz dogz = m.getDogz(dogzId);

		dogz.setFighting(true);

		return getDogzOpenUI();
	}

	/**
	 * 取消当前出战神兽
	 */
	private void takeBackFighting() {

		Dogz fighting = user.getDogzManager().getFighting();

		if (fighting != null) {

			user.getDogzManager().getDogz(fighting.getTypeId()).setFighting(false);
		}
	}

	@Override
	public DogzOpenUI zhuHun(int dogzId) {
		DogzManager m = user.getDogzManager();
		m.zhuHun(dogzId);

		UserBuilder bd = new UserBuilder();
		UserPro data = bd.build(user);
		MessageFactory.getUser().onUpdateUserList(user.getSocket(), data);
		
		return getDogzOpenUI();
	}
}
