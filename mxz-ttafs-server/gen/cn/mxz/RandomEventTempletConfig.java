//[关卡]问号随机事件
		List<RandomEventTemplet> all = new ArrayList<RandomEventTemplet>();
		for (RandomEventTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<RandomEventTemplet> all = new ArrayList<RandomEventTemplet>();
		for (RandomEventTemplet f : getAll()) {
			if(equals(f.getEventName(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<RandomEventTemplet> all = new ArrayList<RandomEventTemplet>();
		for (RandomEventTemplet f : getAll()) {
			if(equals(f.getType(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<RandomEventTemplet> all = new ArrayList<RandomEventTemplet>();
		for (RandomEventTemplet f : getAll()) {
			if(equals(f.getEventWeight(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<RandomEventTemplet> all = new ArrayList<RandomEventTemplet>();
		for (RandomEventTemplet f : getAll()) {
			if(equals(f.getModulesId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<RandomEventTemplet> all = new ArrayList<RandomEventTemplet>();
		for (RandomEventTemplet f : getAll()) {
			if(equals(f.getAppear(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<RandomEventTemplet> all = new ArrayList<RandomEventTemplet>();
		for (RandomEventTemplet f : getAll()) {
			if(equals(f.getMaxInMap(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<RandomEventTemplet> all = new ArrayList<RandomEventTemplet>();
		for (RandomEventTemplet f : getAll()) {
			if(equals(f.getNeeds(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<RandomEventTemplet> all = new ArrayList<RandomEventTemplet>();
		for (RandomEventTemplet f : getAll()) {
			if(equals(f.getAwards(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<RandomEventTemplet> all = new ArrayList<RandomEventTemplet>();
		for (RandomEventTemplet f : getAll()) {
			if(equals(f.getPlot(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<RandomEventTemplet> all = new ArrayList<RandomEventTemplet>();
		for (RandomEventTemplet f : getAll()) {
			if(equals(f.getFormat(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<RandomEventTemplet> all = new ArrayList<RandomEventTemplet>();
		for (RandomEventTemplet f : getAll()) {
			if(equals(f.getPic(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<RandomEventTemplet> all = new ArrayList<RandomEventTemplet>();
		for (RandomEventTemplet f : getAll()) {
			if(equals(f.getScript(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventTemplet f = get(keys.get(i));
			all[i] = f.getEventName();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventTemplet f = get(keys.get(i));
			all[i] = f.getType();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventTemplet f = get(keys.get(i));
			all[i] = f.getEventWeight();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventTemplet f = get(keys.get(i));
			all[i] = f.getModulesId();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventTemplet f = get(keys.get(i));
			all[i] = f.getAppear();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventTemplet f = get(keys.get(i));
			all[i] = f.getMaxInMap();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventTemplet f = get(keys.get(i));
			all[i] = f.getNeeds();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventTemplet f = get(keys.get(i));
			all[i] = f.getAwards();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventTemplet f = get(keys.get(i));
			all[i] = f.getPlot();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventTemplet f = get(keys.get(i));
			all[i] = f.getFormat();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventTemplet f = get(keys.get(i));
			all[i] = f.getPic();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomEventTemplet f = get(keys.get(i));
			all[i] = f.getScript();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (RandomEventTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (RandomEventTemplet f : getAll()) {
			all.add(f.getEventName());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (RandomEventTemplet f : getAll()) {
			all.add(f.getType());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (RandomEventTemplet f : getAll()) {
			all.add(f.getEventWeight());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (RandomEventTemplet f : getAll()) {
			all.add(f.getModulesId());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (RandomEventTemplet f : getAll()) {
			all.add(f.getAppear());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (RandomEventTemplet f : getAll()) {
			all.add(f.getMaxInMap());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (RandomEventTemplet f : getAll()) {
			all.add(f.getNeeds());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (RandomEventTemplet f : getAll()) {
			all.add(f.getAwards());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (RandomEventTemplet f : getAll()) {
			all.add(f.getPlot());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (RandomEventTemplet f : getAll()) {
			all.add(f.getFormat());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (RandomEventTemplet f : getAll()) {
			all.add(f.getPic());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (RandomEventTemplet f : getAll()) {
			all.add(f.getScript());
		}
		return all;
	}
