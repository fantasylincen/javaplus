//[奇遇]15[关卡][神魔]兽魂参与奖励表
		List<RandomEventSHTemplet> all = new ArrayList<RandomEventSHTemplet>();
		for (RandomEventSHTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<RandomEventSHTemplet> all = new ArrayList<RandomEventSHTemplet>();
		for (RandomEventSHTemplet f : getAll()) {
			if(equals(f.getNum(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<RandomEventSHTemplet> all = new ArrayList<RandomEventSHTemplet>();
		for (RandomEventSHTemplet f : getAll()) {
			if(equals(f.getShouHun(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventSHTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventSHTemplet f = get(keys.get(i));
			all[i] = f.getNum();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventSHTemplet f = get(keys.get(i));
			all[i] = f.getShouHun();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (RandomEventSHTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (RandomEventSHTemplet f : getAll()) {
			all.add(f.getNum());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (RandomEventSHTemplet f : getAll()) {
			all.add(f.getShouHun());
		}
		return all;
	}
