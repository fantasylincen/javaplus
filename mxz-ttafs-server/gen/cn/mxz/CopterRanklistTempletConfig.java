//[奇遇]35[渡天劫]后端排行奖励
		List<CopterRanklistTemplet> all = new ArrayList<CopterRanklistTemplet>();
		for (CopterRanklistTemplet f : getAll()) {
			if(equals(f.getGivil(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<CopterRanklistTemplet> all = new ArrayList<CopterRanklistTemplet>();
		for (CopterRanklistTemplet f : getAll()) {
			if(equals(f.getRewardType(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<CopterRanklistTemplet> all = new ArrayList<CopterRanklistTemplet>();
		for (CopterRanklistTemplet f : getAll()) {
			if(equals(f.getSycee3(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<CopterRanklistTemplet> all = new ArrayList<CopterRanklistTemplet>();
		for (CopterRanklistTemplet f : getAll()) {
			if(equals(f.getSycee4(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<CopterRanklistTemplet> all = new ArrayList<CopterRanklistTemplet>();
		for (CopterRanklistTemplet f : getAll()) {
			if(equals(f.getSycee5(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterRanklistTemplet f = get(keys.get(i));
			all[i] = f.getGivil();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterRanklistTemplet f = get(keys.get(i));
			all[i] = f.getRewardType();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterRanklistTemplet f = get(keys.get(i));
			all[i] = f.getSycee3();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterRanklistTemplet f = get(keys.get(i));
			all[i] = f.getSycee4();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterRanklistTemplet f = get(keys.get(i));
			all[i] = f.getSycee5();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (CopterRanklistTemplet f : getAll()) {
			all.add(f.getGivil());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (CopterRanklistTemplet f : getAll()) {
			all.add(f.getRewardType());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (CopterRanklistTemplet f : getAll()) {
			all.add(f.getSycee3());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (CopterRanklistTemplet f : getAll()) {
			all.add(f.getSycee4());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (CopterRanklistTemplet f : getAll()) {
			all.add(f.getSycee5());
		}
		return all;
	}
