//[奇遇]15[关卡][神魔]碎片库表
		List<RandomEventChipTemplet> all = new ArrayList<RandomEventChipTemplet>();
		for (RandomEventChipTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<RandomEventChipTemplet> all = new ArrayList<RandomEventChipTemplet>();
		for (RandomEventChipTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<RandomEventChipTemplet> all = new ArrayList<RandomEventChipTemplet>();
		for (RandomEventChipTemplet f : getAll()) {
			if(equals(f.getRappelzId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<RandomEventChipTemplet> all = new ArrayList<RandomEventChipTemplet>();
		for (RandomEventChipTemplet f : getAll()) {
			if(equals(f.getDogzID(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<RandomEventChipTemplet> all = new ArrayList<RandomEventChipTemplet>();
		for (RandomEventChipTemplet f : getAll()) {
			if(equals(f.getWeight(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventChipTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventChipTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventChipTemplet f = get(keys.get(i));
			all[i] = f.getRappelzId();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventChipTemplet f = get(keys.get(i));
			all[i] = f.getDogzID();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventChipTemplet f = get(keys.get(i));
			all[i] = f.getWeight();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (RandomEventChipTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (RandomEventChipTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (RandomEventChipTemplet f : getAll()) {
			all.add(f.getRappelzId());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (RandomEventChipTemplet f : getAll()) {
			all.add(f.getDogzID());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (RandomEventChipTemplet f : getAll()) {
			all.add(f.getWeight());
		}
		return all;
	}
