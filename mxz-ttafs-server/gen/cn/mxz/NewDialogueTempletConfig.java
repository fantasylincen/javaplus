//[剧情]新手剧情
		List<NewDialogueTemplet> all = new ArrayList<NewDialogueTemplet>();
		for (NewDialogueTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<NewDialogueTemplet> all = new ArrayList<NewDialogueTemplet>();
		for (NewDialogueTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<NewDialogueTemplet> all = new ArrayList<NewDialogueTemplet>();
		for (NewDialogueTemplet f : getAll()) {
			if(equals(f.getResid(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<NewDialogueTemplet> all = new ArrayList<NewDialogueTemplet>();
		for (NewDialogueTemplet f : getAll()) {
			if(equals(f.getContent(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			NewDialogueTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			NewDialogueTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			NewDialogueTemplet f = get(keys.get(i));
			all[i] = f.getResid();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			NewDialogueTemplet f = get(keys.get(i));
			all[i] = f.getContent();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (NewDialogueTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (NewDialogueTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (NewDialogueTemplet f : getAll()) {
			all.add(f.getResid());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (NewDialogueTemplet f : getAll()) {
			all.add(f.getContent());
		}
		return all;
	}
