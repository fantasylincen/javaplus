//[礼包]充值回馈
		List<FeedBackTemplet> all = new ArrayList<FeedBackTemplet>();
		for (FeedBackTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<FeedBackTemplet> all = new ArrayList<FeedBackTemplet>();
		for (FeedBackTemplet f : getAll()) {
			if(equals(f.getBagid(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<FeedBackTemplet> all = new ArrayList<FeedBackTemplet>();
		for (FeedBackTemplet f : getAll()) {
			if(equals(f.getBagName(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<FeedBackTemplet> all = new ArrayList<FeedBackTemplet>();
		for (FeedBackTemplet f : getAll()) {
			if(equals(f.getMoney(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<FeedBackTemplet> all = new ArrayList<FeedBackTemplet>();
		for (FeedBackTemplet f : getAll()) {
			if(equals(f.getLimit(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FeedBackTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FeedBackTemplet f = get(keys.get(i));
			all[i] = f.getBagid();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FeedBackTemplet f = get(keys.get(i));
			all[i] = f.getBagName();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FeedBackTemplet f = get(keys.get(i));
			all[i] = f.getMoney();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FeedBackTemplet f = get(keys.get(i));
			all[i] = f.getLimit();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (FeedBackTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (FeedBackTemplet f : getAll()) {
			all.add(f.getBagid());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (FeedBackTemplet f : getAll()) {
			all.add(f.getBagName());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (FeedBackTemplet f : getAll()) {
			all.add(f.getMoney());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (FeedBackTemplet f : getAll()) {
			all.add(f.getLimit());
		}
		return all;
	}
