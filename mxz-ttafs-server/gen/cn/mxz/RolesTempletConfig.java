//[各种规则]
		List<RolesTemplet> all = new ArrayList<RolesTemplet>();
		for (RolesTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<RolesTemplet> all = new ArrayList<RolesTemplet>();
		for (RolesTemplet f : getAll()) {
			if(equals(f.getRole(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<RolesTemplet> all = new ArrayList<RolesTemplet>();
		for (RolesTemplet f : getAll()) {
			if(equals(f.getExplain(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RolesTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RolesTemplet f = get(keys.get(i));
			all[i] = f.getRole();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RolesTemplet f = get(keys.get(i));
			all[i] = f.getExplain();
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (RolesTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (RolesTemplet f : getAll()) {
			all.add(f.getRole());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (RolesTemplet f : getAll()) {
			all.add(f.getExplain());
		}
		return all;
	}
