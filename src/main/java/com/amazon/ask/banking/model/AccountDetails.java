package com.amazon.ask.banking.model;

/**
 * @author Karthick Rajendran
 *
 */

public class AccountDetails {

 private Long id; 
 private String acctId; 
 private String acctType; 
 private String balance;
 private String name;
 private String pin;
 
 public Long getId() {
  return id;
 }

 public void setId(Long id) {
  this.id = id;
 }

public String getAcctId() {
	return acctId;
}

public void setAcctId(String acctId) {
	this.acctId = acctId;
}

public String getAcctType() {
	return acctType;
}

public void setAcctType(String acctType) {
	this.acctType = acctType;
}

public String getBalance() {
	return balance;
}

public void setBalance(String balance) {
	this.balance = balance;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getPin() {
	return pin;
}

public void setPin(String pin) {
	this.pin = pin;
}


}
