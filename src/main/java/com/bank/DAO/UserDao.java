package com.bank.DAO;

import java.util.Collection;

import com.bank.model.Account;
import com.bank.model.CurrentUser;
import com.bank.model.Transactions;
import com.bank.model.User;

public interface UserDao {

	User getUser(CurrentUser user);
	User userExist(String username);
	Account getAccountInfo(String username);
	void addAccount(User user, Account account);
	void deleteAccount(String username);
	void changePassword(String username);
	Collection<Account> getAll(); 
	Account getCurrentAccount();
	void tx(Account account, Transactions tx);
	Collection<Transactions> getAllTx();
}
