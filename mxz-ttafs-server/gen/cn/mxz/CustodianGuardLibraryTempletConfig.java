//[奇遇]24[保护妲己]三日五日奖励库
		List<CustodianGuardLibraryTemplet> all = new ArrayList<CustodianGuardLibraryTemplet>();
		for (CustodianGuardLibraryTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<CustodianGuardLibraryTemplet> all = new ArrayList<CustodianGuardLibraryTemplet>();
		for (CustodianGuardLibraryTemplet f : getAll()) {
			if(equals(f.getPropNeame(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<CustodianGuardLibraryTemplet> all = new ArrayList<CustodianGuardLibraryTemplet>();
		for (CustodianGuardLibraryTemplet f : getAll()) {
			if(equals(f.getType(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<CustodianGuardLibraryTemplet> all = new ArrayList<CustodianGuardLibraryTemplet>();
		for (CustodianGuardLibraryTemplet f : getAll()) {
			if(equals(f.getStep(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<CustodianGuardLibraryTemplet> all = new ArrayList<CustodianGuardLibraryTemplet>();
		for (CustodianGuardLibraryTemplet f : getAll()) {
			if(equals(f.getWeight(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<CustodianGuardLibraryTemplet> all = new ArrayList<CustodianGuardLibraryTemplet>();
		for (CustodianGuardLibraryTemplet f : getAll()) {
			if(equals(f.getModulesId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<CustodianGuardLibraryTemplet> all = new ArrayList<CustodianGuardLibraryTemplet>();
		for (CustodianGuardLibraryTemplet f : getAll()) {
			if(equals(f.getProbability(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CustodianGuardLibraryTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CustodianGuardLibraryTemplet f = get(keys.get(i));
			all[i] = f.getPropNeame();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CustodianGuardLibraryTemplet f = get(keys.get(i));
			all[i] = f.getType();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CustodianGuardLibraryTemplet f = get(keys.get(i));
			all[i] = f.getStep();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CustodianGuardLibraryTemplet f = get(keys.get(i));
			all[i] = f.getWeight();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CustodianGuardLibraryTemplet f = get(keys.get(i));
			all[i] = f.getModulesId();
		}
		return all;
	}

		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CustodianGuardLibraryTemplet f = get(keys.get(i));
			all[i] = f.getProbability();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (CustodianGuardLibraryTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (CustodianGuardLibraryTemplet f : getAll()) {
			all.add(f.getPropNeame());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (CustodianGuardLibraryTemplet f : getAll()) {
			all.add(f.getType());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (CustodianGuardLibraryTemplet f : getAll()) {
			all.add(f.getStep());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (CustodianGuardLibraryTemplet f : getAll()) {
			all.add(f.getWeight());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (CustodianGuardLibraryTemplet f : getAll()) {
			all.add(f.getModulesId());
		}
		return all;
	}

		List<Float> all = new ArrayList<Float>();
		for (CustodianGuardLibraryTemplet f : getAll()) {
			all.add(f.getProbability());
		}
		return all;
	}
