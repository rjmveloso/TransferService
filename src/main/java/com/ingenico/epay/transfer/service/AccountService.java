package com.ingenico.epay.transfer.service;

import java.math.BigDecimal;

import com.ingenico.epay.transfer.domain.Account;

public interface AccountService {

	public Account getAccount(String name) throws AccountException;

	public Account createAccount(String name, BigDecimal initialBalance) throws AccountException;

	public void authorize(Account account, BigDecimal amount) throws AccountException;

	public void credit(Account account, BigDecimal amount) throws AccountException;
}
