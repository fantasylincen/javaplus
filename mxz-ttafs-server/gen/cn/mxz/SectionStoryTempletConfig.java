//[剧情]章节节点剧情
		List<SectionStoryTemplet> all = new ArrayList<SectionStoryTemplet>();
		for (SectionStoryTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<SectionStoryTemplet> all = new ArrayList<SectionStoryTemplet>();
		for (SectionStoryTemplet f : getAll()) {
			if(equals(f.getStoryid(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<SectionStoryTemplet> all = new ArrayList<SectionStoryTemplet>();
		for (SectionStoryTemplet f : getAll()) {
			if(equals(f.getName(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<SectionStoryTemplet> all = new ArrayList<SectionStoryTemplet>();
		for (SectionStoryTemplet f : getAll()) {
			if(equals(f.getContent(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<SectionStoryTemplet> all = new ArrayList<SectionStoryTemplet>();
		for (SectionStoryTemplet f : getAll()) {
			if(equals(f.getCoord(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<SectionStoryTemplet> all = new ArrayList<SectionStoryTemplet>();
		for (SectionStoryTemplet f : getAll()) {
			if(equals(f.getPicid(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<SectionStoryTemplet> all = new ArrayList<SectionStoryTemplet>();
		for (SectionStoryTemplet f : getAll()) {
			if(equals(f.getUrl(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SectionStoryTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SectionStoryTemplet f = get(keys.get(i));
			all[i] = f.getStoryid();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SectionStoryTemplet f = get(keys.get(i));
			all[i] = f.getName();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SectionStoryTemplet f = get(keys.get(i));
			all[i] = f.getContent();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SectionStoryTemplet f = get(keys.get(i));
			all[i] = f.getCoord();
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SectionStoryTemplet f = get(keys.get(i));
			all[i] = f.getPicid();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SectionStoryTemplet f = get(keys.get(i));
			all[i] = f.getUrl();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (SectionStoryTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (SectionStoryTemplet f : getAll()) {
			all.add(f.getStoryid());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (SectionStoryTemplet f : getAll()) {
			all.add(f.getName());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (SectionStoryTemplet f : getAll()) {
			all.add(f.getContent());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (SectionStoryTemplet f : getAll()) {
			all.add(f.getCoord());
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (SectionStoryTemplet f : getAll()) {
			all.add(f.getPicid());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (SectionStoryTemplet f : getAll()) {
			all.add(f.getUrl());
		}
		return all;
	}
