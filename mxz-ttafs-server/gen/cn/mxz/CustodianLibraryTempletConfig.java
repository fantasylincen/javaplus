//[奇遇]24[保护妲己]神秘奖励库
		List<CustodianLibraryTemplet> all = new ArrayList<CustodianLibraryTemplet>();
		for (CustodianLibraryTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<CustodianLibraryTemplet> all = new ArrayList<CustodianLibraryTemplet>();
		for (CustodianLibraryTemplet f : getAll()) {
			if(equals(f.getPropNeame(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<CustodianLibraryTemplet> all = new ArrayList<CustodianLibraryTemplet>();
		for (CustodianLibraryTemplet f : getAll()) {
			if(equals(f.getAwards(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<CustodianLibraryTemplet> all = new ArrayList<CustodianLibraryTemplet>();
		for (CustodianLibraryTemplet f : getAll()) {
			if(equals(f.getWeight(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<CustodianLibraryTemplet> all = new ArrayList<CustodianLibraryTemplet>();
		for (CustodianLibraryTemplet f : getAll()) {
			if(equals(f.getModulesId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<CustodianLibraryTemplet> all = new ArrayList<CustodianLibraryTemplet>();
		for (CustodianLibraryTemplet f : getAll()) {
			if(equals(f.getSzzy(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CustodianLibraryTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CustodianLibraryTemplet f = get(keys.get(i));
			all[i] = f.getPropNeame();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CustodianLibraryTemplet f = get(keys.get(i));
			all[i] = f.getAwards();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CustodianLibraryTemplet f = get(keys.get(i));
			all[i] = f.getWeight();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CustodianLibraryTemplet f = get(keys.get(i));
			all[i] = f.getModulesId();
		}
		return all;
	}

		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CustodianLibraryTemplet f = get(keys.get(i));
			all[i] = f.getSzzy();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (CustodianLibraryTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (CustodianLibraryTemplet f : getAll()) {
			all.add(f.getPropNeame());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (CustodianLibraryTemplet f : getAll()) {
			all.add(f.getAwards());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (CustodianLibraryTemplet f : getAll()) {
			all.add(f.getWeight());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (CustodianLibraryTemplet f : getAll()) {
			all.add(f.getModulesId());
		}
		return all;
	}

		List<Float> all = new ArrayList<Float>();
		for (CustodianLibraryTemplet f : getAll()) {
			all.add(f.getSzzy());
		}
		return all;
	}
