//[元神]元神位置开启
		List<YuanShenPlaceTemplet> all = new ArrayList<YuanShenPlaceTemplet>();
		for (YuanShenPlaceTemplet f : getAll()) {
			if(equals(f.getSpiritPlace(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<YuanShenPlaceTemplet> all = new ArrayList<YuanShenPlaceTemplet>();
		for (YuanShenPlaceTemplet f : getAll()) {
			if(equals(f.getLevel(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			YuanShenPlaceTemplet f = get(keys.get(i));
			all[i] = f.getSpiritPlace();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			YuanShenPlaceTemplet f = get(keys.get(i));
			all[i] = f.getLevel();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (YuanShenPlaceTemplet f : getAll()) {
			all.add(f.getSpiritPlace());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (YuanShenPlaceTemplet f : getAll()) {
			all.add(f.getLevel());
		}
		return all;
	}
