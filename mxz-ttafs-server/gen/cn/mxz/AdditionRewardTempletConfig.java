//[竞技场]修行值加成
		List<AdditionRewardTemplet> all = new ArrayList<AdditionRewardTemplet>();
		for (AdditionRewardTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<AdditionRewardTemplet> all = new ArrayList<AdditionRewardTemplet>();
		for (AdditionRewardTemplet f : getAll()) {
			if(equals(f.getRatio(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<AdditionRewardTemplet> all = new ArrayList<AdditionRewardTemplet>();
		for (AdditionRewardTemplet f : getAll()) {
			if(equals(f.getAddition(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AdditionRewardTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AdditionRewardTemplet f = get(keys.get(i));
			all[i] = f.getRatio();
		}
		return all;
	}

		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			AdditionRewardTemplet f = get(keys.get(i));
			all[i] = f.getAddition();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (AdditionRewardTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (AdditionRewardTemplet f : getAll()) {
			all.add(f.getRatio());
		}
		return all;
	}

		List<Float> all = new ArrayList<Float>();
		for (AdditionRewardTemplet f : getAll()) {
			all.add(f.getAddition());
		}
		return all;
	}
