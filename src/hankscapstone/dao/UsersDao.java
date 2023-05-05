package hankscapstone.dao;

import hankscapstone.dao.ExpensesDao;
import hankscapstone.model.*;
import hankscapstone.model.Expense;
import hankscapstone.model.Users;
import java.sql.Connection; // Needed to establish a DB connection
import java.sql.DriverManager; // Needed to connect to the DB server
import java.sql.ResultSet; // Needed to store the result of a SQL query
import java.sql.Statement; // Needed to create a SQL query
import java.sql.SQLException; // All DB ops must be in try-catch blocks
import java.util.*; // Needed for the ArrayList ADT
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Run SQL statements to return to view/models
 *
 * @author Bryant Hanks
 * @version 1.0 2023-04-26
 */
public class UsersDao implements UserDao<Users> {

    private List<Users> users;
    private List<Expense> expenditures;
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    /**
     * creates connection to SQL Database
     */
    public UsersDao() {
	users = new ArrayList<Users>();

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
     * returns all users with the given user criteria
     *
     * @param u user info used for criteria
     * @return boolean if a single user was found with matching criteria
     */
    @Override
    public Boolean getUser(Users u) {
	try {
	    Boolean resultInd = false;
	    statement = connection.createStatement();
	    resultSet = statement.executeQuery("select * from users "
		  + "where username = '" + u.getUserName()
		  + "' and password = '" + u.getPassword()
		  + "';");

	    if (resultSet.isBeforeFirst()) {
		users.clear();
		users.add(u);
		resultInd = true;
	    }
	    if (users.size() != 1) {
		return false;
	    }
	    if (resultInd == false) {
		return false;
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null,
		  ex);
	    System.out.println(ex);
	    return false;
	}
	return true;
    }

    /**
     * Adds user to database as long as username isn't taken
     *
     * @param u user info to ad
     * @return boolean if user was added to database
     */
    @Override
    public Boolean addUser(Users u) {
	//String type = null;
	try {
	    statement = connection.createStatement();
	    resultSet = statement.executeQuery("select * from users "
		  + "where username = '" + u.getUserName()
		  + "';");
	    if (resultSet.isBeforeFirst()) {
		//System.out.println("USERNAME ALREADY EXISTS");
		return false;
	    } else {
		statement.execute("insert into users (username, password, income) "
		      + "values ('" + u.getUserName()
		      + "', '" + u.getPassword()
		      + "', " + u.getIncome() + ");"
		);
	    }
	    return true;
	} catch (SQLException ex) {
	    Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null,
		  ex);
	    System.out.println(ex);
	    return false;
	}
    }

    /**
     * Updates income of user that is provided
     *
     * @param u has user and income information
     * @return boolean if income was updated
     */
    @Override
    public Boolean updateIncome(Users u) {
	try {
	    statement = connection.createStatement();
	    boolean stmt = statement.execute("update users set income = "
		  + u.getIncome()
		  + " where username = '"
		  + u.getUserName() + "' and password = '" + u.getPassword() + "';");
	    if (statement.getUpdateCount() > 0) {
		JOptionPane.showMessageDialog(null, "Income has been updated");
	    } else {
		JOptionPane.showMessageDialog(null, "Income update failed, check username and password");
	    }

	    return true;
	} catch (SQLException ex) {
	    Logger.getLogger(ExpensesDao.class.getName()).log(Level.SEVERE, null,
		  ex);
	    System.out.println(ex);
	    return false;
	}
    }

    /**
     * Get income of selected user
     *
     * @param u has user information
     * @return int - income of the specified user
     */
    @Override
    public int getIncome(Users u) {
	int income = 0;
	try {
	    Boolean resultInd = false;
	    statement = connection.createStatement();
	    resultSet = statement.executeQuery("select * from users "
		  + "where username = '" + u.getUserName()
		  + "' and password = '" + u.getPassword()
		  + "';");

	    if (resultSet.next()) {
		income = resultSet.getInt("income");
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null,
		  ex);
	    System.out.println(ex);
	}
	return income;
    }

}
