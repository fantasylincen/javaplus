//[奇遇][vip]签到奖励
		List<RegistAwardTemplet> all = new ArrayList<RegistAwardTemplet>();
		for (RegistAwardTemplet f : getAll()) {
			if(equals(f.getLv(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<RegistAwardTemplet> all = new ArrayList<RegistAwardTemplet>();
		for (RegistAwardTemplet f : getAll()) {
			if(equals(f.getDay(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<RegistAwardTemplet> all = new ArrayList<RegistAwardTemplet>();
		for (RegistAwardTemplet f : getAll()) {
			if(equals(f.getTotalDay(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<RegistAwardTemplet> all = new ArrayList<RegistAwardTemplet>();
		for (RegistAwardTemplet f : getAll()) {
			if(equals(f.getAward(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<RegistAwardTemplet> all = new ArrayList<RegistAwardTemplet>();
		for (RegistAwardTemplet f : getAll()) {
			if(equals(f.getFrontFirst(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<RegistAwardTemplet> all = new ArrayList<RegistAwardTemplet>();
		for (RegistAwardTemplet f : getAll()) {
			if(equals(f.getFrontFirstFixed(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<RegistAwardTemplet> all = new ArrayList<RegistAwardTemplet>();
		for (RegistAwardTemplet f : getAll()) {
			if(equals(f.getFrontMiddle(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<RegistAwardTemplet> all = new ArrayList<RegistAwardTemplet>();
		for (RegistAwardTemplet f : getAll()) {
			if(equals(f.getFrontMiddleFixed(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<RegistAwardTemplet> all = new ArrayList<RegistAwardTemplet>();
		for (RegistAwardTemplet f : getAll()) {
			if(equals(f.getFrontTail(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<RegistAwardTemplet> all = new ArrayList<RegistAwardTemplet>();
		for (RegistAwardTemplet f : getAll()) {
			if(equals(f.getFrontTailFixed(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RegistAwardTemplet f = get(keys.get(i));
			all[i] = f.getLv();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RegistAwardTemplet f = get(keys.get(i));
			all[i] = f.getDay();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RegistAwardTemplet f = get(keys.get(i));
			all[i] = f.getTotalDay();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RegistAwardTemplet f = get(keys.get(i));
			all[i] = f.getAward();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RegistAwardTemplet f = get(keys.get(i));
			all[i] = f.getFrontFirst();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RegistAwardTemplet f = get(keys.get(i));
			all[i] = f.getFrontFirstFixed();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RegistAwardTemplet f = get(keys.get(i));
			all[i] = f.getFrontMiddle();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RegistAwardTemplet f = get(keys.get(i));
			all[i] = f.getFrontMiddleFixed();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RegistAwardTemplet f = get(keys.get(i));
			all[i] = f.getFrontTail();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RegistAwardTemplet f = get(keys.get(i));
			all[i] = f.getFrontTailFixed();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (RegistAwardTemplet f : getAll()) {
			all.add(f.getLv());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (RegistAwardTemplet f : getAll()) {
			all.add(f.getDay());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (RegistAwardTemplet f : getAll()) {
			all.add(f.getTotalDay());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (RegistAwardTemplet f : getAll()) {
			all.add(f.getAward());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (RegistAwardTemplet f : getAll()) {
			all.add(f.getFrontFirst());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (RegistAwardTemplet f : getAll()) {
			all.add(f.getFrontFirstFixed());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (RegistAwardTemplet f : getAll()) {
			all.add(f.getFrontMiddle());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (RegistAwardTemplet f : getAll()) {
			all.add(f.getFrontMiddleFixed());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (RegistAwardTemplet f : getAll()) {
			all.add(f.getFrontTail());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (RegistAwardTemplet f : getAll()) {
			all.add(f.getFrontTailFixed());
		}
		return all;
	}
