//[关卡]随机事件剧本
		List<RandomBookTemplet> all = new ArrayList<RandomBookTemplet>();
		for (RandomBookTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<RandomBookTemplet> all = new ArrayList<RandomBookTemplet>();
		for (RandomBookTemplet f : getAll()) {
			if(equals(f.getStoryId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<RandomBookTemplet> all = new ArrayList<RandomBookTemplet>();
		for (RandomBookTemplet f : getAll()) {
			if(equals(f.getType(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<RandomBookTemplet> all = new ArrayList<RandomBookTemplet>();
		for (RandomBookTemplet f : getAll()) {
			if(equals(f.getUserId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<RandomBookTemplet> all = new ArrayList<RandomBookTemplet>();
		for (RandomBookTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<RandomBookTemplet> all = new ArrayList<RandomBookTemplet>();
		for (RandomBookTemplet f : getAll()) {
			if(equals(f.getIsEnd(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<RandomBookTemplet> all = new ArrayList<RandomBookTemplet>();
		for (RandomBookTemplet f : getAll()) {
			if(equals(f.getPosi(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<RandomBookTemplet> all = new ArrayList<RandomBookTemplet>();
		for (RandomBookTemplet f : getAll()) {
			if(equals(f.getUrl(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<RandomBookTemplet> all = new ArrayList<RandomBookTemplet>();
		for (RandomBookTemplet f : getAll()) {
			if(equals(f.getContent(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomBookTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomBookTemplet f = get(keys.get(i));
			all[i] = f.getStoryId();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomBookTemplet f = get(keys.get(i));
			all[i] = f.getType();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomBookTemplet f = get(keys.get(i));
			all[i] = f.getUserId();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomBookTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomBookTemplet f = get(keys.get(i));
			all[i] = f.getIsEnd();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomBookTemplet f = get(keys.get(i));
			all[i] = f.getPosi();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomBookTemplet f = get(keys.get(i));
			all[i] = f.getUrl();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RandomBookTemplet f = get(keys.get(i));
			all[i] = f.getContent();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (RandomBookTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (RandomBookTemplet f : getAll()) {
			all.add(f.getStoryId());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (RandomBookTemplet f : getAll()) {
			all.add(f.getType());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (RandomBookTemplet f : getAll()) {
			all.add(f.getUserId());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (RandomBookTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (RandomBookTemplet f : getAll()) {
			all.add(f.getIsEnd());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (RandomBookTemplet f : getAll()) {
			all.add(f.getPosi());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (RandomBookTemplet f : getAll()) {
			all.add(f.getUrl());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (RandomBookTemplet f : getAll()) {
			all.add(f.getContent());
		}
		return all;
	}
