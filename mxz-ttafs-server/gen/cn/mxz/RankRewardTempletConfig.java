//[竞技场]排名奖励
		List<RankRewardTemplet> all = new ArrayList<RankRewardTemplet>();
		for (RankRewardTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<RankRewardTemplet> all = new ArrayList<RankRewardTemplet>();
		for (RankRewardTemplet f : getAll()) {
			if(equals(f.getStep(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<RankRewardTemplet> all = new ArrayList<RankRewardTemplet>();
		for (RankRewardTemplet f : getAll()) {
			if(equals(f.getEnd(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<RankRewardTemplet> all = new ArrayList<RankRewardTemplet>();
		for (RankRewardTemplet f : getAll()) {
			if(equals(f.getDanLv(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<RankRewardTemplet> all = new ArrayList<RankRewardTemplet>();
		for (RankRewardTemplet f : getAll()) {
			if(equals(f.getExplain(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<RankRewardTemplet> all = new ArrayList<RankRewardTemplet>();
		for (RankRewardTemplet f : getAll()) {
			if(equals(f.getAwards(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RankRewardTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RankRewardTemplet f = get(keys.get(i));
			all[i] = f.getStep();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RankRewardTemplet f = get(keys.get(i));
			all[i] = f.getEnd();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RankRewardTemplet f = get(keys.get(i));
			all[i] = f.getDanLv();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RankRewardTemplet f = get(keys.get(i));
			all[i] = f.getExplain();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RankRewardTemplet f = get(keys.get(i));
			all[i] = f.getAwards();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (RankRewardTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (RankRewardTemplet f : getAll()) {
			all.add(f.getStep());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (RankRewardTemplet f : getAll()) {
			all.add(f.getEnd());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (RankRewardTemplet f : getAll()) {
			all.add(f.getDanLv());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (RankRewardTemplet f : getAll()) {
			all.add(f.getExplain());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (RankRewardTemplet f : getAll()) {
			all.add(f.getAwards());
		}
		return all;
	}
