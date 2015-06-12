package cn.vgame.a.bank;

import cn.vgame.a.Server;
import cn.vgame.a.account.Role;
import cn.vgame.a.gen.dto.MongoGen.Daos;
import cn.vgame.a.gen.dto.MongoGen.RoleDto;
import cn.vgame.a.result.ErrorResult;
import cn.vgame.share.KeyValue;

public class Bank {

	private final Role role;

	public Bank(Role role) {
		this.role = role;
	}

	/**
	 * 强制解锁用户二级密码
	 */
	public void unlock() {
		KeyValue kv = role.getKeyValueForever();
		kv.set("UN_LOCK_PASSWORD_START_TIME", System.currentTimeMillis());
	}

	public void setBankPassword(String newPassword) {
		role.getDto().setBankPassword(newPassword);
		Daos.getRoleDao().save(role.getDto());
	}

	/**
	 * 银行密码状态 0 未设置安全密码 1 已设置安全密码 2 强制解锁中
	 * 
	 * @return
	 */
	public int getBankPasswordStatus() {
		if (!isSetBankPassword())
			return 0;
		if (isUnlocking())
			return 2;
		return 1;
	}

	/**
	 * 银行密码强制解锁cd
	 * 
	 * @return
	 */
	public int getBankPasswordCd() {
		KeyValue kv = role.getKeyValueForever();
		long t = kv.getLong("UN_LOCK_PASSWORD_START_TIME");

		if (t == 0)
			return 0;

		int cd = Server.getConst().getInt("BANK_PASSWORD_UNLOCK_SEC");

		long c = System.currentTimeMillis();
		int d = (int) ((c - t) / 1000);

		int cdResult = cd - d;
		return Math.max(0, cdResult);
	}

	public String getBankPassword() {
		tryToUnlockPassword();
		return role.getDto().getBankPassword();
	}

	private void tryToUnlockPassword() {

		if(isUnlocking() && getBankPasswordCd() <= 0) {
			clearPassword();
		}
	}

	private void clearPassword() {
		setBankPassword(null);
		cancelLock();
	}

	/**
	 * 是否已经设置安全密码
	 * 
	 * @return
	 */
	private boolean isSetBankPassword() {
		String bankPassword = getBankPassword();
		return bankPassword != null && !bankPassword.isEmpty();
	}

	/**
	 * 是否正在解锁
	 * 
	 * @return
	 */
	private boolean isUnlocking() {
		KeyValue kv = role.getKeyValueForever();
		long t = kv.getLong("UN_LOCK_PASSWORD_START_TIME");
		return t > 0;
	}

	public void toBank(long coin) {
		RoleDto dto = role.getDto();
		if (coin < 0 || coin > role.getCoin()) {
			return;
		}

		if (coin + role.getBankCoin() < 0)
			throw new ErrorResult(10033).toException();

		role.reduceCoin(coin);

		role.addCoinLog(-coin, "bank", "to bank");
		
		dto.setBankCoin(coin + dto.getBankCoin());

		Daos.getRoleDao().save(dto);
	}

	public void fromBank(long coin) {
		RoleDto dto = role.getDto();
		if (coin < 0 || coin > role.getBankCoin()) {
			return;
		}

		dto.setBankCoin(dto.getBankCoin() - coin);

		role.addCoin(coin);
		role.addCoinLog(coin, "bank", "from bank");

		Daos.getRoleDao().save(dto);
	}

	public void cancelLock() {
		KeyValue kv = role.getKeyValueForever();
		kv.set("UN_LOCK_PASSWORD_START_TIME", 0);
	}

	public void checkPassword(String password) {
		String pwd = getBankPassword();
		if(pwd == null || pwd.isEmpty())
			return;
		
		if(!pwd.equals(password)) {
			throw new ErrorResult(10028).toException();
		}
	}
}
