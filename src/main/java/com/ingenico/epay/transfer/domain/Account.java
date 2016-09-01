package com.ingenico.epay.transfer.domain;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class Account {

	private String name;
	private BigDecimal balance;

	private List<Movement> transfers = new LinkedList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public List<Movement> getTransfers() {
		return transfers;
	}

	public void setTransfers(List<Movement> transfers) {
		this.transfers = transfers;
	}

}
