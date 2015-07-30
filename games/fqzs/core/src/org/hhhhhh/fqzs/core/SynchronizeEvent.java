package org.hhhhhh.fqzs.core;

public class SynchronizeEvent {

	private TableData tableData;

	public SynchronizeEvent(TableData tableData) {
		this.tableData = tableData;
	}

	public TableData getTableData() {
		return tableData;
	}
}
