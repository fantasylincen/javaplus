package db.dao.impl;
				ss.setNext(udo.getNick());
				ss.setNext(udo.getCoupon());
				ss.setNext(udo.getGold());
				ss.setNext(udo.getInvitationCode());
				for(int i = 0; i < 40; i++) {
					ss.setNext(udo.getV(i));
				}

				ss.setNext(udo.getNick());
				ss.setNext(udo.getCoupon());
				ss.setNext(udo.getGold());
				ss.setNext(udo.getInvitationCode());
				for(int i = 0; i < 40; i++) {
					ss.setNext(udo.getV(i));
				}


			ss.setNext(udo.getCoupon());
			ss.setNext(udo.getGold());
			ss.setNext(udo.getInvitationCode());
			for(int i = 0; i < 40; i++) {
				ss.setNext(udo.getV(i));
			}

			ss.setNext(udo.getUname());

			ss.setNext(udo.getCoupon());
			ss.setNext(udo.getGold());
			ss.setNext(udo.getInvitationCode());
			for(int i = 0; i < 40; i++) {
				ss.setNext(udo.getV(i));
			}

			ss.setNext(udo.getUname());



				ss.setNext(udo.getNick());
				ss.setNext(udo.getCoupon());
				ss.setNext(udo.getGold());
				ss.setNext(udo.getInvitationCode());
				for(int i = 0; i < 40; i++) {
					ss.setNext(udo.getV(i));
				}

				ss.setNext(udo.getNick());
				ss.setNext(udo.getCoupon());
				ss.setNext(udo.getGold());
				ss.setNext(udo.getInvitationCode());
				for(int i = 0; i < 40; i++) {
					ss.setNext(udo.getV(i));
				}


		udo.setNick(rs.getString("nick"));		
		udo.setCoupon(rs.getInt("coupon"));		
		udo.setGold(rs.getInt("gold"));		
		udo.setInvitationCode(rs.getString("invitation_code"));		

		for(int i = 0; i < 40; i++) {
			udo.setV( i, rs.getInt("v" + (i + 1)));
		}


	
	
	
	
