//[仙市]聚魂招募
		List<JuHunRecruitTemplet> all = new ArrayList<JuHunRecruitTemplet>();
		for (JuHunRecruitTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<JuHunRecruitTemplet> all = new ArrayList<JuHunRecruitTemplet>();
		for (JuHunRecruitTemplet f : getAll()) {
			if(equals(f.getSteps(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<JuHunRecruitTemplet> all = new ArrayList<JuHunRecruitTemplet>();
		for (JuHunRecruitTemplet f : getAll()) {
			if(equals(f.getNeed(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<JuHunRecruitTemplet> all = new ArrayList<JuHunRecruitTemplet>();
		for (JuHunRecruitTemplet f : getAll()) {
			if(equals(f.getGodBank(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			JuHunRecruitTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			JuHunRecruitTemplet f = get(keys.get(i));
			all[i] = f.getSteps();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			JuHunRecruitTemplet f = get(keys.get(i));
			all[i] = f.getNeed();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			JuHunRecruitTemplet f = get(keys.get(i));
			all[i] = f.getGodBank();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (JuHunRecruitTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (JuHunRecruitTemplet f : getAll()) {
			all.add(f.getSteps());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (JuHunRecruitTemplet f : getAll()) {
			all.add(f.getNeed());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (JuHunRecruitTemplet f : getAll()) {
			all.add(f.getGodBank());
		}
		return all;
	}
