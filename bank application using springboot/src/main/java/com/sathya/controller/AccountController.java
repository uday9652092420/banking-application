package com.sathya.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sathya.model.Account;
import com.sathya.service.AccountServiceImp;

@Controller
public class AccountController {
	@Autowired
	private AccountServiceImp service;
	
	@RequestMapping("/")
	public String gotohomepage() {
		return "homepage";
		
	}
	@RequestMapping("/newaccount")
	public String gotonewaccountpage() {
		
		System.out.println("thsis is form URL");
		return "newaccountpage";
		
	}
	@RequestMapping("/savedetiles")
	public String savenewaccountdetiles(Account account) {
		service.saveAccount(account);		
		return "successpage1";
		
	}
	@RequestMapping("/balance")
	public String gotobalanceform() {
		return "balanceform";
		
	}
	@RequestMapping("/viewbalance")
	public String viewbalance(int accountno,ModelMap model) {
		model.put("account", service.getAccount(accountno));
		return "viewbalancepage";
		
	}
	@RequestMapping("/deposite")
	public String gotodepositeform() {
		
		return "depositepage";
		
	}
	@RequestMapping("/savedeposite")
	public String depositeamount(@RequestParam int accountno,@RequestParam String name,@RequestParam String password,@RequestParam double amount,ModelMap model) {
		model.put("account",service.depositeAccount(accountno, name, password, amount));
		model.put("amount", amount);
		
		return "depositeresultspage";
		
	}
	@RequestMapping("/withdraw")
	public String gotowithdrawform() {
		return "withdrawpage";
	
	}
	@RequestMapping("/getwithdraw")
	public String withdrawamount(@RequestParam int accountno,@RequestParam String name,@RequestParam String password,@RequestParam double amount,ModelMap model) {
		Account account=service.withdrawAccount(accountno, name, password, amount);
		if(account!=null) {
			model.put("account", account);
			model.put("amount", amount);
			return "withdrawresults";
		}
		else
		{
			return "nofunds";
		}		
	}
	@RequestMapping("/transfer")
	public String gototransferpage() {
		
		return "transferpage";
		
	}
	@RequestMapping("/gettransfer")
	public String transferamount(@RequestParam int accountno,@RequestParam int transferaccountno,@RequestParam String name,@RequestParam String password,@RequestParam double amount,ModelMap model) {
		Account account=service.transferAccount(accountno, transferaccountno, name, password, amount);
		if(account!=null)
		{
			model.put("account",service.getAccount(accountno));
			model.put("amount", amount);
			model.put("acc1", service.getAccount(transferaccountno));
			return "transferresult";
		}
		else
		{
			return "invalidform";
		}
	
	}
	@RequestMapping("/close")
	public String gotocloseform() {
		return "closepage";
		
	}
	@RequestMapping("/closeA/C")
	public String closeaccount(@RequestParam int accountno,@RequestParam String name,@RequestParam String password,ModelMap model) {
		String account= service.closeAccount(accountno, name, password);
		model.put("account", account);
		return "closeaccountresults";
		
	}
	@RequestMapping("/about")
	public String gotoaboutpage()
	{
		return "about";
		
	}
	

}
