//[恩怨系统消息]
		List<GrudgeSystemTemplet> all = new ArrayList<GrudgeSystemTemplet>();
		for (GrudgeSystemTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<GrudgeSystemTemplet> all = new ArrayList<GrudgeSystemTemplet>();
		for (GrudgeSystemTemplet f : getAll()) {
			if(equals(f.getType(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<GrudgeSystemTemplet> all = new ArrayList<GrudgeSystemTemplet>();
		for (GrudgeSystemTemplet f : getAll()) {
			if(equals(f.getTypeName(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<GrudgeSystemTemplet> all = new ArrayList<GrudgeSystemTemplet>();
		for (GrudgeSystemTemplet f : getAll()) {
			if(equals(f.getContent(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<GrudgeSystemTemplet> all = new ArrayList<GrudgeSystemTemplet>();
		for (GrudgeSystemTemplet f : getAll()) {
			if(equals(f.getModuleName(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<GrudgeSystemTemplet> all = new ArrayList<GrudgeSystemTemplet>();
		for (GrudgeSystemTemplet f : getAll()) {
			if(equals(f.getResid(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<GrudgeSystemTemplet> all = new ArrayList<GrudgeSystemTemplet>();
		for (GrudgeSystemTemplet f : getAll()) {
			if(equals(f.getFormat(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<GrudgeSystemTemplet> all = new ArrayList<GrudgeSystemTemplet>();
		for (GrudgeSystemTemplet f : getAll()) {
			if(equals(f.getUrl(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GrudgeSystemTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GrudgeSystemTemplet f = get(keys.get(i));
			all[i] = f.getType();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GrudgeSystemTemplet f = get(keys.get(i));
			all[i] = f.getTypeName();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GrudgeSystemTemplet f = get(keys.get(i));
			all[i] = f.getContent();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GrudgeSystemTemplet f = get(keys.get(i));
			all[i] = f.getModuleName();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GrudgeSystemTemplet f = get(keys.get(i));
			all[i] = f.getResid();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GrudgeSystemTemplet f = get(keys.get(i));
			all[i] = f.getFormat();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GrudgeSystemTemplet f = get(keys.get(i));
			all[i] = f.getUrl();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (GrudgeSystemTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (GrudgeSystemTemplet f : getAll()) {
			all.add(f.getType());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (GrudgeSystemTemplet f : getAll()) {
			all.add(f.getTypeName());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (GrudgeSystemTemplet f : getAll()) {
			all.add(f.getContent());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (GrudgeSystemTemplet f : getAll()) {
			all.add(f.getModuleName());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (GrudgeSystemTemplet f : getAll()) {
			all.add(f.getResid());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (GrudgeSystemTemplet f : getAll()) {
			all.add(f.getFormat());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (GrudgeSystemTemplet f : getAll()) {
			all.add(f.getUrl());
		}
		return all;
	}
