package cn.mxz.loganalysis;

import java.sql.Date;

public interface LogData {

	Date getTime();

	int getServerId();

	String getHead();

	String getLog();
}