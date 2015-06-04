package cn.mxz.rechargeFeedback;

class RechargeFeedbackObjects {

	static RechargeFeedbackPlayerImpl getPlayer(String id) {
		return new RechargeFeedbackPlayerImpl(id);
	}

}
