//[奇遇]35[渡天劫]前端排行奖励
		List<CopterRanklistFrontTemplet> all = new ArrayList<CopterRanklistFrontTemplet>();
		for (CopterRanklistFrontTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<CopterRanklistFrontTemplet> all = new ArrayList<CopterRanklistFrontTemplet>();
		for (CopterRanklistFrontTemplet f : getAll()) {
			if(equals(f.getGivil(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<CopterRanklistFrontTemplet> all = new ArrayList<CopterRanklistFrontTemplet>();
		for (CopterRanklistFrontTemplet f : getAll()) {
			if(equals(f.getSycee3(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<CopterRanklistFrontTemplet> all = new ArrayList<CopterRanklistFrontTemplet>();
		for (CopterRanklistFrontTemplet f : getAll()) {
			if(equals(f.getSycee4(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<CopterRanklistFrontTemplet> all = new ArrayList<CopterRanklistFrontTemplet>();
		for (CopterRanklistFrontTemplet f : getAll()) {
			if(equals(f.getSycee5(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterRanklistFrontTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterRanklistFrontTemplet f = get(keys.get(i));
			all[i] = f.getGivil();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterRanklistFrontTemplet f = get(keys.get(i));
			all[i] = f.getSycee3();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterRanklistFrontTemplet f = get(keys.get(i));
			all[i] = f.getSycee4();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterRanklistFrontTemplet f = get(keys.get(i));
			all[i] = f.getSycee5();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (CopterRanklistFrontTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (CopterRanklistFrontTemplet f : getAll()) {
			all.add(f.getGivil());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (CopterRanklistFrontTemplet f : getAll()) {
			all.add(f.getSycee3());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (CopterRanklistFrontTemplet f : getAll()) {
			all.add(f.getSycee4());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (CopterRanklistFrontTemplet f : getAll()) {
			all.add(f.getSycee5());
		}
		return all;
	}
