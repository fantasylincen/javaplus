package db.dao.impl;
	@Override
	@Override
	@Override
	@Override
	@Override

				ss.setNext(mso.getMissionId());
				ss.setNext(mso.getDemonStarMax());
				ss.setNext(mso.getDemonStar());
				ss.setNext(mso.getMainBossStarMax());
				ss.setNext(mso.getMainBossStar());
				for(int i = 0; i < 6; i++) {
					ss.setNext(mso.getBranchBossStar(i));
				}
				for(int i = 0; i < 6; i++) {
					ss.setNext(mso.getBranchBossStarMax(i));
				}

			ss.setNext(uname);

			ss.setNext(mso.getDemonStar());
			ss.setNext(mso.getMainBossStarMax());
			ss.setNext(mso.getMainBossStar());
			for(int i = 0; i < 6; i++) {
				ss.setNext(mso.getBranchBossStar(i));
			}

			for(int i = 0; i < 6; i++) {
				ss.setNext(mso.getBranchBossStarMax(i));
			}

			ss.setNext(mso.getMissionId());
			ss.setNext(mso.getUname());

			ss.setNext(mso.getUname());

		mso.setMissionId(rs.getInt("mission_id"));		
		mso.setDemonStarMax(rs.getInt("demon_star_max"));		
		mso.setDemonStar(rs.getInt("demon_star"));		
		mso.setMainBossStarMax(rs.getInt("main_boss_star_max"));		
		mso.setMainBossStar(rs.getInt("main_boss_star"));		

		for(int i = 0; i < 6; i++) {
			mso.setBranchBossStar( i, rs.getInt("branch_boss_star_" + (i + 1)));
		}


		for(int i = 0; i < 6; i++) {
			mso.setBranchBossStarMax( i, rs.getInt("branch_boss_star_max_" + (i + 1)));
		}


			ps.setInt(2, pageSize);

	
	
	
	
	

	
	
	
	
	

	
	
	
	
	

	@Override
	@Override
	@Override
	@Override
	@Override
