package db.dao.impl;
	@Override
	@Override

				ss.setNext(peo.getStatus());
				ss.setNext(new java.sql.Date(peo.getPvpUpTime().getTime()));
				for(int i = 0; i < 15; i++) {
					ss.setNext(peo.getV(i));
				}


			ss.setNext(new java.sql.Date(peo.getPvpUpTime().getTime()));
			for(int i = 0; i < 15; i++) {
				ss.setNext(peo.getV(i));
			}

			ss.setNext(peo.getUname());


		peo.setStatus(rs.getString("status"));		
		peo.setPvpUpTime(rs.getDate("pvp_up_time"));		

		for(int i = 0; i < 15; i++) {
			peo.setV( i, rs.getInt("v" + (i + 1)));
		}


			ps.setInt(2, pageSize);

	
	

	
	

	
	

	@Override
	@Override
