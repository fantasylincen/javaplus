//[角色名称]名称
		List<RoleNameTemplet> all = new ArrayList<RoleNameTemplet>();
		for (RoleNameTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<RoleNameTemplet> all = new ArrayList<RoleNameTemplet>();
		for (RoleNameTemplet f : getAll()) {
			if(equals(f.getFirst(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<RoleNameTemplet> all = new ArrayList<RoleNameTemplet>();
		for (RoleNameTemplet f : getAll()) {
			if(equals(f.getLast(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RoleNameTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RoleNameTemplet f = get(keys.get(i));
			all[i] = f.getFirst();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			RoleNameTemplet f = get(keys.get(i));
			all[i] = f.getLast();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (RoleNameTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (RoleNameTemplet f : getAll()) {
			all.add(f.getFirst());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (RoleNameTemplet f : getAll()) {
			all.add(f.getLast());
		}
		return all;
	}
