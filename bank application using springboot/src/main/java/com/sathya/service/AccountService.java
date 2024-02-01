package com.sathya.service;

import com.sathya.model.Account;

public interface AccountService {
	public Account saveAccount(Account account);
	public Account getAccount(int accountno);
	public Account depositeAccount(int accountno,String name,String password,double amount);
	public Account withdrawAccount(int accountno,String name,String password,double amount);
	public Account transferAccount(int accountno,int transferaccountno,String name,String password,double amount);
	public String closeAccount(int accountno,String name,String password);

}
