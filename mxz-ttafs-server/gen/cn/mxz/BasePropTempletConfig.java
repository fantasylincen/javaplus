//[奖励]id类型表
		List<BasePropTemplet> all = new ArrayList<BasePropTemplet>();
		for (BasePropTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<BasePropTemplet> all = new ArrayList<BasePropTemplet>();
		for (BasePropTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<BasePropTemplet> all = new ArrayList<BasePropTemplet>();
		for (BasePropTemplet f : getAll()) {
			if(equals(f.getDescription(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<BasePropTemplet> all = new ArrayList<BasePropTemplet>();
		for (BasePropTemplet f : getAll()) {
			if(equals(f.getXml(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BasePropTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BasePropTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BasePropTemplet f = get(keys.get(i));
			all[i] = f.getDescription();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			BasePropTemplet f = get(keys.get(i));
			all[i] = f.getXml();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (BasePropTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (BasePropTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (BasePropTemplet f : getAll()) {
			all.add(f.getDescription());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (BasePropTemplet f : getAll()) {
			all.add(f.getXml());
		}
		return all;
	}
