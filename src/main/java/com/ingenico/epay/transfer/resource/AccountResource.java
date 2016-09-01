package com.ingenico.epay.transfer.resource;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ingenico.epay.transfer.domain.Account;
import com.ingenico.epay.transfer.resource.dto.AccountDTO;
import com.ingenico.epay.transfer.resource.dto.TransferDTO;
import com.ingenico.epay.transfer.service.AccountService;
import com.ingenico.epay.transfer.service.TransferService;

@RestController
@RequestMapping("/api/account")
public class AccountResource {

	@Autowired
	private AccountService accountService;

	@Autowired
	private TransferService transferService;

	@RequestMapping(value = "/{name}", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<?> get(@PathVariable String name) {
		try {
			Account account = accountService.getAccount(name);
			AccountDTO dto = new AccountDTO();
			dto.setName(account.getName());
			dto.setBalance(account.getBalance());
			return new ResponseEntity<AccountDTO>(dto, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<String> create(@RequestBody AccountDTO account) {
		try {
			accountService.createAccount(account.getName(), account.getBalance());
			return new ResponseEntity<String>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/transfer", method = RequestMethod.PUT, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<String> transfer(@RequestBody TransferDTO transfer) {
		try {
			transferService.transfer(transfer.getFrom(), transfer.getTo(), transfer.getAmount());
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
