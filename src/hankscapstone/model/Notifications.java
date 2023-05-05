/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hankscapstone.model;

/**
 * List of all notifications to be given to users
 *
 * @author Bryant Hanks
 * @version 1.0 - 2023-04-26
 */
public class Notifications {

    private String userCreated = "User has been created.";
    private String expenseMenu
	  = "The expenses analysis takes your monthly take home income "
	  + "and subtracts all of your expenses and loans "
	  + "to determine if you have money left over.\n"
	  + "If there is a surplus of money it would be wise "
	  + "to apply that towards an emergency fund or as "
	  + "additional payment to existing loans "
	  + "(See Snowball Analysis).\n\n"
	  + "The suggested percentages of your monthly take home "
	  + "income are considered conservative(too low) "
	  + "but in reality they're more reasonable.\n "
	  + "The goal is to not over spend on these two items since"
	  + " they have large impacts on your financial freedom.\n\n"
	  + "It is suggested that only 25% of your monthly take "
	  + "home income should be towards car payments.\n"
	  + "These are depreciating assets and having them "
	  + "too high of a payment can make you \"car poor\".\n\n"
	  + "It is suggested that only 35% of your monthly take "
	  + "home income should be towards your rent/mortgage.\n"
	  + "Spending more than this can lead to being "
	  + "\"house poor\".";
    private String snowballMenu
	  = "The snowball tactic is a loan payoff method where you pay your "
	  + "loans from smallest remaining balance to largest "
	  + "remaining balance, regardless of interest rate.\n"
	  + "Knock out the smallest loan first. "
	  + "Then, take what you were paying on that loan and "
	  + "add it to the payment of your next smallest loan.\n"
	  + "If you have a lot of indvidual loans this can have "
	  + "a drastic impact on how fast you become debt free.\n\n"
	  + "Step 1: Order your loans from smallest to "
	  + "largest remaining balance.\n"
	  + "Step 2: Make minimum payments on all of your loans, "
	  + "excluding the smallest remaining balance loan.\n"
	  + "Step 3: Pay as much as possible "
	  + "(including the previous amount of the most recently "
	  + "paid off loan) to your smallest remaining balance loan.\n"
	  + "Step 4: Repeat previous steps every month\n\n"
	  + "This tool simulates the snowball tactic on your loans "
	  + "and gives a detailed breakdown of how this tactic "
	  + "can make you become debt free faster.";
    private String aboutMenu
	  = "My name is Bryant, I am enthusiast for the FIRE "
	  + "(Financial Independence, Retire Early) movement.\n\n"
	  + "I've always been concious of my financials since I grew "
	  + "up not having as much as my peers/neighbors.\n"
	  + "This translated into not overspending and saving for "
	  + "big financial goals, even when my income was still low."
	  + "\n\nThis has had a widly positive impact on my life "
	  + "and has allowed me to be debt free (excluding mortgage) "
	  + "for most of my adult life.\n"
	  + "Seeing how we're a capitalist society it's very easy to "
	  + "spend and look financially independent.\n"
	  + "Unfortunately it's common knowledge that alot of "
	  + "individuals have crippling debt and will declare "
	  + "bankruptcy.\n\n"
	  + "I wanted to have a positive impact and make a tool "
	  + "that is comprehensive enough to show the fastest way of "
	  + "getting out of debt without being cumbersome to use.\n\n"
	  + "This tool will allow you to input your monthly take home "
	  + "pay, general expenses, and loans.\nThis tool allows you "
	  + "to save this information for later use by creating "
	  + "a profile.\n"
	  + "The bread and butter of this tool is to give an analysis "
	  + "of your expenses to identify if your financials are "
	  + "healthy in the three most important aspects:\n"
	  + "Net postive monthly income\nappropriate ratio of "
	  + "your income to your car and house payments.\n\n"
	  + "The snowball analysis will help demonstrate the power "
	  + "of the snowball tactic with your current loans to help "
	  + "you understand and plan how to pay of your loans fast.";

    public Notifications() {
    }

    public String getExpenseMenu() {
	return expenseMenu;
    }

    public String getUserCreated() {
	return userCreated;
    }

    public String getSnowballMenu() {
	return snowballMenu;
    }

    public String getAboutMenu() {
	return aboutMenu;
    }

}
