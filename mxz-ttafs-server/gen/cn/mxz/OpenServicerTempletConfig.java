//[奇遇]49[开服礼包]开服礼包
		List<OpenServicerTemplet> all = new ArrayList<OpenServicerTemplet>();
		for (OpenServicerTemplet f : getAll()) {
			if(equals(f.getDay(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<OpenServicerTemplet> all = new ArrayList<OpenServicerTemplet>();
		for (OpenServicerTemplet f : getAll()) {
			if(equals(f.getAward(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<OpenServicerTemplet> all = new ArrayList<OpenServicerTemplet>();
		for (OpenServicerTemplet f : getAll()) {
			if(equals(f.getAwardLog(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			OpenServicerTemplet f = get(keys.get(i));
			all[i] = f.getDay();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			OpenServicerTemplet f = get(keys.get(i));
			all[i] = f.getAward();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			OpenServicerTemplet f = get(keys.get(i));
			all[i] = f.getAwardLog();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (OpenServicerTemplet f : getAll()) {
			all.add(f.getDay());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (OpenServicerTemplet f : getAll()) {
			all.add(f.getAward());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (OpenServicerTemplet f : getAll()) {
			all.add(f.getAwardLog());
		}
		return all;
	}
