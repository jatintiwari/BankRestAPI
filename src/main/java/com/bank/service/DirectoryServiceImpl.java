package com.bank.service;


import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bank.DAO.UserDao;
import com.bank.model.Account;
import com.bank.model.CurrentUser;
import com.bank.model.Transactions;
import com.bank.model.User;
import com.bank.util.Bank;
import com.google.gson.Gson;

@Service
public class DirectoryServiceImpl implements DirectoryService {

	@Autowired
	private UserDao userDao;

	@Transactional
	public User getUser(CurrentUser user) {
		return userDao.getUser(user);
		
	}

	@Transactional
	public void addAccount(Account account, User user) {
		userDao.addAccount(user, account);
	}
	
	@Transactional
	public void deleteAccount(String username) {
		userDao.deleteAccount(username);
	}
	@Transactional
	public boolean usernameExist(String username){
		try {
			if(!userDao.userExist(username).equals(null)){
				return true;
			}else return false;
		} catch (NullPointerException e) {
			return false;
		}
	}
	@Transactional
	public void changepassword(String username) {
		userDao.changePassword(username);
	}
	@Transactional
	public Account searchAccountInfo(String username) {
		return userDao.getAccountInfo(username);
	}
	@Transactional
	public Collection<Account> getAll() {
		return userDao.getAll();
	}
	@Transactional
	public Account getCurrentAccount() {
		return userDao.getCurrentAccount();
	}
	@Transactional
	public void tx(Account account, Transactions tx) {
		userDao.tx(account, tx);
	}
	@Transactional
	public Collection<Transactions> getAllTx() {
		return userDao.getAllTx();
	}

	

}
