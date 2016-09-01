package com.ingenico.epay.transfer.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ingenico.epay.transfer.domain.Account;
import com.ingenico.epay.transfer.domain.Transfer;
import com.ingenico.epay.transfer.repository.TransferRepository;

@Service
public class TransferServiceImpl implements TransferService {

	@Autowired
	private AccountService accountService;

	@Autowired
	private TransferRepository transferRepository;

	public void transfer(String source, String target, BigDecimal amount) throws TransferException {
		if (amount == null || BigDecimal.ZERO.compareTo(amount) >= 0) {
			throw new TransferException("Transaction from " + source + " to " + target + " must be greater than 0");
		}

		try {
			Account sourceAccount = accountService.getAccount(source);
			Account targetAccount = accountService.getAccount(target);

			registerTransfer(source, target, amount);

			accountService.authorize(sourceAccount, amount);

			try {
				accountService.credit(targetAccount, amount);
				// registerTransfer(source, target, amount);
			} catch (AccountException e) {
				// refund original account
				accountService.credit(sourceAccount, amount.negate());
				throw e;
			}
		} catch (Exception e) {
			new TransferException(e.getMessage());
		}
	}

	private void registerTransfer(String fromAccount, String toAccount, BigDecimal amount) {
		Transfer transfer = new Transfer();
		transfer.setOrigin(fromAccount);
		transfer.setTarget(toAccount);
		transfer.setAmount(amount);
		transferRepository.add(transfer);
	}
}
