//[奇遇]15[关卡][神魔]功德奖励表
		List<RandomEventMeritTemplet> all = new ArrayList<RandomEventMeritTemplet>();
		for (RandomEventMeritTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<RandomEventMeritTemplet> all = new ArrayList<RandomEventMeritTemplet>();
		for (RandomEventMeritTemplet f : getAll()) {
			if(equals(f.getMerit(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<RandomEventMeritTemplet> all = new ArrayList<RandomEventMeritTemplet>();
		for (RandomEventMeritTemplet f : getAll()) {
			if(equals(f.getAwards(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventMeritTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventMeritTemplet f = get(keys.get(i));
			all[i] = f.getMerit();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventMeritTemplet f = get(keys.get(i));
			all[i] = f.getAwards();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (RandomEventMeritTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (RandomEventMeritTemplet f : getAll()) {
			all.add(f.getMerit());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (RandomEventMeritTemplet f : getAll()) {
			all.add(f.getAwards());
		}
		return all;
	}
