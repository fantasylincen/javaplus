//[奇遇]15[关卡][神魔]基础奖励表
		List<RandomEventBaseTemplet> all = new ArrayList<RandomEventBaseTemplet>();
		for (RandomEventBaseTemplet f : getAll()) {
			if(equals(f.getRappelzLv(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<RandomEventBaseTemplet> all = new ArrayList<RandomEventBaseTemplet>();
		for (RandomEventBaseTemplet f : getAll()) {
			if(equals(f.getMerit(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<RandomEventBaseTemplet> all = new ArrayList<RandomEventBaseTemplet>();
		for (RandomEventBaseTemplet f : getAll()) {
			if(equals(f.getFindReward(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<RandomEventBaseTemplet> all = new ArrayList<RandomEventBaseTemplet>();
		for (RandomEventBaseTemplet f : getAll()) {
			if(equals(f.getMvpReward(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<RandomEventBaseTemplet> all = new ArrayList<RandomEventBaseTemplet>();
		for (RandomEventBaseTemplet f : getAll()) {
			if(equals(f.getKillReward(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<RandomEventBaseTemplet> all = new ArrayList<RandomEventBaseTemplet>();
		for (RandomEventBaseTemplet f : getAll()) {
			if(equals(f.getBossParam(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventBaseTemplet f = get(keys.get(i));
			all[i] = f.getRappelzLv();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventBaseTemplet f = get(keys.get(i));
			all[i] = f.getMerit();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventBaseTemplet f = get(keys.get(i));
			all[i] = f.getFindReward();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventBaseTemplet f = get(keys.get(i));
			all[i] = f.getMvpReward();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventBaseTemplet f = get(keys.get(i));
			all[i] = f.getKillReward();
		}
		return all;
	}

		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventBaseTemplet f = get(keys.get(i));
			all[i] = f.getBossParam();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (RandomEventBaseTemplet f : getAll()) {
			all.add(f.getRappelzLv());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (RandomEventBaseTemplet f : getAll()) {
			all.add(f.getMerit());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (RandomEventBaseTemplet f : getAll()) {
			all.add(f.getFindReward());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (RandomEventBaseTemplet f : getAll()) {
			all.add(f.getMvpReward());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (RandomEventBaseTemplet f : getAll()) {
			all.add(f.getKillReward());
		}
		return all;
	}

		List<Float> all = new ArrayList<Float>();
		for (RandomEventBaseTemplet f : getAll()) {
			all.add(f.getBossParam());
		}
		return all;
	}
