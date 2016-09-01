package com.ingenico.epay.transfer.domain;

import java.math.BigDecimal;

public class Movement {

	private String account;
	private BigDecimal amount;
	private MovementType type;

	public enum MovementType {
		INBOUND, OUTBOUND;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public MovementType getType() {
		return type;
	}

	public void setType(MovementType type) {
		this.type = type;
	}

}
