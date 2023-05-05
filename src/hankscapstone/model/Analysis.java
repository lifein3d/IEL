package hankscapstone.model;

import static hankscapstone.model.LinkedList.snowballSort;
import static java.lang.Math.ceil;
import java.text.NumberFormat;
import java.util.List;

/**
 * Generates all analysis of the authorized user
 *
 * @author Bryant Hanks
 * @version 1.0 2023-04-26
 */
public class Analysis {

    /**
     * Generate expense analysis report
     *
     * @param expenditures list of all expenses and loans
     * @param authorizedUser logged in user info
     * @return String - Report to display in pop up
     */
    public static String expenseAnalysis(List<Expense> expenditures,
	  Users authorizedUser) {
	double totalExpenses = 0.0;
	double monthlyIncome = authorizedUser.getIncome();
	double carLoans = 0.0;
	int rentIndex = -1;
	Double expenseEvaluationRate = 0.0;
	Double carEvaluationRate = 0.0;
	Double rentEvaluationRate = 0.0;
	String expenseEvaluation = null;
	String carEvaluation = null;
	String rentEvaluation = null;

	NumberFormat nf = NumberFormat.getInstance();
	nf.setMaximumFractionDigits(2);

	//loop to determine total expense amount, total of car loans, 
	//and total for rent/mortgage
	for (int i = 0; i < expenditures.size(); i++) {
	    totalExpenses += expenditures.get(i).getMonthlyAmount();
	    if (expenditures.get(i).getName().equals("Car")) {
		carLoans += expenditures.get(i).getMonthlyAmount();
	    }
	    if (rentIndex < 0 && expenditures.get(i).getName().equals("Home")) {
		if (rentIndex > -1) {
		    if (expenditures.get(i).getMonthlyAmount()
			  > expenditures.get(rentIndex).getMonthlyAmount()) {
			rentIndex = i;
		    }
		} else {
		    rentIndex = i;
		}
	    }
	    if (expenditures.get(i).getName().equals("Rent")) {
		rentIndex = i;
	    }
	}

	//calculate then determine the response to give based on expense result 
	expenseEvaluationRate = monthlyIncome - totalExpenses;

	if (expenseEvaluationRate > 0) {
	    expenseEvaluation = "Your expenses ($" + nf.format(totalExpenses)
		  + ") are less than your income ($"
		  + nf.format(monthlyIncome) + ").\n"
		  + "Cash flow: $" + nf.format(expenseEvaluationRate) 
		  + "\nGood Job!\n\n";
	} else if (expenseEvaluationRate == 0) {
	    expenseEvaluation = "Your expenses ($" + nf.format(totalExpenses)
		  + ") are the same amount as your income ($"
		  + nf.format(monthlyIncome) + ").\n"
		  + "Cash flow: $" + nf.format(expenseEvaluationRate)
		  + "\nYou need to take immediate measures to either increase "
		  + "your income or decrease your expenses\n\n";
	} else {
	    expenseEvaluation = "Your expenses ($" + nf.format(totalExpenses)
		  + ") are more than your income ($" + nf.format(monthlyIncome)
		  + ").\n"
		  + "Cash flow: $" + nf.format(expenseEvaluationRate)
		  + "\nYou need to take immediate measures to either increase "
		  + "your income or decrease your expenses\n\n";
	}

	//calcualte what % their car payment(s) to their income
	if (carLoans > 0.0) {
	    carEvaluationRate = carLoans / monthlyIncome;
	    if (carEvaluationRate <= 0.1) {
		carEvaluation = "Your car payment(s) ($" + nf.format(carLoans)
		      + ") is "
		      + nf.format(carEvaluationRate * 100)
		      + "% of your income ($" + nf.format(monthlyIncome) + ")."
		      + "\nThis is under the 10% suggested limit of your "
		      + "monthly take home pay. Good Job!\n";
	    } else {
		carEvaluation = "Your car payment(s) ($" + nf.format(carLoans)
		      + ") is "
		      + nf.format(carEvaluationRate * 100)
		      + "% of your income ($" + nf.format(monthlyIncome) + ")."
		      + "\nThis EXCEEDS the 10% suggested limit of your monthly "
		      + "take home pay\n";
	    }
	} else {
	    carEvaluation = "You do not have a car payment.\n\n";
	}

	//calcualte what % their rent/mortgage payment to their income
	if (rentIndex >= 0) {
	    rentEvaluationRate = (expenditures.get(rentIndex).getMonthlyAmount()
		  / monthlyIncome);

	    if (rentEvaluationRate <= 0.25) {
		rentEvaluation = "Your rent/mortgage payment ($"
		      + nf.format(expenditures.get(rentIndex).getMonthlyAmount())
		      + ") is "
		      + nf.format(rentEvaluationRate * 100)
		      + "% of your income ($" + monthlyIncome + ")."
		      + "\nThis is under the 25% suggested limit of your "
		      + "monthly take home pay. \nGood Job!\n\n";
	    } else {
		rentEvaluation = "Your rent/mortgage payment ($"
		      + nf.format(expenditures.get(rentIndex).getMonthlyAmount())
		      + ") is "
		      + nf.format(rentEvaluationRate * 100)
		      + "% of your income ($" + monthlyIncome + ")."
		      + "\nThis EXCEEDS the 25% suggested limit of your "
		      + "monthly take home pay\n\n";
	    }
	} else {
	    rentEvaluation = "You do not have a rent/mortgage payment.\n\n";
	}

	return (expenseEvaluation + rentEvaluation + carEvaluation);
    }

