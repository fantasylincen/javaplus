package com.linekong.platform.protocol.erating.eRatingAGIP;

public interface IERatingProtocol {

	public abstract int analyzeBody(byte[] body);

	public abstract byte[] getBody();
}