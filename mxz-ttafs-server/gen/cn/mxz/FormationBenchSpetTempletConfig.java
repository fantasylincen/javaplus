//[阵法]替补属性品质刷新
		List<FormationBenchSpetTemplet> all = new ArrayList<FormationBenchSpetTemplet>();
		for (FormationBenchSpetTemplet f : getAll()) {
			if(equals(f.getSpet(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<FormationBenchSpetTemplet> all = new ArrayList<FormationBenchSpetTemplet>();
		for (FormationBenchSpetTemplet f : getAll()) {
			if(equals(f.getWeight(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<FormationBenchSpetTemplet> all = new ArrayList<FormationBenchSpetTemplet>();
		for (FormationBenchSpetTemplet f : getAll()) {
			if(equals(f.getExplain(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<FormationBenchSpetTemplet> all = new ArrayList<FormationBenchSpetTemplet>();
		for (FormationBenchSpetTemplet f : getAll()) {
			if(equals(f.getMustNumber(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormationBenchSpetTemplet f = get(keys.get(i));
			all[i] = f.getSpet();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormationBenchSpetTemplet f = get(keys.get(i));
			all[i] = f.getWeight();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormationBenchSpetTemplet f = get(keys.get(i));
			all[i] = f.getExplain();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormationBenchSpetTemplet f = get(keys.get(i));
			all[i] = f.getMustNumber();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (FormationBenchSpetTemplet f : getAll()) {
			all.add(f.getSpet());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (FormationBenchSpetTemplet f : getAll()) {
			all.add(f.getWeight());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (FormationBenchSpetTemplet f : getAll()) {
			all.add(f.getExplain());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (FormationBenchSpetTemplet f : getAll()) {
			all.add(f.getMustNumber());
		}
		return all;
	}
