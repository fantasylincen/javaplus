//[奇遇]机器人难度形象配置
		List<SnatchDifficultyTemplet> all = new ArrayList<SnatchDifficultyTemplet>();
		for (SnatchDifficultyTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<SnatchDifficultyTemplet> all = new ArrayList<SnatchDifficultyTemplet>();
		for (SnatchDifficultyTemplet f : getAll()) {
			if(equals(f.getStepScope(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<SnatchDifficultyTemplet> all = new ArrayList<SnatchDifficultyTemplet>();
		for (SnatchDifficultyTemplet f : getAll()) {
			if(equals(f.getMin(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<SnatchDifficultyTemplet> all = new ArrayList<SnatchDifficultyTemplet>();
		for (SnatchDifficultyTemplet f : getAll()) {
			if(equals(f.getMax(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SnatchDifficultyTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SnatchDifficultyTemplet f = get(keys.get(i));
			all[i] = f.getStepScope();
		}
		return all;
	}

		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SnatchDifficultyTemplet f = get(keys.get(i));
			all[i] = f.getMin();
		}
		return all;
	}

		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SnatchDifficultyTemplet f = get(keys.get(i));
			all[i] = f.getMax();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (SnatchDifficultyTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (SnatchDifficultyTemplet f : getAll()) {
			all.add(f.getStepScope());
		}
		return all;
	}

		List<Float> all = new ArrayList<Float>();
		for (SnatchDifficultyTemplet f : getAll()) {
			all.add(f.getMin());
		}
		return all;
	}

		List<Float> all = new ArrayList<Float>();
		for (SnatchDifficultyTemplet f : getAll()) {
			all.add(f.getMax());
		}
		return all;
	}
