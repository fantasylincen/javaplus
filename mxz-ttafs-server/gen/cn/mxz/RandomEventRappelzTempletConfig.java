//[奇遇]15[关卡][神魔]神魔出现表
		List<RandomEventRappelzTemplet> all = new ArrayList<RandomEventRappelzTemplet>();
		for (RandomEventRappelzTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<RandomEventRappelzTemplet> all = new ArrayList<RandomEventRappelzTemplet>();
		for (RandomEventRappelzTemplet f : getAll()) {
			if(equals(f.getProtagonistLv(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<RandomEventRappelzTemplet> all = new ArrayList<RandomEventRappelzTemplet>();
		for (RandomEventRappelzTemplet f : getAll()) {
			if(equals(f.getRappelzLv(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<RandomEventRappelzTemplet> all = new ArrayList<RandomEventRappelzTemplet>();
		for (RandomEventRappelzTemplet f : getAll()) {
			if(equals(f.getRappelzId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventRappelzTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventRappelzTemplet f = get(keys.get(i));
			all[i] = f.getProtagonistLv();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventRappelzTemplet f = get(keys.get(i));
			all[i] = f.getRappelzLv();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventRappelzTemplet f = get(keys.get(i));
			all[i] = f.getRappelzId();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (RandomEventRappelzTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (RandomEventRappelzTemplet f : getAll()) {
			all.add(f.getProtagonistLv());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (RandomEventRappelzTemplet f : getAll()) {
			all.add(f.getRappelzLv());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (RandomEventRappelzTemplet f : getAll()) {
			all.add(f.getRappelzId());
		}
		return all;
	}
