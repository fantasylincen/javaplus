//[神将]经验配置
		List<FighterExpTemplet> all = new ArrayList<FighterExpTemplet>();
		for (FighterExpTemplet f : getAll()) {
			if(equals(f.getLevel(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<FighterExpTemplet> all = new ArrayList<FighterExpTemplet>();
		for (FighterExpTemplet f : getAll()) {
			if(equals(f.getPlayer(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<FighterExpTemplet> all = new ArrayList<FighterExpTemplet>();
		for (FighterExpTemplet f : getAll()) {
			if(equals(f.getPlayerSum(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterExpTemplet f = get(keys.get(i));
			all[i] = f.getLevel();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterExpTemplet f = get(keys.get(i));
			all[i] = f.getPlayer();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FighterExpTemplet f = get(keys.get(i));
			all[i] = f.getPlayerSum();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (FighterExpTemplet f : getAll()) {
			all.add(f.getLevel());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (FighterExpTemplet f : getAll()) {
			all.add(f.getPlayer());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (FighterExpTemplet f : getAll()) {
			all.add(f.getPlayerSum());
		}
		return all;
	}
