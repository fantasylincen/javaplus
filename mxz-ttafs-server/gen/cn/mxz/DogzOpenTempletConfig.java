//[神兽]神兽开启
		List<DogzOpenTemplet> all = new ArrayList<DogzOpenTemplet>();
		for (DogzOpenTemplet f : getAll()) {
			if(equals(f.getCount(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<DogzOpenTemplet> all = new ArrayList<DogzOpenTemplet>();
		for (DogzOpenTemplet f : getAll()) {
			if(equals(f.getUserLevel(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<DogzOpenTemplet> all = new ArrayList<DogzOpenTemplet>();
		for (DogzOpenTemplet f : getAll()) {
			if(equals(f.getDogzLevel(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<DogzOpenTemplet> all = new ArrayList<DogzOpenTemplet>();
		for (DogzOpenTemplet f : getAll()) {
			if(equals(f.getComment(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DogzOpenTemplet f = get(keys.get(i));
			all[i] = f.getCount();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DogzOpenTemplet f = get(keys.get(i));
			all[i] = f.getUserLevel();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DogzOpenTemplet f = get(keys.get(i));
			all[i] = f.getDogzLevel();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			DogzOpenTemplet f = get(keys.get(i));
			all[i] = f.getComment();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (DogzOpenTemplet f : getAll()) {
			all.add(f.getCount());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (DogzOpenTemplet f : getAll()) {
			all.add(f.getUserLevel());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (DogzOpenTemplet f : getAll()) {
			all.add(f.getDogzLevel());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (DogzOpenTemplet f : getAll()) {
			all.add(f.getComment());
		}
		return all;
	}
