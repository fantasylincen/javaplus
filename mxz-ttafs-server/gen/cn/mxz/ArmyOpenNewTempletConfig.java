//[阵法]上阵人数开启
		List<ArmyOpenNewTemplet> all = new ArrayList<ArmyOpenNewTemplet>();
		for (ArmyOpenNewTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<ArmyOpenNewTemplet> all = new ArrayList<ArmyOpenNewTemplet>();
		for (ArmyOpenNewTemplet f : getAll()) {
			if(equals(f.getLeadLv(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<ArmyOpenNewTemplet> all = new ArrayList<ArmyOpenNewTemplet>();
		for (ArmyOpenNewTemplet f : getAll()) {
			if(equals(f.getArmyState(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ArmyOpenNewTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ArmyOpenNewTemplet f = get(keys.get(i));
			all[i] = f.getLeadLv();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ArmyOpenNewTemplet f = get(keys.get(i));
			all[i] = f.getArmyState();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (ArmyOpenNewTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (ArmyOpenNewTemplet f : getAll()) {
			all.add(f.getLeadLv());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (ArmyOpenNewTemplet f : getAll()) {
			all.add(f.getArmyState());
		}
		return all;
	}
