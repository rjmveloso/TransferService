package com.ingenico.epay.transfer.service;

import java.math.BigDecimal;

public interface TransferService {

	public void transfer(String source, String target, BigDecimal amount) throws TransferException;

}
