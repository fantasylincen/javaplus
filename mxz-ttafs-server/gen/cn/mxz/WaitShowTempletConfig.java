//[消息]等待小贴士-未用
		List<WaitShowTemplet> all = new ArrayList<WaitShowTemplet>();
		for (WaitShowTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<WaitShowTemplet> all = new ArrayList<WaitShowTemplet>();
		for (WaitShowTemplet f : getAll()) {
			if(equals(f.getContent(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			WaitShowTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			WaitShowTemplet f = get(keys.get(i));
			all[i] = f.getContent();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (WaitShowTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (WaitShowTemplet f : getAll()) {
			all.add(f.getContent());
		}
		return all;
	}
