//[神兽]神兽成长
		List<DogzLevelTemplet> all = new ArrayList<DogzLevelTemplet>();
		for (DogzLevelTemplet f : getAll()) {
			if(equals(f.getLevel(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<DogzLevelTemplet> all = new ArrayList<DogzLevelTemplet>();
		for (DogzLevelTemplet f : getAll()) {
			if(equals(f.getSoulPower(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<DogzLevelTemplet> all = new ArrayList<DogzLevelTemplet>();
		for (DogzLevelTemplet f : getAll()) {
			if(equals(f.getSoulNumber(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<DogzLevelTemplet> all = new ArrayList<DogzLevelTemplet>();
		for (DogzLevelTemplet f : getAll()) {
			if(equals(f.getUpgrade(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<DogzLevelTemplet> all = new ArrayList<DogzLevelTemplet>();
		for (DogzLevelTemplet f : getAll()) {
			if(equals(f.getSingleSoulPower(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<DogzLevelTemplet> all = new ArrayList<DogzLevelTemplet>();
		for (DogzLevelTemplet f : getAll()) {
			if(equals(f.getSumSoulNumber(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<DogzLevelTemplet> all = new ArrayList<DogzLevelTemplet>();
		for (DogzLevelTemplet f : getAll()) {
			if(equals(f.getSocial(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DogzLevelTemplet f = get(keys.get(i));
			all[i] = f.getLevel();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DogzLevelTemplet f = get(keys.get(i));
			all[i] = f.getSoulPower();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DogzLevelTemplet f = get(keys.get(i));
			all[i] = f.getSoulNumber();
		}
		return all;
	}

		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DogzLevelTemplet f = get(keys.get(i));
			all[i] = f.getUpgrade();
		}
		return all;
	}

		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DogzLevelTemplet f = get(keys.get(i));
			all[i] = f.getSingleSoulPower();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DogzLevelTemplet f = get(keys.get(i));
			all[i] = f.getSumSoulNumber();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DogzLevelTemplet f = get(keys.get(i));
			all[i] = f.getSocial();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (DogzLevelTemplet f : getAll()) {
			all.add(f.getLevel());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (DogzLevelTemplet f : getAll()) {
			all.add(f.getSoulPower());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (DogzLevelTemplet f : getAll()) {
			all.add(f.getSoulNumber());
		}
		return all;
	}

		List<Float> all = new ArrayList<Float>();
		for (DogzLevelTemplet f : getAll()) {
			all.add(f.getUpgrade());
		}
		return all;
	}

		List<Float> all = new ArrayList<Float>();
		for (DogzLevelTemplet f : getAll()) {
			all.add(f.getSingleSoulPower());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (DogzLevelTemplet f : getAll()) {
			all.add(f.getSumSoulNumber());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (DogzLevelTemplet f : getAll()) {
			all.add(f.getSocial());
		}
		return all;
	}
