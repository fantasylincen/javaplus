//[奇遇]26[摇钱树]
		List<CornucopiaTemplet> all = new ArrayList<CornucopiaTemplet>();
		for (CornucopiaTemplet f : getAll()) {
			if(equals(f.getTimes(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<CornucopiaTemplet> all = new ArrayList<CornucopiaTemplet>();
		for (CornucopiaTemplet f : getAll()) {
			if(equals(f.getCouponsNeed(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<CornucopiaTemplet> all = new ArrayList<CornucopiaTemplet>();
		for (CornucopiaTemplet f : getAll()) {
			if(equals(f.getCash(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<CornucopiaTemplet> all = new ArrayList<CornucopiaTemplet>();
		for (CornucopiaTemplet f : getAll()) {
			if(equals(f.getExplain(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CornucopiaTemplet f = get(keys.get(i));
			all[i] = f.getTimes();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CornucopiaTemplet f = get(keys.get(i));
			all[i] = f.getCouponsNeed();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CornucopiaTemplet f = get(keys.get(i));
			all[i] = f.getCash();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			CornucopiaTemplet f = get(keys.get(i));
			all[i] = f.getExplain();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (CornucopiaTemplet f : getAll()) {
			all.add(f.getTimes());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (CornucopiaTemplet f : getAll()) {
			all.add(f.getCouponsNeed());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (CornucopiaTemplet f : getAll()) {
			all.add(f.getCash());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (CornucopiaTemplet f : getAll()) {
			all.add(f.getExplain());
		}
		return all;
	}
