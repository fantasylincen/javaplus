//[帮助]帮助
		List<HelpTemplet> all = new ArrayList<HelpTemplet>();
		for (HelpTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<HelpTemplet> all = new ArrayList<HelpTemplet>();
		for (HelpTemplet f : getAll()) {
			if(equals(f.getCategory(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<HelpTemplet> all = new ArrayList<HelpTemplet>();
		for (HelpTemplet f : getAll()) {
			if(equals(f.getClassify(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<HelpTemplet> all = new ArrayList<HelpTemplet>();
		for (HelpTemplet f : getAll()) {
			if(equals(f.getTitle(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<HelpTemplet> all = new ArrayList<HelpTemplet>();
		for (HelpTemplet f : getAll()) {
			if(equals(f.getDescription(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			HelpTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			HelpTemplet f = get(keys.get(i));
			all[i] = f.getCategory();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			HelpTemplet f = get(keys.get(i));
			all[i] = f.getClassify();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			HelpTemplet f = get(keys.get(i));
			all[i] = f.getTitle();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			HelpTemplet f = get(keys.get(i));
			all[i] = f.getDescription();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (HelpTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (HelpTemplet f : getAll()) {
			all.add(f.getCategory());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (HelpTemplet f : getAll()) {
			all.add(f.getClassify());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (HelpTemplet f : getAll()) {
			all.add(f.getTitle());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (HelpTemplet f : getAll()) {
			all.add(f.getDescription());
		}
		return all;
	}
