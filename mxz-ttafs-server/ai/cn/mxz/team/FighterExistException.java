package cn.mxz.team;

/**
 * 战士已经存在
 * @author 林岑
 *
 */
class FighterExistException extends RuntimeException {

	private int	typeId;

	FighterExistException(int typeId) {
		this.typeId = typeId;
	}

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -3668499751338809998L;

	public int getTypeId() {
		return typeId;
	}
}
