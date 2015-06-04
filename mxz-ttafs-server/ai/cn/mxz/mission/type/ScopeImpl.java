package cn.mxz.mission.type;

public class ScopeImpl implements Scope {

	private int	min;
	private int	max;

	public ScopeImpl(int min, int max) {
		this.min = min;
		this.max = max;
	}

	@Override
	public boolean contains(int round) {
		return min <= round && round <= max; 
	}

}
