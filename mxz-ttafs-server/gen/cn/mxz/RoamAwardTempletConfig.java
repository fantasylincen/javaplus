//[奇遇]25[云游仙人]奖励
		List<RoamAwardTemplet> all = new ArrayList<RoamAwardTemplet>();
		for (RoamAwardTemplet f : getAll()) {
			if(equals(f.getStep(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<RoamAwardTemplet> all = new ArrayList<RoamAwardTemplet>();
		for (RoamAwardTemplet f : getAll()) {
			if(equals(f.getTime(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<RoamAwardTemplet> all = new ArrayList<RoamAwardTemplet>();
		for (RoamAwardTemplet f : getAll()) {
			if(equals(f.getExp(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<RoamAwardTemplet> all = new ArrayList<RoamAwardTemplet>();
		for (RoamAwardTemplet f : getAll()) {
			if(equals(f.getWine(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<RoamAwardTemplet> all = new ArrayList<RoamAwardTemplet>();
		for (RoamAwardTemplet f : getAll()) {
			if(equals(f.getWeight(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RoamAwardTemplet f = get(keys.get(i));
			all[i] = f.getStep();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RoamAwardTemplet f = get(keys.get(i));
			all[i] = f.getTime();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RoamAwardTemplet f = get(keys.get(i));
			all[i] = f.getExp();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RoamAwardTemplet f = get(keys.get(i));
			all[i] = f.getWine();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RoamAwardTemplet f = get(keys.get(i));
			all[i] = f.getWeight();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (RoamAwardTemplet f : getAll()) {
			all.add(f.getStep());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (RoamAwardTemplet f : getAll()) {
			all.add(f.getTime());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (RoamAwardTemplet f : getAll()) {
			all.add(f.getExp());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (RoamAwardTemplet f : getAll()) {
			all.add(f.getWine());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (RoamAwardTemplet f : getAll()) {
			all.add(f.getWeight());
		}
		return all;
	}
