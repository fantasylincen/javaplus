//[地图]活动地图
		List<ActivityMapTemplet> all = new ArrayList<ActivityMapTemplet>();
		for (ActivityMapTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<ActivityMapTemplet> all = new ArrayList<ActivityMapTemplet>();
		for (ActivityMapTemplet f : getAll()) {
			if(equals(f.getType(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<ActivityMapTemplet> all = new ArrayList<ActivityMapTemplet>();
		for (ActivityMapTemplet f : getAll()) {
			if(equals(f.getResid(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<ActivityMapTemplet> all = new ArrayList<ActivityMapTemplet>();
		for (ActivityMapTemplet f : getAll()) {
			if(equals(f.getFormat(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ActivityMapTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ActivityMapTemplet f = get(keys.get(i));
			all[i] = f.getType();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ActivityMapTemplet f = get(keys.get(i));
			all[i] = f.getResid();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			ActivityMapTemplet f = get(keys.get(i));
			all[i] = f.getFormat();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (ActivityMapTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (ActivityMapTemplet f : getAll()) {
			all.add(f.getType());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (ActivityMapTemplet f : getAll()) {
			all.add(f.getResid());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (ActivityMapTemplet f : getAll()) {
			all.add(f.getFormat());
		}
		return all;
	}
