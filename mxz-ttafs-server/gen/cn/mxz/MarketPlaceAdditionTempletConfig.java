//[商城]特殊物品购买价格加成倍数
		List<MarketPlaceAdditionTemplet> all = new ArrayList<MarketPlaceAdditionTemplet>();
		for (MarketPlaceAdditionTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<MarketPlaceAdditionTemplet> all = new ArrayList<MarketPlaceAdditionTemplet>();
		for (MarketPlaceAdditionTemplet f : getAll()) {
			if(equals(f.getMin(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<MarketPlaceAdditionTemplet> all = new ArrayList<MarketPlaceAdditionTemplet>();
		for (MarketPlaceAdditionTemplet f : getAll()) {
			if(equals(f.getMax(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<MarketPlaceAdditionTemplet> all = new ArrayList<MarketPlaceAdditionTemplet>();
		for (MarketPlaceAdditionTemplet f : getAll()) {
			if(equals(f.getPropId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<MarketPlaceAdditionTemplet> all = new ArrayList<MarketPlaceAdditionTemplet>();
		for (MarketPlaceAdditionTemplet f : getAll()) {
			if(equals(f.getX(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MarketPlaceAdditionTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MarketPlaceAdditionTemplet f = get(keys.get(i));
			all[i] = f.getMin();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MarketPlaceAdditionTemplet f = get(keys.get(i));
			all[i] = f.getMax();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MarketPlaceAdditionTemplet f = get(keys.get(i));
			all[i] = f.getPropId();
		}
		return all;
	}

		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MarketPlaceAdditionTemplet f = get(keys.get(i));
			all[i] = f.getX();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (MarketPlaceAdditionTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (MarketPlaceAdditionTemplet f : getAll()) {
			all.add(f.getMin());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (MarketPlaceAdditionTemplet f : getAll()) {
			all.add(f.getMax());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (MarketPlaceAdditionTemplet f : getAll()) {
			all.add(f.getPropId());
		}
		return all;
	}

		List<Float> all = new ArrayList<Float>();
		for (MarketPlaceAdditionTemplet f : getAll()) {
			all.add(f.getX());
		}
		return all;
	}