    /**
     * Generates snowball analysis report
     *
     * @param list linked list of all loans in order to be paid off first
     * @param additionalPayment amount to apply to principal of first loan
     * @return String - Report to display in pop up
     */
    public static String snowballAnalysis(LinkedList list, double additionalPayment) {
	Double n = 0.0; //amount payment periods
	Double t = 0.0; //total loan cost
	Double b = 0.0; //principal
	Double r = 0.0; //interest rate
	Double m = 0.0; //payment amount
	int q = 12; //amount of annual payment periods
	int totalMonths = 0; //total months required to pay all loans
	int monthDiff = 0; //difference between regular and snowball in months
	Double totalYears = 0.0; //total years required to pay all loans
	Double yearDiff = 0.0; //difference between regular and snowball in years
	Double highestN = 0.0; //largest month count to pay off loan (regular)
	Double highestNxYear = 0.0; //in years instead of months
	LinkedList.Node currNode = list.head;
	LinkedList.Node prevNode = null;
	String analysis = null; //string to return for pop up

	/*
	https://www.hughcalc.org/formula.php
	
	formula to find how many months to pay off loan
	n = - (LN(1-(B/m)*(r/q)))/LN(1+(r/q))
	
	formula to find total cost of loan
	t = m * n (monthly payment * total number of payment periods)
	 */
	//go through linked list and determine remaining months for all loans
	while (currNode != null) {
	    b = currNode.expense.getRemainingBalance();
	    m = currNode.expense.getMonthlyAmount();
	    r = currNode.expense.getInterest() / 100;

	    n = ceil(-(Math.log(1 - (b / m) * (r / q))) / Math.log(1 + (r / q)));

	    if (n.isNaN()) {
		return ("The '" + currNode.expense.getName() + "' loan has "
		      + "invalid values. Please update.");
	    }
	    //take the longest loan and use it for analysis
	    if (n > highestN) {
		highestN = n;
	    }

	    //t = m * n;
	    currNode = currNode.next;
	}

	//go through linked list and using snowball apply appropriate payments
	//make minimum payment to all loans every month**
	//for list head (smallest loan) apply minimum payment + additional payments
	//remove any loans that have been paid off
	currNode = list.head;
	while (list.head != null) {
	    while (currNode != null) {
		//apply minimum payment + additional payment
		if (currNode == list.head) {
		    currNode.expense.remainingBalance
			  -= (snowballPaymentCalculation(currNode.expense)
			  + additionalPayment);
		} else {//apply minimum payment
		    currNode.expense.remainingBalance
			  -= snowballPaymentCalculation(currNode.expense);
		}
		currNode = currNode.next;
	    }
	    //update how many months have passed
	    totalMonths += 1;
	    currNode = list.head;
	    //remove any loans that have been paid off
	    while (currNode != null) {
		//still has balance, next loan
		if (currNode.expense.getRemainingBalance() > 0.0) {
		    prevNode = currNode;
		    currNode = currNode.next;
		    //add minimum payment of paid off loan to additional payment
		    //reset head of list
		} else if (currNode == list.head) {
		    additionalPayment += currNode.expense.getMonthlyAmount();
		    list.head = currNode.next;
		    currNode = currNode.next;
		    //if loan was paid off prior to being smallest, add to additional payment
		} else {
		    additionalPayment += currNode.expense.getMonthlyAmount();
		    prevNode.next = currNode.next;
		    currNode = currNode.next;
		}
	    }
	    currNode = list.head;
	    //resort list to ensure proper snowball tactic
	    snowballSort(list);
	}

	highestNxYear = (Math.round((highestN / 12.0) * 100.0) / 100.0);
	totalYears = (Math.round((totalMonths / 12.0) * 100.0) / 100.0);
	monthDiff = (int) Math.round(highestN) - totalMonths;
	yearDiff = highestNxYear - totalYears;

	analysis = "Debt free without using snowball:\n"
	      + "Total Months: " + (int) Math.round(highestN)
	      + "\nTotal Years: " + highestNxYear + "\n\n"
	      + "Debt free using snowball:\n"
	      + "Total Months: " + totalMonths + "\n"
	      + "Total Years: " + totalYears + "\n\n"
	      + "Difference:\n"
	      + "Debt free " + monthDiff + " months earlier\n"
	      + "Debt free " + yearDiff + " years earlier";

	return analysis;
    }

    /**
     * Calculate how much interest is taken out of the monthly payment
     *
     * @param expense expense information to use in calculation
     * @return Double - Amount of money to apply to principal of loan
     */
    public static Double snowballPaymentCalculation(Expense expense) {
	double monthlyInterest = expense.getInterest() / 12 / 100;
	double interestPayment = expense.getRemainingBalance() * monthlyInterest;
	double calculatedAmount = expense.getMonthlyAmount() - interestPayment;

	return calculatedAmount;
    }
}
