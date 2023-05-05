/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hankscapstone.model;

/**
 * List of all errors to be given to users
 *
 * @author Bryant Hanks
 * @version 1.0 - 2023-04-26
 */
public class Errors {

    private final String userPwd = "Error: Please Enter Username and Password";
    private final String userExt = "Error: Username "
	  + "already exists. Please try again";
    private String encrypt = "Error: Password "
	  + "Encryption Error. Try different password";
    private String invalidUserPwd = "Error: Invalid Username or Password";
    private String invalidUserPwdInc = "Error: Invalid Username, Password,"
	  + " or income value";
    private String incomeNumber = "Error: Income is not a positive number";
    private String amountNumber = "Error: Amount is not a positive number";
    private String tableError = "Error: Item from table not selected. "
	  + "Please select an item.";
    private String updateName = "Error: Cannot update items name."
	  + " Please delete instead of update";
    private String loanNumbers = "Error: the following fields need to be numbers:\n"
	  + "Monthly Payment\n"
	  + "Remaining Balance\n"
	  + "Interest Rate";
    private String additionalPmt = "Error: Additional payment is not a positive"
	  + " number";

    public Errors() {
    }

    public String getUserPwd() {
	return userPwd;
    }

    public String getUserExt() {
	return userExt;
    }

    public String getEncrypt() {
	return encrypt;
    }

    public String getInvalidUserPwd() {
	return invalidUserPwd;
    }

    public String getInvalidUserPwdInc() {
	return invalidUserPwdInc;
    }

    public String getIncomeNumber() {
	return incomeNumber;
    }

    public String getAmountNumber() {
	return amountNumber;
    }

    public String getTableError() {
	return tableError;
    }

    public String getUpdateName() {
	return updateName;
    }

    public String getLoanNumbers() {
	return loanNumbers;
    }

    public String getAdditionalPmt() {
	return additionalPmt;
    }

}
