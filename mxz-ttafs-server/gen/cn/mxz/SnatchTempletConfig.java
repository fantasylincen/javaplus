//[夺宝]夺宝
		List<SnatchTemplet> all = new ArrayList<SnatchTemplet>();
		for (SnatchTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<SnatchTemplet> all = new ArrayList<SnatchTemplet>();
		for (SnatchTemplet f : getAll()) {
			if(equals(f.getQuality(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<SnatchTemplet> all = new ArrayList<SnatchTemplet>();
		for (SnatchTemplet f : getAll()) {
			if(equals(f.getObjectType(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<SnatchTemplet> all = new ArrayList<SnatchTemplet>();
		for (SnatchTemplet f : getAll()) {
			if(equals(f.getFinder(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<SnatchTemplet> all = new ArrayList<SnatchTemplet>();
		for (SnatchTemplet f : getAll()) {
			if(equals(f.getDataNumberMin(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<SnatchTemplet> all = new ArrayList<SnatchTemplet>();
		for (SnatchTemplet f : getAll()) {
			if(equals(f.getDataNumberMax(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<SnatchTemplet> all = new ArrayList<SnatchTemplet>();
		for (SnatchTemplet f : getAll()) {
			if(equals(f.getPlunder(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<SnatchTemplet> all = new ArrayList<SnatchTemplet>();
		for (SnatchTemplet f : getAll()) {
			if(equals(f.getText(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<SnatchTemplet> all = new ArrayList<SnatchTemplet>();
		for (SnatchTemplet f : getAll()) {
			if(equals(f.getCodeId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SnatchTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SnatchTemplet f = get(keys.get(i));
			all[i] = f.getQuality();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SnatchTemplet f = get(keys.get(i));
			all[i] = f.getObjectType();
		}
		return all;
	}

		float[] all = new float[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SnatchTemplet f = get(keys.get(i));
			all[i] = f.getFinder();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SnatchTemplet f = get(keys.get(i));
			all[i] = f.getDataNumberMin();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SnatchTemplet f = get(keys.get(i));
			all[i] = f.getDataNumberMax();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SnatchTemplet f = get(keys.get(i));
			all[i] = f.getPlunder();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SnatchTemplet f = get(keys.get(i));
			all[i] = f.getText();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SnatchTemplet f = get(keys.get(i));
			all[i] = f.getCodeId();
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (SnatchTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (SnatchTemplet f : getAll()) {
			all.add(f.getQuality());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (SnatchTemplet f : getAll()) {
			all.add(f.getObjectType());
		}
		return all;
	}

		List<Float> all = new ArrayList<Float>();
		for (SnatchTemplet f : getAll()) {
			all.add(f.getFinder());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (SnatchTemplet f : getAll()) {
			all.add(f.getDataNumberMin());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (SnatchTemplet f : getAll()) {
			all.add(f.getDataNumberMax());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (SnatchTemplet f : getAll()) {
			all.add(f.getPlunder());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (SnatchTemplet f : getAll()) {
			all.add(f.getText());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (SnatchTemplet f : getAll()) {
			all.add(f.getCodeId());
		}
		return all;
	}
