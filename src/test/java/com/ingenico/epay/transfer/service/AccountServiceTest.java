package com.ingenico.epay.transfer.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ingenico.epay.transfer.Application;
import com.ingenico.epay.transfer.domain.Account;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class AccountServiceTest {

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Autowired
	private AccountService accountService;

	@Test
	public void createAccount() throws AccountException {
		exception.expect(AccountException.class);
		accountService.createAccount(null, null);

		Account account = accountService.createAccount("111", BigDecimal.TEN);
		assertThat(account).isNotNull();
		assertThat(account.getBalance()).isEqualTo(BigDecimal.TEN);

		exception.expect(AccountException.class);
		accountService.createAccount("111", BigDecimal.TEN);

		accountService.createAccount("666", BigDecimal.TEN);
	}

	@Test
	public void getAccountTest() throws AccountException {
		exception.expect(AccountException.class);
		accountService.getAccount(null);

		Account account = accountService.getAccount("666");
		assertThat(account).isNotNull();
		assertThat(account.getName()).isEqualTo("666");
		assertThat(account.getBalance()).isEqualTo(BigDecimal.TEN);
	}
}
