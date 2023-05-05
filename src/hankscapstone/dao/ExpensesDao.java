package hankscapstone.dao;

import hankscapstone.dao.ExpenseDao;
import hankscapstone.model.*;
import hankscapstone.model.Expense;
import java.sql.Connection; // Needed to establish a DB connection
import java.sql.DriverManager; // Needed to connect to the DB server
import java.sql.ResultSet; // Needed to store the result of a SQL query
import java.sql.Statement; // Needed to create a SQL query
import java.sql.SQLException; // All DB ops must be in try-catch blocks
import java.util.*; // Needed for the ArrayList ADT
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Run SQL statements to return to view/models
 *
 * @author Bryant Hanks
 * @version 1.0 2023-04-26
 */
public class ExpensesDao implements ExpenseDao<Expense> {

    private List<Expense> expenditures;
    private List<Expense> expenses;
    private List<Expense> loans;
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    /**
     * creates connection to SQL Database
     */
    public ExpensesDao() {
	expenditures = new ArrayList<Expense>();
	expenses = new ArrayList<Expense>();
	loans = new ArrayList<Expense>();

	try {
	    Class.forName("com.mysql.cj.jdbc.Driver");
	    connection = DriverManager.getConnection(
		  "jdbc:mysql://localhost:3306/expenses", "cs493",
		  "p@sswordCS493");
	} catch (ClassNotFoundException | SQLException e) {
	    System.out.println(e);
	}
    }

    /**
     * Return all expenses, excluding loans for user
     *
     * @param expense has user info
     * @return list of all expenses for user
     */
    @Override
    public List getAllExpenses(Expense expense) {

	try {
	    statement = connection.createStatement();
	    resultSet = statement.executeQuery("select itemID, username, name, "
		  + "monthlyAmount, remainingBalance, interest from "
		  + "expenses join users on userID = id "
		  + "where expense = true "
		  + "and username = '" + expense.getUsername() + "';");

	    while (resultSet.next()) {
		expense = new Expense(resultSet.getInt("itemID"),
		      resultSet.getString("username"),
		      resultSet.getString("name"),
		      resultSet.getDouble("monthlyAmount"),
		      resultSet.getDouble("remainingBalance"),
		      resultSet.getDouble("interest"));
		expense.toString();
		expenses.add(expense);
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(ExpensesDao.class.getName()).log(Level.SEVERE, null,
		  ex);
	    System.out.println(ex);
	}
	return expenses;
    }

    /**
     * Return all expenses and loans for user
     *
     * @param expense has user info
     * @return list of all expenses and loans for user
     */
    @Override
    public List getAll(Expense expense) {

	try {
	    statement = connection.createStatement();
	    resultSet = statement.executeQuery("select username, name, "
		  + "sum(monthlyAmount) as amount from expenses join users on "
		  + "userID = id where username = '" + expense.getUsername()
		  + "' group by username, name order by 3 desc;");

	    while (resultSet.next()) {
		expense = new Expense(resultSet.getString("username"),
		      resultSet.getString("name"),
		      resultSet.getDouble("amount"));
		expense.toString();
		expenditures.add(expense);
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(ExpensesDao.class.getName()).log(Level.SEVERE, null,
		  ex);
	    System.out.println(ex);
	}
	return expenditures;
    }

    /**
     * Return all loans, excluding expenses for user
     *
     * @param expense has user info
     * @return list of all loans for user
     */
    @Override
    public List getAllLoans(Expense expense) {

	try {
	    statement = connection.createStatement();
	    resultSet = statement.executeQuery("select itemID, username, name, "
		  + "monthlyAmount, remainingBalance, interest from "
		  + "expenses join users on userID = id "
		  + "where expense = false "
		  + "and username = '" + expense.getUsername() + "';");

	    while (resultSet.next()) {
		expense = new Expense(resultSet.getInt("itemID"),
		      resultSet.getString("username"),
		      resultSet.getString("name"),
		      resultSet.getDouble("monthlyAmount"),
		      resultSet.getDouble("remainingBalance"),
		      resultSet.getDouble("interest"));
		expense.toString();
		loans.add(expense);
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(ExpensesDao.class.getName()).log(Level.SEVERE, null,
		  ex);
	    System.out.println(ex);
	}
	return loans;
    }

    /**
     * Adds expense to database
     *
     * @param e all expense info
     * @return boolean whether it was added or not
     */
    @Override
    public Boolean addExpense(Expense e) {
	//String type = null;
	try {
	    statement = connection.createStatement();
	    statement.execute("insert into expenses set userID = "
		  + "(select id from users where username = '" + e.getUsername()
		  + "'), name = '" + e.getName()
		  + "', monthlyAmount = " + e.getMonthlyAmount()
		  + ", remainingBalance = 0.0, interest = 0.0"
		  + ", expense = true; ");
	    return true;
	} catch (SQLException ex) {
	    Logger.getLogger(ExpensesDao.class.getName()).log(Level.SEVERE, null,
		  ex);
	    System.out.println(ex);
	    return false;
	}
    }

    /**
     * Adds loan to database
     *
     * @param e all loan info
     * @return boolean whether it was added or not
     */
    @Override
    public Boolean addLoan(Expense e) {
	try {
	    statement = connection.createStatement();
	    statement.execute("insert into expenses set userID = "
		  + "(select id from users where username = '" + e.getUsername()
		  + "'), name = '" + e.getName()
		  + "', monthlyAmount = " + e.getMonthlyAmount()
		  + ", remainingBalance = " + e.getRemainingBalance()
		  + ", interest = " + e.getInterest()
		  + ", expense = false;");
	    return true;
	} catch (SQLException ex) {
	    Logger.getLogger(ExpensesDao.class.getName()).log(Level.SEVERE, null,
		  ex);
	    System.out.println(ex);
	    return false;
	}
    }

    /**
     * updates loan with provided info
     *
     * @param e updated loan info
     * @return boolean if loan was updated
     */
    @Override
    public Boolean updateLoan(Expense e) {
	try {
	    statement = connection.createStatement();
	    statement.execute("update expenses set monthlyAmount = "
		  + e.getMonthlyAmount()
		  + ", interest = " + e.getInterest()
		  + ", remainingBalance = " + e.getRemainingBalance()
		  + " where itemID = " + e.getID() + ";");
	    return true;
	} catch (SQLException ex) {
	    Logger.getLogger(ExpensesDao.class.getName()).log(Level.SEVERE, null,
		  ex);
	    System.out.println(ex);
	    return false;
	}
    }

    /**
     * updates expense with provided info
     *
     * @param e updated expense info
     * @return boolean if expense was updated
     */
    @Override
    public Boolean updateExpense(Expense e) {
	try {
	    statement = connection.createStatement();
	    statement.execute("update expenses set monthlyAmount = "
		  + e.getMonthlyAmount()
		  + " where itemID = " + e.getID() + ";");
	    return true;
	} catch (SQLException ex) {
	    Logger.getLogger(ExpensesDao.class.getName()).log(Level.SEVERE, null,
		  ex);
	    System.out.println(ex);
	    return false;
	}
    }

    /**
     * deletes expense
     *
     * @param e expense that needs to be deleted
     * @return boolean if expense was deleted
     */
    @Override
    public Boolean deleteExpense(Expense e) {
	try {
	    statement = connection.createStatement();
	    statement.execute("delete from expenses where "
		  + "itemID = " + e.getID() + ";");
	    return true;
	} catch (SQLException ex) {
	    Logger.getLogger(ExpensesDao.class.getName()).log(Level.SEVERE, null,
		  ex);
	    System.out.println(ex);
	    return false;
	}
    }

}
