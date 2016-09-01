package com.ingenico.epay.transfer.repository;

import com.ingenico.epay.transfer.domain.Account;

public interface AccountRepository {

	public Account get(String name);

	public Account create(Account account);

}
