//[奇遇]33[boss]buff属性
		List<BossBuffTemplet> all = new ArrayList<BossBuffTemplet>();
		for (BossBuffTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<BossBuffTemplet> all = new ArrayList<BossBuffTemplet>();
		for (BossBuffTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<BossBuffTemplet> all = new ArrayList<BossBuffTemplet>();
		for (BossBuffTemplet f : getAll()) {
			if(equals(f.getFrontPar(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<BossBuffTemplet> all = new ArrayList<BossBuffTemplet>();
		for (BossBuffTemplet f : getAll()) {
			if(equals(f.getFrontParMax(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<BossBuffTemplet> all = new ArrayList<BossBuffTemplet>();
		for (BossBuffTemplet f : getAll()) {
			if(equals(f.getSycee(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<BossBuffTemplet> all = new ArrayList<BossBuffTemplet>();
		for (BossBuffTemplet f : getAll()) {
			if(equals(f.getAddSycee(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<BossBuffTemplet> all = new ArrayList<BossBuffTemplet>();
		for (BossBuffTemplet f : getAll()) {
			if(equals(f.getNumber(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BossBuffTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BossBuffTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}

		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BossBuffTemplet f = get(keys.get(i));
			all[i] = f.getFrontPar();
		}
		return all;
	}

		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BossBuffTemplet f = get(keys.get(i));
			all[i] = f.getFrontParMax();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BossBuffTemplet f = get(keys.get(i));
			all[i] = f.getSycee();
		}
		return all;
	}

		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BossBuffTemplet f = get(keys.get(i));
			all[i] = f.getAddSycee();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BossBuffTemplet f = get(keys.get(i));
			all[i] = f.getNumber();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (BossBuffTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (BossBuffTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}

		List<Float> all = new ArrayList<Float>();
		for (BossBuffTemplet f : getAll()) {
			all.add(f.getFrontPar());
		}
		return all;
	}

		List<Float> all = new ArrayList<Float>();
		for (BossBuffTemplet f : getAll()) {
			all.add(f.getFrontParMax());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (BossBuffTemplet f : getAll()) {
			all.add(f.getSycee());
		}
		return all;
	}

		List<Float> all = new ArrayList<Float>();
		for (BossBuffTemplet f : getAll()) {
			all.add(f.getAddSycee());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (BossBuffTemplet f : getAll()) {
			all.add(f.getNumber());
		}
		return all;
	}
