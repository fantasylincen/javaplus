//[战斗]单体攻击
		List<BattleSelectorTemplet> all = new ArrayList<BattleSelectorTemplet>();
		for (BattleSelectorTemplet f : getAll()) {
			if(equals(f.getPosition(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<BattleSelectorTemplet> all = new ArrayList<BattleSelectorTemplet>();
		for (BattleSelectorTemplet f : getAll()) {
			if(equals(f.getDst(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BattleSelectorTemplet f = get(keys.get(i));
			all[i] = f.getPosition();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BattleSelectorTemplet f = get(keys.get(i));
			all[i] = f.getDst();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (BattleSelectorTemplet f : getAll()) {
			all.add(f.getPosition());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (BattleSelectorTemplet f : getAll()) {
			all.add(f.getDst());
		}
		return all;
	}
