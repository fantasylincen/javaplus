//[消息提示]游戏公告
		List<GameNoticeTemplet> all = new ArrayList<GameNoticeTemplet>();
		for (GameNoticeTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<GameNoticeTemplet> all = new ArrayList<GameNoticeTemplet>();
		for (GameNoticeTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<GameNoticeTemplet> all = new ArrayList<GameNoticeTemplet>();
		for (GameNoticeTemplet f : getAll()) {
			if(equals(f.getType(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<GameNoticeTemplet> all = new ArrayList<GameNoticeTemplet>();
		for (GameNoticeTemplet f : getAll()) {
			if(equals(f.getTime(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<GameNoticeTemplet> all = new ArrayList<GameNoticeTemplet>();
		for (GameNoticeTemplet f : getAll()) {
			if(equals(f.getContent(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GameNoticeTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GameNoticeTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GameNoticeTemplet f = get(keys.get(i));
			all[i] = f.getType();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GameNoticeTemplet f = get(keys.get(i));
			all[i] = f.getTime();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			GameNoticeTemplet f = get(keys.get(i));
			all[i] = f.getContent();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (GameNoticeTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (GameNoticeTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (GameNoticeTemplet f : getAll()) {
			all.add(f.getType());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (GameNoticeTemplet f : getAll()) {
			all.add(f.getTime());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (GameNoticeTemplet f : getAll()) {
			all.add(f.getContent());
		}
		return all;
	}
