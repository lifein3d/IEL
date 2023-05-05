package hankscapstone.model;

/**
 * Creates user object
 *
 * @author Bryant Hanks
 * @version 1.0 2023-04-26
 */
public class Users {

    protected String userName;
    protected String password;
    protected int income;

    /**
     * default constructor
     *
     * @param userName - name of the user
     * @param password - password of the user
     */
    public Users(String userName, String password) {
	this.userName = userName;
	this.password = password;
	this.income = 0;
    }

    /**
     * constructor
     *
     * @param userName - name of the user
     * @param password - password of the user
     * @param income - income of the user
     */
    public Users(String userName, String password, int income) {
	this.userName = userName;
	this.password = password;
	this.income = income;
    }

    public int getIncome() {
	return income;
    }

    public void setIncome(int income) {
	this.income = income;
    }

    public String getUserName() {
	return userName;
    }

    public void setUserName(String userName) {
	this.userName = userName;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    /**
     * validate all fields are valid
     *
     * @return boolean - if fields are valid
     */
    public boolean validate() {
	if (this.userName == null) {
	    return false;
	}

	if (this.userName.equals("")) {
	    return false;
	}

	if (this.password == null) {
	    return false;
	}

	if (this.password.equals("")) {
	    return false;
	}

	if (this.income < 0) {
	    return false;
	} else {
	    return true;
	}
    }

    /**
     * validates just username and password
     *
     * @return boolean - if username and password are valid
     */
    public boolean validateLogin() {
	if (this.userName == null) {
	    return false;
	}

	if (this.userName.equals("")) {
	    return false;
	}

	if (this.password == null) {
	    return false;
	}

	if (this.password.equals("")) {
	    return false;
	} else {
	    return true;
	}
    }

    /**
     * outputs user info in a string
     *
     * @return String - user info
     */
    @Override
    public String toString() {
	return this.userName + " | " + this.password + " | " + this.income;
    }
}
