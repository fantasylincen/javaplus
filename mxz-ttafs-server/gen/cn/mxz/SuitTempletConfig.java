//[道具][装备]套装属性
		List<SuitTemplet> all = new ArrayList<SuitTemplet>();
		for (SuitTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<SuitTemplet> all = new ArrayList<SuitTemplet>();
		for (SuitTemplet f : getAll()) {
			if(equals(f.getSuitId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<SuitTemplet> all = new ArrayList<SuitTemplet>();
		for (SuitTemplet f : getAll()) {
			if(equals(f.getRow(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<SuitTemplet> all = new ArrayList<SuitTemplet>();
		for (SuitTemplet f : getAll()) {
			if(equals(f.getBaseAdditionType(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<SuitTemplet> all = new ArrayList<SuitTemplet>();
		for (SuitTemplet f : getAll()) {
			if(equals(f.getAdditionValue(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SuitTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SuitTemplet f = get(keys.get(i));
			all[i] = f.getSuitId();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SuitTemplet f = get(keys.get(i));
			all[i] = f.getRow();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SuitTemplet f = get(keys.get(i));
			all[i] = f.getBaseAdditionType();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SuitTemplet f = get(keys.get(i));
			all[i] = f.getAdditionValue();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (SuitTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (SuitTemplet f : getAll()) {
			all.add(f.getSuitId());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (SuitTemplet f : getAll()) {
			all.add(f.getRow());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (SuitTemplet f : getAll()) {
			all.add(f.getBaseAdditionType());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (SuitTemplet f : getAll()) {
			all.add(f.getAdditionValue());
		}
		return all;
	}
