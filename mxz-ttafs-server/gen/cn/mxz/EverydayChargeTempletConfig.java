//[奇遇]24[每日首冲]每日首冲物品库
		List<EverydayChargeTemplet> all = new ArrayList<EverydayChargeTemplet>();
		for (EverydayChargeTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<EverydayChargeTemplet> all = new ArrayList<EverydayChargeTemplet>();
		for (EverydayChargeTemplet f : getAll()) {
			if(equals(f.getPropNeame(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<EverydayChargeTemplet> all = new ArrayList<EverydayChargeTemplet>();
		for (EverydayChargeTemplet f : getAll()) {
			if(equals(f.getAwards(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<EverydayChargeTemplet> all = new ArrayList<EverydayChargeTemplet>();
		for (EverydayChargeTemplet f : getAll()) {
			if(equals(f.getWeight(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<EverydayChargeTemplet> all = new ArrayList<EverydayChargeTemplet>();
		for (EverydayChargeTemplet f : getAll()) {
			if(equals(f.getModulesId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			EverydayChargeTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			EverydayChargeTemplet f = get(keys.get(i));
			all[i] = f.getPropNeame();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			EverydayChargeTemplet f = get(keys.get(i));
			all[i] = f.getAwards();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			EverydayChargeTemplet f = get(keys.get(i));
			all[i] = f.getWeight();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			EverydayChargeTemplet f = get(keys.get(i));
			all[i] = f.getModulesId();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (EverydayChargeTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (EverydayChargeTemplet f : getAll()) {
			all.add(f.getPropNeame());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (EverydayChargeTemplet f : getAll()) {
			all.add(f.getAwards());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (EverydayChargeTemplet f : getAll()) {
			all.add(f.getWeight());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (EverydayChargeTemplet f : getAll()) {
			all.add(f.getModulesId());
		}
		return all;
	}
