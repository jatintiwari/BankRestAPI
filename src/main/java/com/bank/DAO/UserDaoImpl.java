package com.bank.DAO;


import java.util.Collection;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bank.model.Account;
import com.bank.model.CurrentUser;
import com.bank.model.Transactions;
import com.bank.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	SessionFactory sessionFactory;
	
	public User getUser(CurrentUser user) {
		 return (User) sessionFactory.getCurrentSession().get(User.class,user.getUsername());
	}
	
	public User userExist(String username) {
		Query query=sessionFactory.getCurrentSession().createQuery("from User where username =:user");
		query.setString("user", username);
		try {
			return (User) query.list().get(0);
		} catch (Exception e) {
			return null;
		}
	}

	public void addAccount(User user, Account account) {
		System.out.println("adding account for username: "+account.getName());
		sessionFactory.getCurrentSession().save(user);
		account.setUser(user);
		account.setStatus("Active");
		sessionFactory.getCurrentSession().save(account);
	}

	public void deleteAccount(String username) {
		Query query=sessionFactory.getCurrentSession().createQuery("from Account where user =:user");
		query.setString("user", username);
		Account account= (Account)query.list().get(0);
		account.setStatus("D-Active");
		sessionFactory.getCurrentSession().save(account);
	}

	

	public void changePassword(String user) {
		Query query=sessionFactory.getCurrentSession().createQuery("from User where username =:user");
		query.setString("user", user);
		User u= (User)query.list().get(0);
		u.setPassword("");
		sessionFactory.getCurrentSession().save(user);
	}

	public Account getAccountInfo(String username) {
		Query query=sessionFactory.openSession().createQuery("from Account where user =:user");
		query.setString("user", username);
		try {
			return(Account) query.list().get(0);
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public Collection<Account> getAll() {
		return sessionFactory.getCurrentSession().createQuery("from Account").list();
	}

	public Account getCurrentAccount() {
		Query query=sessionFactory.getCurrentSession().createQuery("from Account where user =:user");
		query.setString("user",CurrentUser.getCurrentUser());
		return (Account)query.list().get(0);
		
	}
	public void tx(Account account, Transactions tx) {
		sessionFactory.getCurrentSession().saveOrUpdate(tx);
		sessionFactory.getCurrentSession().saveOrUpdate(account);
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Transactions> getAllTx(){
		Query query=sessionFactory.openSession().createQuery("from Transactions where user =:user");
		query.setString("user", CurrentUser.getCurrentUser());
		return query.list();
	}


}
