package hankscapstone.model;

/**
 * Creates an Expense Object
 *
 * @author Bryant Hanks
 * @version 1.0 2023-04-26
 */
public class Expense {

    protected int id;

    protected String username;

    protected String name;

    protected double monthlyAmount;

    protected double remainingBalance;

    protected double interest;

    /**
     * default constructor
     */
    public Expense() {
	this.id = 0;
	this.username = null;
	this.name = null;
	this.monthlyAmount = 0.0;
	this.remainingBalance = 0.0;
	this.interest = 0.0;
    }

    /**
     * Constructor
     *
     * @param id - itemID in database
     * @param username - logged in username
     * @param name - name of expense
     * @param monthlyAmount - amount of the expense
     * @param remainingBalance - remaining balance of the loan
     * @param interest - interest of the loan
     */
    public Expense(int id, String username, String name, double monthlyAmount, double remainingBalance,
	  double interest) {
	this.id = id;
	this.username = username;
	this.name = name;
	this.monthlyAmount = monthlyAmount;
	this.remainingBalance = remainingBalance;
	this.interest = interest;
    }

    /**
     * constructor
     *
     * @param username - logged in username
     * @param name - name of expense
     * @param monthlyAmount - amount of the expense
     * @param remainingBalance - remaining balance of the loan
     * @param interest - interest of the loan
     */
    public Expense(String username, String name, double monthlyAmount, double remainingBalance,
	  double interest) {
	this.username = username;
	this.name = name;
	this.monthlyAmount = monthlyAmount;
	this.remainingBalance = remainingBalance;
	this.interest = interest;
    }

    /**
     * constructor
     *
     * @param username - logged in username
     * @param name - name of expense
     * @param monthlyAmount - amount of the expense
     */
    public Expense(String username, String name, double monthlyAmount) {
	this.id = 0;
	this.username = username;
	this.name = name;
	this.monthlyAmount = monthlyAmount;
	this.remainingBalance = 0.0;
	this.interest = 0.0;
    }

    public int getID() {
	return id;
    }

    public void setID(int id) {
	this.id = id;
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public double getMonthlyAmount() {
	return monthlyAmount;
    }

    public void setMonthlyAmount(double monthlyAmount) {
	this.monthlyAmount = monthlyAmount;
    }

    public double getRemainingBalance() {
	return remainingBalance;
    }

    public void setRemainingBalance(double remainingBalance) {
	this.remainingBalance = remainingBalance;
    }

    public double getInterest() {
	return interest;
    }

    public void setInterest(double interest) {
	this.interest = interest;
    }

    /**
     * validates username, name, monthlyamount, and interest are all valid
     * inputs
     *
     * @return boolean - if all inputs are valid
     */
    public boolean validate() {
	if (this.username == null) {
	    return false;
	}

	if (this.username == "") {
	    return false;
	}

	if (this.name == null) {
	    return false;
	}

	if (this.name == "") {
	    return false;
	}

	if (this.monthlyAmount < 0.0) {
	    return false;
	}

	if (this.remainingBalance < 0.0) {
	    return false;
	}

	if (this.interest < 0.0) {
	    return false;
	} else {
	    return true;
	}
    }

    /**
     * outputs expense as a string with itemID
     *
     * @return String - Expense information
     */
    public String toStringID() {
	return this.id + " | "
	      + this.name + " | "
	      + this.monthlyAmount + " | "
	      + this.remainingBalance + " | "
	      + this.interest;
    }

    /**
     * outputs expense as a string without itemID
     *
     * @return String - Expense information
     */
    @Override
    public String toString() {
	return this.name + " | "
	      + this.monthlyAmount + " | "
	      + this.remainingBalance + " | "
	      + this.interest;
    }
}
