/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hankscapstone.model;

import java.util.Arrays;

/**
 *
 * @author proga
 */
public class ExpensesCombo {
    private String[] expenses = {"Rent",
	    "Internet",
	    "TV",
	    "Entertainment",
	    "Shopping",
	    "Car Insurance",
	    "Cell Phone",
	    "Health & Wellness",
	    "Travel",
	    "Food",
	    "Gas",
	    "Utilities",
	    "Professional Services",
	    "Pet Supplies",
	    "Other"};

    public String[] getExpenses() {
	Arrays.sort(expenses);
	return expenses;
    }

    public void setExpenses(String[] expenses) {
	this.expenses = expenses;
    }

}
