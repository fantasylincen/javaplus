//[活动]活动配置表
		List<ActivityTemplet> all = new ArrayList<ActivityTemplet>();
		for (ActivityTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<ActivityTemplet> all = new ArrayList<ActivityTemplet>();
		for (ActivityTemplet f : getAll()) {
			if(equals(f.getCode(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<ActivityTemplet> all = new ArrayList<ActivityTemplet>();
		for (ActivityTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<ActivityTemplet> all = new ArrayList<ActivityTemplet>();
		for (ActivityTemplet f : getAll()) {
			if(equals(f.getRank(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<ActivityTemplet> all = new ArrayList<ActivityTemplet>();
		for (ActivityTemplet f : getAll()) {
			if(equals(f.getQuality(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<ActivityTemplet> all = new ArrayList<ActivityTemplet>();
		for (ActivityTemplet f : getAll()) {
			if(equals(f.getTime(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<ActivityTemplet> all = new ArrayList<ActivityTemplet>();
		for (ActivityTemplet f : getAll()) {
			if(equals(f.getOpen(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<ActivityTemplet> all = new ArrayList<ActivityTemplet>();
		for (ActivityTemplet f : getAll()) {
			if(equals(f.getShow(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<ActivityTemplet> all = new ArrayList<ActivityTemplet>();
		for (ActivityTemplet f : getAll()) {
			if(equals(f.getLevel(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<ActivityTemplet> all = new ArrayList<ActivityTemplet>();
		for (ActivityTemplet f : getAll()) {
			if(equals(f.getResid(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<ActivityTemplet> all = new ArrayList<ActivityTemplet>();
		for (ActivityTemplet f : getAll()) {
			if(equals(f.getFormat(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<ActivityTemplet> all = new ArrayList<ActivityTemplet>();
		for (ActivityTemplet f : getAll()) {
			if(equals(f.getUrl(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ActivityTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ActivityTemplet f = get(keys.get(i));
			all[i] = f.getCode();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ActivityTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ActivityTemplet f = get(keys.get(i));
			all[i] = f.getRank();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ActivityTemplet f = get(keys.get(i));
			all[i] = f.getQuality();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ActivityTemplet f = get(keys.get(i));
			all[i] = f.getTime();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ActivityTemplet f = get(keys.get(i));
			all[i] = f.getOpen();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ActivityTemplet f = get(keys.get(i));
			all[i] = f.getShow();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ActivityTemplet f = get(keys.get(i));
			all[i] = f.getLevel();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ActivityTemplet f = get(keys.get(i));
			all[i] = f.getResid();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ActivityTemplet f = get(keys.get(i));
			all[i] = f.getFormat();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ActivityTemplet f = get(keys.get(i));
			all[i] = f.getUrl();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (ActivityTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (ActivityTemplet f : getAll()) {
			all.add(f.getCode());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (ActivityTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (ActivityTemplet f : getAll()) {
			all.add(f.getRank());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (ActivityTemplet f : getAll()) {
			all.add(f.getQuality());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (ActivityTemplet f : getAll()) {
			all.add(f.getTime());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (ActivityTemplet f : getAll()) {
			all.add(f.getOpen());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (ActivityTemplet f : getAll()) {
			all.add(f.getShow());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (ActivityTemplet f : getAll()) {
			all.add(f.getLevel());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (ActivityTemplet f : getAll()) {
			all.add(f.getResid());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (ActivityTemplet f : getAll()) {
			all.add(f.getFormat());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (ActivityTemplet f : getAll()) {
			all.add(f.getUrl());
		}
		return all;
	}
