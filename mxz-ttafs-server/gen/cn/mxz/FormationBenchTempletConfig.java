//[阵法]替补属性库
		List<FormationBenchTemplet> all = new ArrayList<FormationBenchTemplet>();
		for (FormationBenchTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<FormationBenchTemplet> all = new ArrayList<FormationBenchTemplet>();
		for (FormationBenchTemplet f : getAll()) {
			if(equals(f.getSpet(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<FormationBenchTemplet> all = new ArrayList<FormationBenchTemplet>();
		for (FormationBenchTemplet f : getAll()) {
			if(equals(f.getNatureType(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<FormationBenchTemplet> all = new ArrayList<FormationBenchTemplet>();
		for (FormationBenchTemplet f : getAll()) {
			if(equals(f.getAddition(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<FormationBenchTemplet> all = new ArrayList<FormationBenchTemplet>();
		for (FormationBenchTemplet f : getAll()) {
			if(equals(f.getWeight(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormationBenchTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormationBenchTemplet f = get(keys.get(i));
			all[i] = f.getSpet();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormationBenchTemplet f = get(keys.get(i));
			all[i] = f.getNatureType();
		}
		return all;
	}

		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormationBenchTemplet f = get(keys.get(i));
			all[i] = f.getAddition();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FormationBenchTemplet f = get(keys.get(i));
			all[i] = f.getWeight();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (FormationBenchTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (FormationBenchTemplet f : getAll()) {
			all.add(f.getSpet());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (FormationBenchTemplet f : getAll()) {
			all.add(f.getNatureType());
		}
		return all;
	}

		List<Float> all = new ArrayList<Float>();
		for (FormationBenchTemplet f : getAll()) {
			all.add(f.getAddition());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (FormationBenchTemplet f : getAll()) {
			all.add(f.getWeight());
		}
		return all;
	}
