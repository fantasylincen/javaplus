//[竞技场]物品兑换
		List<ArenaConvertTemplet> all = new ArrayList<ArenaConvertTemplet>();
		for (ArenaConvertTemplet f : getAll()) {
			if(equals(f.getTypeId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<ArenaConvertTemplet> all = new ArrayList<ArenaConvertTemplet>();
		for (ArenaConvertTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<ArenaConvertTemplet> all = new ArrayList<ArenaConvertTemplet>();
		for (ArenaConvertTemplet f : getAll()) {
			if(equals(f.getOrder(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<ArenaConvertTemplet> all = new ArrayList<ArenaConvertTemplet>();
		for (ArenaConvertTemplet f : getAll()) {
			if(equals(f.getQuality(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<ArenaConvertTemplet> all = new ArrayList<ArenaConvertTemplet>();
		for (ArenaConvertTemplet f : getAll()) {
			if(equals(f.getDescription(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<ArenaConvertTemplet> all = new ArrayList<ArenaConvertTemplet>();
		for (ArenaConvertTemplet f : getAll()) {
			if(equals(f.getHonor(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<ArenaConvertTemplet> all = new ArrayList<ArenaConvertTemplet>();
		for (ArenaConvertTemplet f : getAll()) {
			if(equals(f.getConvertnumber(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<ArenaConvertTemplet> all = new ArrayList<ArenaConvertTemplet>();
		for (ArenaConvertTemplet f : getAll()) {
			if(equals(f.getConvertMax(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<ArenaConvertTemplet> all = new ArrayList<ArenaConvertTemplet>();
		for (ArenaConvertTemplet f : getAll()) {
			if(equals(f.getCoolTime(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<ArenaConvertTemplet> all = new ArrayList<ArenaConvertTemplet>();
		for (ArenaConvertTemplet f : getAll()) {
			if(equals(f.getLv(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ArenaConvertTemplet f = get(keys.get(i));
			all[i] = f.getTypeId();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ArenaConvertTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ArenaConvertTemplet f = get(keys.get(i));
			all[i] = f.getOrder();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ArenaConvertTemplet f = get(keys.get(i));
			all[i] = f.getQuality();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ArenaConvertTemplet f = get(keys.get(i));
			all[i] = f.getDescription();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ArenaConvertTemplet f = get(keys.get(i));
			all[i] = f.getHonor();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ArenaConvertTemplet f = get(keys.get(i));
			all[i] = f.getConvertnumber();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ArenaConvertTemplet f = get(keys.get(i));
			all[i] = f.getConvertMax();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ArenaConvertTemplet f = get(keys.get(i));
			all[i] = f.getCoolTime();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ArenaConvertTemplet f = get(keys.get(i));
			all[i] = f.getLv();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (ArenaConvertTemplet f : getAll()) {
			all.add(f.getTypeId());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (ArenaConvertTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (ArenaConvertTemplet f : getAll()) {
			all.add(f.getOrder());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (ArenaConvertTemplet f : getAll()) {
			all.add(f.getQuality());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (ArenaConvertTemplet f : getAll()) {
			all.add(f.getDescription());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (ArenaConvertTemplet f : getAll()) {
			all.add(f.getHonor());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (ArenaConvertTemplet f : getAll()) {
			all.add(f.getConvertnumber());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (ArenaConvertTemplet f : getAll()) {
			all.add(f.getConvertMax());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (ArenaConvertTemplet f : getAll()) {
			all.add(f.getCoolTime());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (ArenaConvertTemplet f : getAll()) {
			all.add(f.getLv());
		}
		return all;
	}
