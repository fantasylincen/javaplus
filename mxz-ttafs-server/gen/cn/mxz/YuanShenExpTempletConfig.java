//[元神]元神等级经验表
		List<YuanShenExpTemplet> all = new ArrayList<YuanShenExpTemplet>();
		for (YuanShenExpTemplet f : getAll()) {
			if(equals(f.getShadowLevel(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<YuanShenExpTemplet> all = new ArrayList<YuanShenExpTemplet>();
		for (YuanShenExpTemplet f : getAll()) {
			if(equals(f.getShadowExp(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<YuanShenExpTemplet> all = new ArrayList<YuanShenExpTemplet>();
		for (YuanShenExpTemplet f : getAll()) {
			if(equals(f.getShadowSumExp(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			YuanShenExpTemplet f = get(keys.get(i));
			all[i] = f.getShadowLevel();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			YuanShenExpTemplet f = get(keys.get(i));
			all[i] = f.getShadowExp();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			YuanShenExpTemplet f = get(keys.get(i));
			all[i] = f.getShadowSumExp();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (YuanShenExpTemplet f : getAll()) {
			all.add(f.getShadowLevel());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (YuanShenExpTemplet f : getAll()) {
			all.add(f.getShadowExp());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (YuanShenExpTemplet f : getAll()) {
			all.add(f.getShadowSumExp());
		}
		return all;
	}
