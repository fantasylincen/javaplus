//[剧情]章节剧情字幕
		List<SectionTitleTemplet> all = new ArrayList<SectionTitleTemplet>();
		for (SectionTitleTemplet f : getAll()) {
			if(equals(f.getId(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<SectionTitleTemplet> all = new ArrayList<SectionTitleTemplet>();
		for (SectionTitleTemplet f : getAll()) {
			if(equals(f.getContent(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		List<SectionTitleTemplet> all = new ArrayList<SectionTitleTemplet>();
		for (SectionTitleTemplet f : getAll()) {
			if(equals(f.getUrl(), value)) {
				all.add(f);
			}
		}
		return all;
	}

		int[] all = new int[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SectionTitleTemplet f = get(keys.get(i));
			all[i] = f.getId();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SectionTitleTemplet f = get(keys.get(i));
			all[i] = f.getContent();
		}
		return all;
	}

		String[] all = new String[map.size()];
		for (int i = 0; i < keys.size(); i++) {
			SectionTitleTemplet f = get(keys.get(i));
			all[i] = f.getUrl();
		}
		return all;
	}

		List<Integer> all = new ArrayList<Integer>();
		for (SectionTitleTemplet f : getAll()) {
			all.add(f.getId());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (SectionTitleTemplet f : getAll()) {
			all.add(f.getContent());
		}
		return all;
	}

		List<String> all = new ArrayList<String>();
		for (SectionTitleTemplet f : getAll()) {
			all.add(f.getUrl());
		}
		return all;
	}
