//[消息提示]
		List<WaitMessageTemplet> all = new ArrayList<WaitMessageTemplet>();
		for (WaitMessageTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<WaitMessageTemplet> all = new ArrayList<WaitMessageTemplet>();
		for (WaitMessageTemplet f : getAll()) {
			if(equals(f.getContent(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			WaitMessageTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			WaitMessageTemplet f = get(keys.get(i));
			all[i] = f.getContent();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (WaitMessageTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (WaitMessageTemplet f : getAll()) {
			all.add(f.getContent());
		}
		return all;
	}
