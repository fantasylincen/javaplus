//[礼包]礼包奖励
		List<PresentTemplet> all = new ArrayList<PresentTemplet>();
		for (PresentTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<PresentTemplet> all = new ArrayList<PresentTemplet>();
		for (PresentTemplet f : getAll()) {
			if(equals(f.getKey(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<PresentTemplet> all = new ArrayList<PresentTemplet>();
		for (PresentTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<PresentTemplet> all = new ArrayList<PresentTemplet>();
		for (PresentTemplet f : getAll()) {
			if(equals(f.getAwards(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<PresentTemplet> all = new ArrayList<PresentTemplet>();
		for (PresentTemplet f : getAll()) {
			if(equals(f.getExplain(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			PresentTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			PresentTemplet f = get(keys.get(i));
			all[i] = f.getKey();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			PresentTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			PresentTemplet f = get(keys.get(i));
			all[i] = f.getAwards();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			PresentTemplet f = get(keys.get(i));
			all[i] = f.getExplain();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (PresentTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (PresentTemplet f : getAll()) {
			all.add(f.getKey());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (PresentTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (PresentTemplet f : getAll()) {
			all.add(f.getAwards());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (PresentTemplet f : getAll()) {
			all.add(f.getExplain());
		}
		return all;
	}
