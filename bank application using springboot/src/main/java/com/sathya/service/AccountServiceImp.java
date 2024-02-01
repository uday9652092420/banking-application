package com.sathya.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sathya.model.Account;
import com.sathya.repo.AccountRepo;

@Service
public class AccountServiceImp implements AccountService {
	@Autowired
	private AccountRepo repo;

	@Override
	public Account saveAccount(Account account) {		
		Account r=repo.save(account);
		return r;
	}

	@Override
	public Account getAccount(int accountno) {
		Account abcd = repo.findById(accountno).get();
		return abcd;

	}

	@Override
	public Account depositeAccount(int accountno, String name, String password, double amount) {
	Account a1=repo.findById(accountno).get();
	double totalamount=0.0;
	if(a1.getAccountno()==accountno&&a1.getName().equals(name)&&a1.getPassword().equals(password))
	{
		totalamount=a1.getAmount()+amount;
		
	}
	a1.setAmount(totalamount);
	repo.save(a1);
		return a1;
	}

	@Override
	public Account withdrawAccount(int accountno, String name, String password, double amount) {
		Account account=repo.findById(accountno).get();
		double totalbalance=0;
		double balance=account.getAmount();
		if(account.getAccountno()==accountno&&account.getName().equals(name)&&account.getPassword().equals(password))
		{
			if(balance<amount)
			{
				return null;
			}
			else
			{
				totalbalance=balance-amount;
			}
		}
		else
		{
		return null;
		}
		account.setAmount(totalbalance);
		repo.save(account);
		
		return account;
	}

	@Override
	public Account transferAccount(int accountno, int transferaccountno, String name, String password, double amount) {
		Account account=repo.findById(accountno).get();
		Account transferaccount=repo.findById(transferaccountno).get();
		double transferamount=amount;
		double totalbalance=0;
		double recievertotal=0;
		if(account.getAccountno()== accountno&&account.getName().equals(name)&&account.getPassword().equals(password))
		{
			if(account.getAmount()<transferamount)
			{
				return null;
			}
			else {
			  totalbalance=account.getAmount()-transferamount;
			  account.setAmount(totalbalance);
			  repo.save(account);
			}
		}
		if(transferaccount.getAccountno()==transferaccountno)
		{
			recievertotal=transferaccount.getAmount()+transferamount;	
			transferaccount.setAmount(recievertotal);
			repo.save(transferaccount);
			
		}
		else
		{			
		return null;
		}
		return transferaccount;
	}

	@Override
	public String closeAccount(int accountno, String name, String password) {
		Account account=repo.findById(accountno).get();
		if(account!=null)
		{
			if(account.getAccountno()==accountno&& account.getName().equals(name) && account.getPassword().equals(password))
			{
				repo.delete(account);
				return "account successfully deleted";
			}
			
		}
		return "failded to close account once check credentials";
	}

	
	

}
