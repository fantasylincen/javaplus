//[关卡]宝箱几率
		List<BoxOddsTemplet> all = new ArrayList<BoxOddsTemplet>();
		for (BoxOddsTemplet f : getAll()) {
			if(equals(f.getBoxId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<BoxOddsTemplet> all = new ArrayList<BoxOddsTemplet>();
		for (BoxOddsTemplet f : getAll()) {
			if(equals(f.getBoxName(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<BoxOddsTemplet> all = new ArrayList<BoxOddsTemplet>();
		for (BoxOddsTemplet f : getAll()) {
			if(equals(f.getMapId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<BoxOddsTemplet> all = new ArrayList<BoxOddsTemplet>();
		for (BoxOddsTemplet f : getAll()) {
			if(equals(f.getBoxWeight(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BoxOddsTemplet f = get(keys.get(i));
			all[i] = f.getBoxId();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BoxOddsTemplet f = get(keys.get(i));
			all[i] = f.getBoxName();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BoxOddsTemplet f = get(keys.get(i));
			all[i] = f.getMapId();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BoxOddsTemplet f = get(keys.get(i));
			all[i] = f.getBoxWeight();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (BoxOddsTemplet f : getAll()) {
			all.add(f.getBoxId());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (BoxOddsTemplet f : getAll()) {
			all.add(f.getBoxName());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (BoxOddsTemplet f : getAll()) {
			all.add(f.getMapId());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (BoxOddsTemplet f : getAll()) {
			all.add(f.getBoxWeight());
		}
		return all;
	}
