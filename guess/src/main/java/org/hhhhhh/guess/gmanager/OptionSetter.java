package org.hhhhhh.guess.gmanager;

import org.hhhhhh.guess.hibernate.dao.DbUtil;
import org.hhhhhh.guess.hibernate.dto.QuestionOptionDto;

import cn.javaplus.util.Util;

public class OptionSetter {

	public void setOptions(QuestionOption option, String questionId) {
		set("A", option, questionId);
		set("B", option, questionId);
		set("C", option, questionId);
		set("D", option, questionId);
		set("E", option, questionId);
		set("F", option, questionId);
	}

	private void set(String head, QuestionOption option, String questionId) {
		String dsc = getDsc(option, head);
		if(dsc == null || dsc.isEmpty()) {
			return;
		}
		
		QuestionOptionDto d = new QuestionOptionDto();
		d.setHead(head);
		d.setCount(0);
		d.setCreateTime(Util.Time.getCurrentFormatTime());
		d.setDsc(dsc);
		d.setQuestionId(questionId);
		d.setQuestionIdAndHead(questionId + ":" + head);
		DbUtil.save(d);
	}

	private String getDsc(QuestionOption option, String head) {
		if("A".equals(head)) {
			return option.getOptionA();
		}
		if("B".equals(head)) {
			return option.getOptionB();
		}
		if("C".equals(head)) {
			return option.getOptionC();
		}
		if("D".equals(head)) {
			return option.getOptionD();
		}
		if("E".equals(head)) {
			return option.getOptionE();
		}
		if("F".equals(head)) {
			return option.getOptionF();
		}
		throw new IllegalArgumentException("");
	}

}
