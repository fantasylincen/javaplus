//[关卡][0]世界大陆
		List<MainlandTemplet> all = new ArrayList<MainlandTemplet>();
		for (MainlandTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<MainlandTemplet> all = new ArrayList<MainlandTemplet>();
		for (MainlandTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<MainlandTemplet> all = new ArrayList<MainlandTemplet>();
		for (MainlandTemplet f : getAll()) {
			if(equals(f.getPictype(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<MainlandTemplet> all = new ArrayList<MainlandTemplet>();
		for (MainlandTemplet f : getAll()) {
			if(equals(f.getOpen(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<MainlandTemplet> all = new ArrayList<MainlandTemplet>();
		for (MainlandTemplet f : getAll()) {
			if(equals(f.getCondition(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<MainlandTemplet> all = new ArrayList<MainlandTemplet>();
		for (MainlandTemplet f : getAll()) {
			if(equals(f.getStartCondition(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<MainlandTemplet> all = new ArrayList<MainlandTemplet>();
		for (MainlandTemplet f : getAll()) {
			if(equals(f.getOverCondition(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<MainlandTemplet> all = new ArrayList<MainlandTemplet>();
		for (MainlandTemplet f : getAll()) {
			if(equals(f.getStartChapter(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<MainlandTemplet> all = new ArrayList<MainlandTemplet>();
		for (MainlandTemplet f : getAll()) {
			if(equals(f.getOverChapter(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MainlandTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MainlandTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MainlandTemplet f = get(keys.get(i));
			all[i] = f.getPictype();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MainlandTemplet f = get(keys.get(i));
			all[i] = f.getOpen();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MainlandTemplet f = get(keys.get(i));
			all[i] = f.getCondition();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MainlandTemplet f = get(keys.get(i));
			all[i] = f.getStartCondition();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MainlandTemplet f = get(keys.get(i));
			all[i] = f.getOverCondition();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MainlandTemplet f = get(keys.get(i));
			all[i] = f.getStartChapter();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			MainlandTemplet f = get(keys.get(i));
			all[i] = f.getOverChapter();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (MainlandTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (MainlandTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (MainlandTemplet f : getAll()) {
			all.add(f.getPictype());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (MainlandTemplet f : getAll()) {
			all.add(f.getOpen());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (MainlandTemplet f : getAll()) {
			all.add(f.getCondition());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (MainlandTemplet f : getAll()) {
			all.add(f.getStartCondition());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (MainlandTemplet f : getAll()) {
			all.add(f.getOverCondition());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (MainlandTemplet f : getAll()) {
			all.add(f.getStartChapter());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (MainlandTemplet f : getAll()) {
			all.add(f.getOverChapter());
		}
		return all;
	}
