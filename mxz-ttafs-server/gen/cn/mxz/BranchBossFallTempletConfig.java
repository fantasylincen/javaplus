//[关卡]4[地图]支线boss的随机掉落
		List<BranchBossFallTemplet> all = new ArrayList<BranchBossFallTemplet>();
		for (BranchBossFallTemplet f : getAll()) {
			if(equals(f.getIdentif(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<BranchBossFallTemplet> all = new ArrayList<BranchBossFallTemplet>();
		for (BranchBossFallTemplet f : getAll()) {
			if(equals(f.getPropId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<BranchBossFallTemplet> all = new ArrayList<BranchBossFallTemplet>();
		for (BranchBossFallTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<BranchBossFallTemplet> all = new ArrayList<BranchBossFallTemplet>();
		for (BranchBossFallTemplet f : getAll()) {
			if(equals(f.getType(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<BranchBossFallTemplet> all = new ArrayList<BranchBossFallTemplet>();
		for (BranchBossFallTemplet f : getAll()) {
			if(equals(f.getQuality(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<BranchBossFallTemplet> all = new ArrayList<BranchBossFallTemplet>();
		for (BranchBossFallTemplet f : getAll()) {
			if(equals(f.getWeight(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<BranchBossFallTemplet> all = new ArrayList<BranchBossFallTemplet>();
		for (BranchBossFallTemplet f : getAll()) {
			if(equals(f.getRange(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<BranchBossFallTemplet> all = new ArrayList<BranchBossFallTemplet>();
		for (BranchBossFallTemplet f : getAll()) {
			if(equals(f.getModulesId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BranchBossFallTemplet f = get(keys.get(i));
			all[i] = f.getIdentif();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BranchBossFallTemplet f = get(keys.get(i));
			all[i] = f.getPropId();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BranchBossFallTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BranchBossFallTemplet f = get(keys.get(i));
			all[i] = f.getType();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BranchBossFallTemplet f = get(keys.get(i));
			all[i] = f.getQuality();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BranchBossFallTemplet f = get(keys.get(i));
			all[i] = f.getWeight();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BranchBossFallTemplet f = get(keys.get(i));
			all[i] = f.getRange();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BranchBossFallTemplet f = get(keys.get(i));
			all[i] = f.getModulesId();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (BranchBossFallTemplet f : getAll()) {
			all.add(f.getIdentif());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (BranchBossFallTemplet f : getAll()) {
			all.add(f.getPropId());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (BranchBossFallTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (BranchBossFallTemplet f : getAll()) {
			all.add(f.getType());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (BranchBossFallTemplet f : getAll()) {
			all.add(f.getQuality());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (BranchBossFallTemplet f : getAll()) {
			all.add(f.getWeight());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (BranchBossFallTemplet f : getAll()) {
			all.add(f.getRange());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (BranchBossFallTemplet f : getAll()) {
			all.add(f.getModulesId());
		}
		return all;
	}
