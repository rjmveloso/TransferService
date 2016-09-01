package com.ingenico.epay.transfer.service;

import java.math.BigDecimal;

public interface TransferService {

	public void transfer(String from, String to, BigDecimal amount) throws TransferException;

}
