//[阵法]被升级阵法概率加成
		List<FormationHostTemplet> all = new ArrayList<FormationHostTemplet>();
		for (FormationHostTemplet f : getAll()) {
			if(equals(f.getSpetLv(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<FormationHostTemplet> all = new ArrayList<FormationHostTemplet>();
		for (FormationHostTemplet f : getAll()) {
			if(equals(f.getFormationSpet(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<FormationHostTemplet> all = new ArrayList<FormationHostTemplet>();
		for (FormationHostTemplet f : getAll()) {
			if(equals(f.getFormationLv(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<FormationHostTemplet> all = new ArrayList<FormationHostTemplet>();
		for (FormationHostTemplet f : getAll()) {
			if(equals(f.getHostPro(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<FormationHostTemplet> all = new ArrayList<FormationHostTemplet>();
		for (FormationHostTemplet f : getAll()) {
			if(equals(f.getSumPro(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<FormationHostTemplet> all = new ArrayList<FormationHostTemplet>();
		for (FormationHostTemplet f : getAll()) {
			if(equals(f.getCashNeed(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormationHostTemplet f = get(keys.get(i));
			all[i] = f.getSpetLv();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormationHostTemplet f = get(keys.get(i));
			all[i] = f.getFormationSpet();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormationHostTemplet f = get(keys.get(i));
			all[i] = f.getFormationLv();
		}
		return all;
	}

		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormationHostTemplet f = get(keys.get(i));
			all[i] = f.getHostPro();
		}
		return all;
	}

		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormationHostTemplet f = get(keys.get(i));
			all[i] = f.getSumPro();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormationHostTemplet f = get(keys.get(i));
			all[i] = f.getCashNeed();
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (FormationHostTemplet f : getAll()) {
			all.add(f.getSpetLv());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (FormationHostTemplet f : getAll()) {
			all.add(f.getFormationSpet());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (FormationHostTemplet f : getAll()) {
			all.add(f.getFormationLv());
		}
		return all;
	}

		List<Float> all = new ArrayList<Float>();
		for (FormationHostTemplet f : getAll()) {
			all.add(f.getHostPro());
		}
		return all;
	}

		List<Float> all = new ArrayList<Float>();
		for (FormationHostTemplet f : getAll()) {
			all.add(f.getSumPro());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (FormationHostTemplet f : getAll()) {
			all.add(f.getCashNeed());
		}
		return all;
	}
