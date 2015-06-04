package com.linekong.platform.protocol.erating;

import java.util.Arrays;

public class ResponseImpl implements Response {

	private int		totalLength;
	private short	version;
	private short	remainPackages;
	private long	commandId;
	private long	gameId;
	private long	sequenceId;
	private long	gatewayId;
	private byte[]	body;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(" [totalLength=");
		builder.append(totalLength);
		builder.append(", version=");
		builder.append(version);
		builder.append(", remainPackages=");
		builder.append(remainPackages);
		builder.append(", commandId=");
		builder.append(commandId);
		builder.append(", gameId=");
		builder.append(gameId);
		builder.append(", sequenceId=");
		builder.append(sequenceId);
		builder.append(", gatewayId=");
		builder.append(gatewayId);
		builder.append(", body=");
		builder.append(Arrays.toString(body));
		builder.append("]");
		return builder.toString();
	}

	public void setTotalLength(int totalLength) {
		this.totalLength = totalLength;
	}

	public void setVersion(short version) {
		this.version = version;
	}

	public void setRemainPackages(short remainPackages) {
		this.remainPackages = remainPackages;
	}

	public void setCommandId(long commandId) {
		this.commandId = commandId;
	}

	public void setGameId(long gameId) {
		this.gameId = gameId;
	}

	public void setSequenceId(long sequenceId) {
		this.sequenceId = sequenceId;
	}

	public void setGatewayId(long gatewayId) {
		this.gatewayId = gatewayId;
	}

	public void setBody(byte[] body) {
		this.body = body;
	}

	public int getTotalLength() {
		return totalLength;
	}

	public short getVersion() {
		return version;
	}

	public short getRemainPackages() {
		return remainPackages;
	}

	public long getCommandId() {
		return commandId;
	}

	public long getGameId() {
		return gameId;
	}

	public long getSequenceId() {
		return sequenceId;
	}

	public long getGatewayId() {
		return gatewayId;
	}

	@Override
	public byte[] getBody() {
		return body;
	}

}
