package com.bank.service;

import java.util.Collection;

import com.bank.model.Account;
import com.bank.model.CurrentUser;
import com.bank.model.Transactions;
import com.bank.model.User;

public interface DirectoryService {

	User getUser(CurrentUser user);
	boolean usernameExist(String username);
	void addAccount(Account account, User user);
	void deleteAccount(String username);
	void changepassword(String username);
	Account searchAccountInfo(String username);
	Collection<Account> getAll();
	Account getCurrentAccount();
	void tx(Account account, Transactions tx);
	Collection<Transactions> getAllTx();
}
