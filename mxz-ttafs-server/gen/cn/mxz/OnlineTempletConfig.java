//[奖励]在线奖励
		List<OnlineTemplet> all = new ArrayList<OnlineTemplet>();
		for (OnlineTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<OnlineTemplet> all = new ArrayList<OnlineTemplet>();
		for (OnlineTemplet f : getAll()) {
			if(equals(f.getDuration(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<OnlineTemplet> all = new ArrayList<OnlineTemplet>();
		for (OnlineTemplet f : getAll()) {
			if(equals(f.getAwards(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			OnlineTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			OnlineTemplet f = get(keys.get(i));
			all[i] = f.getDuration();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			OnlineTemplet f = get(keys.get(i));
			all[i] = f.getAwards();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (OnlineTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (OnlineTemplet f : getAll()) {
			all.add(f.getDuration());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (OnlineTemplet f : getAll()) {
			all.add(f.getAwards());
		}
		return all;
	}
