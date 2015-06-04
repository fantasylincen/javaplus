package cn.mxz.friend;

import mongo.gen.MongoGen.UserFriendRequestDto;

public class RequestImpl implements Request {

	private UserFriendRequestDto	request;

	public RequestImpl(UserFriendRequestDto request) {
		this.request = request;
	}

	@Override
	public long getRequestTime() {
		return request.getRequestTime();
	}

	@Override
	public String getApplicant() {
		return request.getApplicant();
	}

	@Override
	public String getReceiver() {
		return request.getReceiver();
	}
}
