package com.ingenico.epay.transfer.repository;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.springframework.stereotype.Service;

import com.ingenico.epay.transfer.domain.Transfer;

@Service
public class InMemoryTransferRepository implements TransferRepository {

	private final Queue<Transfer> transfers = new ConcurrentLinkedQueue<>();

	@Override
	public void add(Transfer transfer) {
		transfers.add(transfer);
	}

}
