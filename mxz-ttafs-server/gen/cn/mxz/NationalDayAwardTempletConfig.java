//活动]国庆登陆奖励
		List<NationalDayAwardTemplet> all = new ArrayList<NationalDayAwardTemplet>();
		for (NationalDayAwardTemplet f : getAll()) {
			if(equals(f.getDay(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<NationalDayAwardTemplet> all = new ArrayList<NationalDayAwardTemplet>();
		for (NationalDayAwardTemplet f : getAll()) {
			if(equals(f.getAwards(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<NationalDayAwardTemplet> all = new ArrayList<NationalDayAwardTemplet>();
		for (NationalDayAwardTemplet f : getAll()) {
			if(equals(f.getLog(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			NationalDayAwardTemplet f = get(keys.get(i));
			all[i] = f.getDay();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			NationalDayAwardTemplet f = get(keys.get(i));
			all[i] = f.getAwards();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			NationalDayAwardTemplet f = get(keys.get(i));
			all[i] = f.getLog();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (NationalDayAwardTemplet f : getAll()) {
			all.add(f.getDay());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (NationalDayAwardTemplet f : getAll()) {
			all.add(f.getAwards());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (NationalDayAwardTemplet f : getAll()) {
			all.add(f.getLog());
		}
		return all;
	}
