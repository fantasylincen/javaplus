//[奇遇]28[等级奖励]等级奖励
		List<LevelUpRewardTemplet> all = new ArrayList<LevelUpRewardTemplet>();
		for (LevelUpRewardTemplet f : getAll()) {
			if(equals(f.getNeedLevel(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<LevelUpRewardTemplet> all = new ArrayList<LevelUpRewardTemplet>();
		for (LevelUpRewardTemplet f : getAll()) {
			if(equals(f.getAwards(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<LevelUpRewardTemplet> all = new ArrayList<LevelUpRewardTemplet>();
		for (LevelUpRewardTemplet f : getAll()) {
			if(equals(f.getExplain(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			LevelUpRewardTemplet f = get(keys.get(i));
			all[i] = f.getNeedLevel();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			LevelUpRewardTemplet f = get(keys.get(i));
			all[i] = f.getAwards();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			LevelUpRewardTemplet f = get(keys.get(i));
			all[i] = f.getExplain();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (LevelUpRewardTemplet f : getAll()) {
			all.add(f.getNeedLevel());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (LevelUpRewardTemplet f : getAll()) {
			all.add(f.getAwards());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (LevelUpRewardTemplet f : getAll()) {
			all.add(f.getExplain());
		}
		return all;
	}
