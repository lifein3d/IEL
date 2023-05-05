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
public class LoansCombo {

    private String[] loans = {"Car",
	"Credit Card",
	"Home Equity",
	"Home",
	"Personal",
	"School",
	"Other",
	"Medical"};

    public String[] getLoans() {
	Arrays.sort(loans);
	return loans;
    }

    public void setLoans(String[] loans) {
	this.loans = loans;
    }

}
