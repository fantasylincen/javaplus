//[活动]女娲散财
		List<NvWaTemplet> all = new ArrayList<NvWaTemplet>();
		for (NvWaTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<NvWaTemplet> all = new ArrayList<NvWaTemplet>();
		for (NvWaTemplet f : getAll()) {
			if(equals(f.getVolume(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<NvWaTemplet> all = new ArrayList<NvWaTemplet>();
		for (NvWaTemplet f : getAll()) {
			if(equals(f.getDiscount(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			NvWaTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			NvWaTemplet f = get(keys.get(i));
			all[i] = f.getVolume();
		}
		return all;
	}

		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			NvWaTemplet f = get(keys.get(i));
			all[i] = f.getDiscount();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (NvWaTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (NvWaTemplet f : getAll()) {
			all.add(f.getVolume());
		}
		return all;
	}

		List<Float> all = new ArrayList<Float>();
		for (NvWaTemplet f : getAll()) {
			all.add(f.getDiscount());
		}
		return all;
	}
