//[元神]元神品阶刷新
		List<YuanShenBenchSpetTemplet> all = new ArrayList<YuanShenBenchSpetTemplet>();
		for (YuanShenBenchSpetTemplet f : getAll()) {
			if(equals(f.getStep(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<YuanShenBenchSpetTemplet> all = new ArrayList<YuanShenBenchSpetTemplet>();
		for (YuanShenBenchSpetTemplet f : getAll()) {
			if(equals(f.getWeightSans(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<YuanShenBenchSpetTemplet> all = new ArrayList<YuanShenBenchSpetTemplet>();
		for (YuanShenBenchSpetTemplet f : getAll()) {
			if(equals(f.getExplain(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<YuanShenBenchSpetTemplet> all = new ArrayList<YuanShenBenchSpetTemplet>();
		for (YuanShenBenchSpetTemplet f : getAll()) {
			if(equals(f.getMustNumber(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			YuanShenBenchSpetTemplet f = get(keys.get(i));
			all[i] = f.getStep();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			YuanShenBenchSpetTemplet f = get(keys.get(i));
			all[i] = f.getWeightSans();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			YuanShenBenchSpetTemplet f = get(keys.get(i));
			all[i] = f.getExplain();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			YuanShenBenchSpetTemplet f = get(keys.get(i));
			all[i] = f.getMustNumber();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (YuanShenBenchSpetTemplet f : getAll()) {
			all.add(f.getStep());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (YuanShenBenchSpetTemplet f : getAll()) {
			all.add(f.getWeightSans());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (YuanShenBenchSpetTemplet f : getAll()) {
			all.add(f.getExplain());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (YuanShenBenchSpetTemplet f : getAll()) {
			all.add(f.getMustNumber());
		}
		return all;
	}
