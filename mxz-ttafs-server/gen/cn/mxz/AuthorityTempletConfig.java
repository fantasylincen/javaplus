//[权限]等级权限配置
		List<AuthorityTemplet> all = new ArrayList<AuthorityTemplet>();
		for (AuthorityTemplet f : getAll()) {
			if(equals(f.getLevel(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<AuthorityTemplet> all = new ArrayList<AuthorityTemplet>();
		for (AuthorityTemplet f : getAll()) {
			if(equals(f.getBattleNumber(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<AuthorityTemplet> all = new ArrayList<AuthorityTemplet>();
		for (AuthorityTemplet f : getAll()) {
			if(equals(f.getFreeTime(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<AuthorityTemplet> all = new ArrayList<AuthorityTemplet>();
		for (AuthorityTemplet f : getAll()) {
			if(equals(f.getExpaddition(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<AuthorityTemplet> all = new ArrayList<AuthorityTemplet>();
		for (AuthorityTemplet f : getAll()) {
			if(equals(f.getLevelUpAward(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<AuthorityTemplet> all = new ArrayList<AuthorityTemplet>();
		for (AuthorityTemplet f : getAll()) {
			if(equals(f.getInitialAddition(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<AuthorityTemplet> all = new ArrayList<AuthorityTemplet>();
		for (AuthorityTemplet f : getAll()) {
			if(equals(f.getVigorLimit(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<AuthorityTemplet> all = new ArrayList<AuthorityTemplet>();
		for (AuthorityTemplet f : getAll()) {
			if(equals(f.getPowerLimit(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<AuthorityTemplet> all = new ArrayList<AuthorityTemplet>();
		for (AuthorityTemplet f : getAll()) {
			if(equals(f.getSnatchPar(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<AuthorityTemplet> all = new ArrayList<AuthorityTemplet>();
		for (AuthorityTemplet f : getAll()) {
			if(equals(f.getLevelUpgoods(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AuthorityTemplet f = get(keys.get(i));
			all[i] = f.getLevel();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AuthorityTemplet f = get(keys.get(i));
			all[i] = f.getBattleNumber();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AuthorityTemplet f = get(keys.get(i));
			all[i] = f.getFreeTime();
		}
		return all;
	}

		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AuthorityTemplet f = get(keys.get(i));
			all[i] = f.getExpaddition();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AuthorityTemplet f = get(keys.get(i));
			all[i] = f.getLevelUpAward();
		}
		return all;
	}

		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AuthorityTemplet f = get(keys.get(i));
			all[i] = f.getInitialAddition();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AuthorityTemplet f = get(keys.get(i));
			all[i] = f.getVigorLimit();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AuthorityTemplet f = get(keys.get(i));
			all[i] = f.getPowerLimit();
		}
		return all;
	}

		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AuthorityTemplet f = get(keys.get(i));
			all[i] = f.getSnatchPar();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AuthorityTemplet f = get(keys.get(i));
			all[i] = f.getLevelUpgoods();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (AuthorityTemplet f : getAll()) {
			all.add(f.getLevel());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (AuthorityTemplet f : getAll()) {
			all.add(f.getBattleNumber());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (AuthorityTemplet f : getAll()) {
			all.add(f.getFreeTime());
		}
		return all;
	}

		List<Float> all = new ArrayList<Float>();
		for (AuthorityTemplet f : getAll()) {
			all.add(f.getExpaddition());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (AuthorityTemplet f : getAll()) {
			all.add(f.getLevelUpAward());
		}
		return all;
	}

		List<Float> all = new ArrayList<Float>();
		for (AuthorityTemplet f : getAll()) {
			all.add(f.getInitialAddition());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (AuthorityTemplet f : getAll()) {
			all.add(f.getVigorLimit());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (AuthorityTemplet f : getAll()) {
			all.add(f.getPowerLimit());
		}
		return all;
	}

		List<Float> all = new ArrayList<Float>();
		for (AuthorityTemplet f : getAll()) {
			all.add(f.getSnatchPar());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (AuthorityTemplet f : getAll()) {
			all.add(f.getLevelUpgoods());
		}
		return all;
	}
