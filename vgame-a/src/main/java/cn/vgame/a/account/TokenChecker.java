package cn.vgame.a.account;

public interface TokenChecker {

	void check(String userId, String token, String appId);

}
