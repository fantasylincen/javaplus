//[修练场]关注缘分加成暂时未用
		List<LotTemplet> all = new ArrayList<LotTemplet>();
		for (LotTemplet f : getAll()) {
			if(equals(f.getAttention(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<LotTemplet> all = new ArrayList<LotTemplet>();
		for (LotTemplet f : getAll()) {
			if(equals(f.getAddition(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			LotTemplet f = get(keys.get(i));
			all[i] = f.getAttention();
		}
		return all;
	}

		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			LotTemplet f = get(keys.get(i));
			all[i] = f.getAddition();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (LotTemplet f : getAll()) {
			all.add(f.getAttention());
		}
		return all;
	}

		List<Float> all = new ArrayList<Float>();
		for (LotTemplet f : getAll()) {
			all.add(f.getAddition());
		}
		return all;
	}
