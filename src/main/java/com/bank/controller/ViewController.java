package com.bank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bank.model.CurrentUser;
import com.bank.util.Bank;

@Controller
public class ViewController {

	@RequestMapping(value="welcome", method=RequestMethod.GET)
	public String justToRedirect(){
		try {
			if(CurrentUser.getUserType().equalsIgnoreCase(Bank.ADMIN)){
				System.out.println("User type is Admin");
				return "welcomeAdmin";
			}else if(CurrentUser.getUserType().equalsIgnoreCase(Bank.CLIENT)){
				System.out.println("User type is Client");
				return "welcomeUser";
			}return "index";
		} catch (NullPointerException e) {
			System.out.println("Exception: User undefined");
			return "index";
		}
	}

	@RequestMapping(value="logout", method=RequestMethod.GET)
	public String logout(){
		System.out.println("Proceed log out");
		CurrentUser.setUserType(null);
		return "index";
	}

}
