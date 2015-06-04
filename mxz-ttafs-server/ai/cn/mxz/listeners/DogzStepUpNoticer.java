package cn.mxz.listeners;

import cn.mxz.dogz.DogzStepUpEvent;
import cn.mxz.events.Listener2;

public class DogzStepUpNoticer implements Listener2 {

	@Override
	public void onEvent(Object e) {

//		DogzStepUpEvent event = (DogzStepUpEvent) e;
//
//		final Dogz source = (Dogz) e.getSource();
//
//		int typeId = source.getTypeId();
//
//		DogzTemplet temp = DogzTempletConfig.get(typeId);
//
//
//		List<DogzLevelTemplet> allll = DogzLevelTempletConfig.getAll();
//
//		Collection<DogzLevelTemplet> f = Collections2.filter(allll, new Predicate<DogzLevelTemplet>() {
//
//			@Override
//			public boolean apply(DogzLevelTemplet input) {
//				return input.getDogzId() == source.getTypeId() && input.getLevel() == source.getLevel();
//			}
//		});
//
//		DogzLevelTemplet t = Util.Collection.getFirst(f);
//
//		new MessageSenderToAllUp().sendMessage(S.S71001, event.getCity().getPlayer().getNick(), temp.getDogzName(), "");
	}

	@Override
	public Class<?> getEventListendClass() {
		return DogzStepUpEvent.class;
	}

}
