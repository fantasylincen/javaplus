//[奖励]连续登录翻牌次数
		List<ContinuousRewardsTemplet> all = new ArrayList<ContinuousRewardsTemplet>();
		for (ContinuousRewardsTemplet f : getAll()) {
			if(equals(f.getEnterDay(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<ContinuousRewardsTemplet> all = new ArrayList<ContinuousRewardsTemplet>();
		for (ContinuousRewardsTemplet f : getAll()) {
			if(equals(f.getNumber(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ContinuousRewardsTemplet f = get(keys.get(i));
			all[i] = f.getEnterDay();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ContinuousRewardsTemplet f = get(keys.get(i));
			all[i] = f.getNumber();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (ContinuousRewardsTemplet f : getAll()) {
			all.add(f.getEnterDay());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (ContinuousRewardsTemplet f : getAll()) {
			all.add(f.getNumber());
		}
		return all;
	}
