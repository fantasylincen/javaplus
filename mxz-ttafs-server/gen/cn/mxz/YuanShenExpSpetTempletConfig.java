//[元神]元神品质经验加成表
		List<YuanShenExpSpetTemplet> all = new ArrayList<YuanShenExpSpetTemplet>();
		for (YuanShenExpSpetTemplet f : getAll()) {
			if(equals(f.getSpet(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<YuanShenExpSpetTemplet> all = new ArrayList<YuanShenExpSpetTemplet>();
		for (YuanShenExpSpetTemplet f : getAll()) {
			if(equals(f.getSpetAdd(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			YuanShenExpSpetTemplet f = get(keys.get(i));
			all[i] = f.getSpet();
		}
		return all;
	}

		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			YuanShenExpSpetTemplet f = get(keys.get(i));
			all[i] = f.getSpetAdd();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (YuanShenExpSpetTemplet f : getAll()) {
			all.add(f.getSpet());
		}
		return all;
	}

		List<Float> all = new ArrayList<Float>();
		for (YuanShenExpSpetTemplet f : getAll()) {
			all.add(f.getSpetAdd());
		}
		return all;
	}
