//[奇遇][邀请有礼]邀请有礼
		List<FriendsFeedbackTemplet> all = new ArrayList<FriendsFeedbackTemplet>();
		for (FriendsFeedbackTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<FriendsFeedbackTemplet> all = new ArrayList<FriendsFeedbackTemplet>();
		for (FriendsFeedbackTemplet f : getAll()) {
			if(equals(f.getNumber(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<FriendsFeedbackTemplet> all = new ArrayList<FriendsFeedbackTemplet>();
		for (FriendsFeedbackTemplet f : getAll()) {
			if(equals(f.getLevel(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<FriendsFeedbackTemplet> all = new ArrayList<FriendsFeedbackTemplet>();
		for (FriendsFeedbackTemplet f : getAll()) {
			if(equals(f.getAward(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<FriendsFeedbackTemplet> all = new ArrayList<FriendsFeedbackTemplet>();
		for (FriendsFeedbackTemplet f : getAll()) {
			if(equals(f.getDescription(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FriendsFeedbackTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FriendsFeedbackTemplet f = get(keys.get(i));
			all[i] = f.getNumber();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FriendsFeedbackTemplet f = get(keys.get(i));
			all[i] = f.getLevel();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FriendsFeedbackTemplet f = get(keys.get(i));
			all[i] = f.getAward();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			FriendsFeedbackTemplet f = get(keys.get(i));
			all[i] = f.getDescription();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (FriendsFeedbackTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (FriendsFeedbackTemplet f : getAll()) {
			all.add(f.getNumber());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (FriendsFeedbackTemplet f : getAll()) {
			all.add(f.getLevel());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (FriendsFeedbackTemplet f : getAll()) {
			all.add(f.getAward());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (FriendsFeedbackTemplet f : getAll()) {
			all.add(f.getDescription());
		}
		return all;
	}
