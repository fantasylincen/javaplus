//[奇遇]35[渡天劫]难度配置
		List<CopterDifficultyTemplet> all = new ArrayList<CopterDifficultyTemplet>();
		for (CopterDifficultyTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<CopterDifficultyTemplet> all = new ArrayList<CopterDifficultyTemplet>();
		for (CopterDifficultyTemplet f : getAll()) {
			if(equals(f.getNumber(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<CopterDifficultyTemplet> all = new ArrayList<CopterDifficultyTemplet>();
		for (CopterDifficultyTemplet f : getAll()) {
			if(equals(f.getDifficulty(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<CopterDifficultyTemplet> all = new ArrayList<CopterDifficultyTemplet>();
		for (CopterDifficultyTemplet f : getAll()) {
			if(equals(f.getLevelsOf(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<CopterDifficultyTemplet> all = new ArrayList<CopterDifficultyTemplet>();
		for (CopterDifficultyTemplet f : getAll()) {
			if(equals(f.getDifficultyDeploy(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<CopterDifficultyTemplet> all = new ArrayList<CopterDifficultyTemplet>();
		for (CopterDifficultyTemplet f : getAll()) {
			if(equals(f.getStar(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<CopterDifficultyTemplet> all = new ArrayList<CopterDifficultyTemplet>();
		for (CopterDifficultyTemplet f : getAll()) {
			if(equals(f.getFactor(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterDifficultyTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterDifficultyTemplet f = get(keys.get(i));
			all[i] = f.getNumber();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterDifficultyTemplet f = get(keys.get(i));
			all[i] = f.getDifficulty();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterDifficultyTemplet f = get(keys.get(i));
			all[i] = f.getLevelsOf();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterDifficultyTemplet f = get(keys.get(i));
			all[i] = f.getDifficultyDeploy();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterDifficultyTemplet f = get(keys.get(i));
			all[i] = f.getStar();
		}
		return all;
	}

		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CopterDifficultyTemplet f = get(keys.get(i));
			all[i] = f.getFactor();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (CopterDifficultyTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (CopterDifficultyTemplet f : getAll()) {
			all.add(f.getNumber());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (CopterDifficultyTemplet f : getAll()) {
			all.add(f.getDifficulty());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (CopterDifficultyTemplet f : getAll()) {
			all.add(f.getLevelsOf());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (CopterDifficultyTemplet f : getAll()) {
			all.add(f.getDifficultyDeploy());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (CopterDifficultyTemplet f : getAll()) {
			all.add(f.getStar());
		}
		return all;
	}

		List<Float> all = new ArrayList<Float>();
		for (CopterDifficultyTemplet f : getAll()) {
			all.add(f.getFactor());
		}
		return all;
	}
