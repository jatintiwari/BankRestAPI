package com.bank.controller;


import java.util.Collection;
import java.util.Date;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.bank.model.Account;
import com.bank.model.CurrentUser;
import com.bank.model.Transactions;
import com.bank.model.User;
import com.bank.service.DirectoryService;
import com.bank.util.Bank;

@Controller
@RequestMapping(value="account")
public class AccountController {

	@Autowired
	DirectoryService directoryService;
	
	private JSONArray jsonArray;
	private JSONObject jsonObject;
	private ObjectMapper mapper;

	@RequestMapping(value="validate", method=RequestMethod.POST)
	public @ResponseBody String validate(String validateLogin){
		try{
			System.out.println("Validation begin");
			mapper= new ObjectMapper();
			mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			CurrentUser currentUser = mapper.readValue(validateLogin, CurrentUser.class);
			if(currentUser.getUsername().equalsIgnoreCase(Bank.ADMINUSERNAME) 
					&& currentUser.getPassword().equalsIgnoreCase(Bank.ADMINPASSWORD)){
				CurrentUser.setUserType(Bank.ADMIN);
				return "{\"status\":\"AccountExits\",\"targetUrl\":\"welcome\"}";
			}
			User user= directoryService.getUser(currentUser);
			if(currentUser.getUsername().equals(user.getUsername()) 
					&& currentUser.getPassword().equals(user.getPassword()))
			{ 
				CurrentUser.setUserType(Bank.CLIENT);
				CurrentUser.setCurrentUser(currentUser.getUsername());
				return "{\"status\":\"AccountExits\",\"targetUrl\":\"welcome\"}";
			}else{
				return "{ \"status\": \"username/password not valid\" }";
			}
		}catch(Exception e){
			e.printStackTrace();
			return "\"status\":\"username/password not valid\"";
		}
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody String addAccount(String incomingData){
		try {
			
			Account account= mapper.readValue(incomingData, Account.class);
			User user= mapper.readValue(incomingData, User.class);
			directoryService.addAccount(account,user);
			return "{\"status\":\"Account Added\"}";
		} catch (Exception e) {
			return "{ \"status\": \"account not added exception\" }";
		}
	}
	
	@RequestMapping(value="deactivate/{username}",method=RequestMethod.PUT)
	@ResponseStatus(value=HttpStatus.OK)
	public @ResponseBody String deleteAccount(@PathVariable("username") String username){
		try {
			System.out.println("Deleting an account"+username);
			if(directoryService.usernameExist(username)){
				directoryService.deleteAccount(username);
				return "{\"status\":\"Account Deleted\"}";	
			}else return "{\"status\":\"Account not found\"}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"status\": \"account deleted exception\" }";
		}
	}
	
	@RequestMapping(value="changePassword",method=RequestMethod.POST)
	@ResponseStatus(value=HttpStatus.OK)
	public @ResponseBody String changePassword(String name){
		try {
			System.out.println("Changing account password "+name);
			if(directoryService.usernameExist(name)){
				directoryService.changepassword(name);
				return "{\"status\":\"Password Changed\"}";	
			}else return "{\"status\":\"Account not found\"}";
		} catch (Exception e) {
			return "{ \"status\": \"Change password exception\" }";
		}
	}
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseStatus(value=HttpStatus.OK)
	public @ResponseBody String searchAccount(@RequestParam String username){
		try {
			System.out.println("Get account info: "+username);
			if(directoryService.usernameExist(username)){
			Account account =directoryService.searchAccountInfo(username);
			jsonObject = new JSONObject();
			jsonArray= new JSONArray();
			jsonObject.put("id", account.getId());
			jsonObject.put("name", account.getName());
			jsonObject.put("accountType", account.getAccountType());
			jsonObject.put("amount", account.getAmount());
			jsonObject.put("username", account.getUser().getUsername());
			jsonObject.put("status", account.getStatus());
			jsonArray.put(jsonObject);
				return jsonObject.toString();	
			}else return "{\"status\":\"Account not found\"}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{ \"status\": \"Get account exception\" }";
		}
	}
	@RequestMapping(value="/getall", method=RequestMethod.GET)
	@ResponseStatus(value=HttpStatus.OK)
	public @ResponseBody String getAllAccounts(){
		try{
			Collection<Account> accounts = directoryService.getAll();
			jsonArray = new JSONArray();
			
			for(Account account: accounts){
				jsonObject = new JSONObject();
				jsonObject.put("id", account.getId());
				jsonObject.put("name", account.getName());
				jsonObject.put("accountType", account.getAccountType());
				jsonObject.put("amount", account.getAmount());
				jsonObject.put("username", account.getUser().getUsername());
				jsonObject.put("status", account.getStatus());
				//put JSON object in JSON array.
				jsonArray.put(jsonObject);
			}
			System.out.println(jsonArray);
			return jsonArray.toString();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return "{\"result\": \"no items\"}";		
	}
	
	@RequestMapping(value="deposit", method=RequestMethod.POST)
	@ResponseStatus(value=HttpStatus.OK)
	public @ResponseBody String tx( @RequestParam("amount") Double amount,
			@RequestParam("txType") String txType){
		System.out.println("Amount to deposit: "+amount);
		Transactions tx=new Transactions();
		Account account =directoryService.getCurrentAccount();
		if(txType.equalsIgnoreCase("deposit")){
			account.setAmount(account.getAmount()+amount);
			tx.setBalance(account.getAmount()+amount);
		}else{
			account.setAmount(account.getAmount()-amount);
			tx.setBalance(account.getAmount());
		}
		tx.setAmount(amount);
		tx.setDate(new Date());
		tx.setType(txType);
		tx.setUser(account.getUser());
		directoryService.tx(account, tx);
		return "{ \"status\": \"deposited\" }";
	}
	
	@RequestMapping(value="getAllTx", method=RequestMethod.GET)
	@ResponseStatus(value=HttpStatus.OK)
	public @ResponseBody String getAllTx(){
		try {
			Collection<Transactions> txlist =directoryService.getAllTx();
			jsonArray= new JSONArray();
			
			for(Transactions tx: txlist){
				jsonObject= new JSONObject();
				jsonObject.put("date", tx.getDate());
				jsonObject.put("type", tx.getType());
				jsonObject.put("amount", tx.getAmount());
				jsonObject.put("balance", tx.getBalance());
				
				jsonArray.put(jsonObject);
			}
			return jsonArray.toString();
			
		} catch (Exception e) {
			return "{\"status\": \"No Transactions\"}";
		}
	}
	
}
