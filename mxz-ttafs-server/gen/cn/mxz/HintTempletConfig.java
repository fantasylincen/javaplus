//[消息提示]提示系统
		List<HintTemplet> all = new ArrayList<HintTemplet>();
		for (HintTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<HintTemplet> all = new ArrayList<HintTemplet>();
		for (HintTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<HintTemplet> all = new ArrayList<HintTemplet>();
		for (HintTemplet f : getAll()) {
			if(equals(f.getLink(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<HintTemplet> all = new ArrayList<HintTemplet>();
		for (HintTemplet f : getAll()) {
			if(equals(f.getShuoming1(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<HintTemplet> all = new ArrayList<HintTemplet>();
		for (HintTemplet f : getAll()) {
			if(equals(f.getDescription1(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<HintTemplet> all = new ArrayList<HintTemplet>();
		for (HintTemplet f : getAll()) {
			if(equals(f.getDescription2(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<HintTemplet> all = new ArrayList<HintTemplet>();
		for (HintTemplet f : getAll()) {
			if(equals(f.getFormat(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<HintTemplet> all = new ArrayList<HintTemplet>();
		for (HintTemplet f : getAll()) {
			if(equals(f.getResid(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<HintTemplet> all = new ArrayList<HintTemplet>();
		for (HintTemplet f : getAll()) {
			if(equals(f.getShuoming2(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			HintTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			HintTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			HintTemplet f = get(keys.get(i));
			all[i] = f.getLink();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			HintTemplet f = get(keys.get(i));
			all[i] = f.getShuoming1();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			HintTemplet f = get(keys.get(i));
			all[i] = f.getDescription1();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			HintTemplet f = get(keys.get(i));
			all[i] = f.getDescription2();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			HintTemplet f = get(keys.get(i));
			all[i] = f.getFormat();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			HintTemplet f = get(keys.get(i));
			all[i] = f.getResid();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			HintTemplet f = get(keys.get(i));
			all[i] = f.getShuoming2();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (HintTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (HintTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (HintTemplet f : getAll()) {
			all.add(f.getLink());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (HintTemplet f : getAll()) {
			all.add(f.getShuoming1());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (HintTemplet f : getAll()) {
			all.add(f.getDescription1());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (HintTemplet f : getAll()) {
			all.add(f.getDescription2());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (HintTemplet f : getAll()) {
			all.add(f.getFormat());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (HintTemplet f : getAll()) {
			all.add(f.getResid());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (HintTemplet f : getAll()) {
			all.add(f.getShuoming2());
		}
		return all;
	}
