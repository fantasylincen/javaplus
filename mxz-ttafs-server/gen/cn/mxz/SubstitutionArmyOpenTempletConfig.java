//[阵法]替补位置开启
		List<SubstitutionArmyOpenTemplet> all = new ArrayList<SubstitutionArmyOpenTemplet>();
		for (SubstitutionArmyOpenTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<SubstitutionArmyOpenTemplet> all = new ArrayList<SubstitutionArmyOpenTemplet>();
		for (SubstitutionArmyOpenTemplet f : getAll()) {
			if(equals(f.getLeadLv(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<SubstitutionArmyOpenTemplet> all = new ArrayList<SubstitutionArmyOpenTemplet>();
		for (SubstitutionArmyOpenTemplet f : getAll()) {
			if(equals(f.getArmyState(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SubstitutionArmyOpenTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SubstitutionArmyOpenTemplet f = get(keys.get(i));
			all[i] = f.getLeadLv();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SubstitutionArmyOpenTemplet f = get(keys.get(i));
			all[i] = f.getArmyState();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (SubstitutionArmyOpenTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (SubstitutionArmyOpenTemplet f : getAll()) {
			all.add(f.getLeadLv());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (SubstitutionArmyOpenTemplet f : getAll()) {
			all.add(f.getArmyState());
		}
		return all;
	}
