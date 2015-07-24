package org.hhhhhh.guess.question;

import java.util.Comparator;

import org.hhhhhh.guess.hibernate.dto.RoundDto;

public class RoundsC implements Comparator< RoundDto> {

	@Override
	public int compare(RoundDto o1, RoundDto o2) {
		return o1.getStartTime().compareTo(o2.getStartTime());
	}

}
