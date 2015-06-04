package cn.mxz.events;

import cn.mxz.city.City;

public class UserCreateEvent {

	private City city;
	private String invitationCode;
	private String thirdPartyId;
	private String userName;

	public UserCreateEvent(City city, String invitationCode,
			String thirdPartyId, String userName) {
		this.city = city;
		this.invitationCode = invitationCode;
		this.thirdPartyId = thirdPartyId;
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public String getThirdPartyId() {
		return thirdPartyId;
	}

	public City getCity() {
		return city;
	}

	public String getInvitationCode() {
		return invitationCode;
	}
}
