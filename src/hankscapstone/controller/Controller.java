package hankscapstone.controller;

import hankscapstone.dao.UsersDao;
import hankscapstone.dao.UserDao;
import hankscapstone.dao.ExpensesDao;
import static hankscapstone.model.Analysis.expenseAnalysis;
import static hankscapstone.model.Analysis.snowballAnalysis;
import hankscapstone.model.Encryption;
import hankscapstone.model.Errors;
import hankscapstone.model.ExpenditureTable;
import hankscapstone.model.Expense;
import hankscapstone.model.ExpenseTable;
import hankscapstone.model.ExpensesCombo;
import hankscapstone.model.LinkedList;
import static hankscapstone.model.LinkedList.clear;
import static hankscapstone.model.LinkedList.insert;
import static hankscapstone.model.LinkedList.snowballSort;
import hankscapstone.model.LoanTable;
import hankscapstone.model.LoansCombo;
import hankscapstone.model.Notifications;
import hankscapstone.model.Users;
import hankscapstone.view.MainUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Controller that handles actions between models and view
 *
 * @author Bryant Hanks
 * @version 1.0 - 2023-04-26
 */
public class Controller {

    private MainUI view;
    private final UserDao userDatabase = new UsersDao();
    private final ExpensesDao expenseDatabase = new ExpensesDao();
    private List<Expense> expenditures = null; //list of expenses and loans combined
    private List<Expense> expenses = null; //list of expenses
    private List<Expense> loans = null; //list of loans
    private ExpenditureTable expenditureModel = new ExpenditureTable();
    private ExpenseTable expenseModel = new ExpenseTable();
    private ExpensesCombo expenseCombo = new ExpensesCombo();
    private LoansCombo loanCombo = new LoansCombo();
    private LoanTable loanModel = new LoanTable();
    private int selectedRow = -1; //make sure no rows are selected in jtables
    private LinkedList linkedList = new LinkedList(); //linked list of loans
    private Users authorizedUser; //stores logged in user info for db calls
    private Expense authorizedUserExpense; //logged in users expenses for db calls
    private Errors error = new Errors();
    private Notifications notification = new Notifications();

    public Controller(int num) {

    }

    public Controller() {
	view = new MainUI();

	/**
	 * When tabs are changed update appropriate lists to display most
	 * updated info
	 */
	view.tabbedPaneChangeListener(new ChangeListener() {
	    @Override
	    public void stateChanged(ChangeEvent e) {
		if (view.getTabbedPane() == 1) {

		    if (expenses != null) {
			expenses.clear();
		    }

		    selectedRow = -1;
		    expenses
			  = expenseDatabase.getAllExpenses(authorizedUserExpense);

		    expenseModel.setExpenses(expenses);
		    view.setExpensesTableModel(expenseModel);
		    expenseModel.fireTableDataChanged();

		    view.setExpensesTableColRow(false, true);
		}

		if (view.getTabbedPane() == 2) {

		    if (loans != null) {
			loans.clear();
		    }

		    selectedRow = -1;
		    loans = expenseDatabase.getAllLoans(authorizedUserExpense);

		    loanModel.setExpenses(loans);
		    view.setLoansTableModel(loanModel);
		    loanModel.fireTableDataChanged();

		    view.setLoansTableColRow(false, true);
		}

		if (view.getTabbedPane() == 3) {

		    if (expenditures != null) {
			expenditures.clear();
		    }

		    expenses
			  = expenseDatabase.getAllExpenses(authorizedUserExpense);
		    loans = expenseDatabase.getAllLoans(authorizedUserExpense);
		    expenditures = expenseDatabase.getAll(authorizedUserExpense);

		    expenditureModel.setExpenses(expenditures);
		    view.setAnalysisTableModel(expenditureModel);
		    expenditureModel.fireTableDataChanged();

		    view.setAnalysisTableColRow(false, false);
		}
	    }
	});

	/**
	 * Validates input information, encrypts password, creates and logs in
	 * new user
	 */
	view.userCreateButtonActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		authorizedUser = new Users(view.getUsernameTextField().getText(),
		      String.valueOf(view.getPasswordField().getPassword()));
		authorizedUserExpense = new Expense(authorizedUser.getUserName(),
		      "", 0.0, 0.0, 0.0);

		//confirm fields are have valid inputs
		if (!authorizedUser.validateLogin()) {
		    view.popup(error.getUserPwd());

		} else {
		    view.setExpenseIncomeTextField("0");

		    //encrypts password
		    try {
			authorizedUser.setPassword(Encryption.toHexString(
			      Encryption.getSHA(authorizedUser.getPassword())));
		    } catch (NoSuchAlgorithmException ex) {
			//Logger.getLogger(MainUI.class.getName()).log(Level.SEVERE, null, ex);
			view.popup(error.getEncrypt());
			return;
		    }

		    //confirm user was added to database
		    if (!userDatabase.addUser(authorizedUser)) {
			view.popup(error.getUserExt());
			return;
		    }
		    view.popup(notification.getUserCreated());
		    view.setTabbedPaneBool(1, true);
		    view.setTabbedPaneBool(2, true);
		    view.setTabbedPaneBool(3, true);
		    view.setUsernameTextField("");
		    view.setPasswordField("");
		    view.setAnalysisAdditionalTextField("0");
		    view.setTabbedPane(1);
		}
	    }
	});

	/**
	 * Validates input information, encrypts password, logs in user
	 */
	view.userLoginButtonActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		authorizedUser = new Users(view.getUsernameTextField().getText(),
		      String.valueOf(view.getPasswordField().getPassword()));
		authorizedUserExpense = new Expense(authorizedUser.getUserName(),
		      "", 0.0, 0.0, 0.0);

		//validate input fields are valid
		if (!authorizedUser.validateLogin()) {
		    view.popup(error.getInvalidUserPwd());
		} else {
		    //encrypts password
		    try {
			authorizedUser.setPassword(Encryption
			      .toHexString(Encryption.getSHA(authorizedUser
				    .getPassword())));
		    } catch (NoSuchAlgorithmException ex) {
			view.popup(error.getEncrypt());
		    }

		    //confirms if login credits were correct
		    if (!userDatabase.getUser(authorizedUser)) {
			view.popup(error.getInvalidUserPwd());
			return;
		    }

		    view.setTabbedPaneBool(1, true);
		    view.setTabbedPaneBool(2, true);
		    view.setTabbedPaneBool(3, true);
		    view.setUsernameTextField("");
		    view.setPasswordField("");
		    view.setAnalysisAdditionalTextField("0");

		    view.setExpenseIncomeTextField(Integer.toString(userDatabase
			  .getIncome(authorizedUser)));
		    authorizedUser.setIncome(userDatabase.getIncome(
			  authorizedUser));
		    view.setTabbedPane(1);
		}
	    }
	});

	/**
	 * Validates input information and updates income
	 */
	view.expenseUpdateIncomeButtonActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		try {
		    authorizedUser.setIncome(Integer.parseInt(
			  view.getExpenseIncomeTextField().getText()));
		} catch (NumberFormatException ex) {
		    //Logger.getLogger(MainUI.class.getName()).log(Level.SEVERE, null, e);
		    view.popup(error.getIncomeNumber());
		    return;
		}

		if (authorizedUser.getIncome() < 0) {
		    view.popup(error.getIncomeNumber());
		    return;
		}

		if (!userDatabase.updateIncome(authorizedUser)) {
		    view.popup(error.getInvalidUserPwdInc());
		}
	    }

	});

	/**
	 * Validates input information adds to expenses
	 */
	view.expenseAddButtonActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		String uid = authorizedUser.getUserName();
		String name = view.getExpenseNameCombo().getSelectedItem()
		      .toString();
		double amt = 0.0;

		try {
		    amt = Double.valueOf(view.getExpenseAmountTextField()
			  .getText());
		} catch (NumberFormatException ex) {
		    view.popup(error.getAmountNumber());
		    return;
		}

		Expense expense = new Expense(uid, name, amt, 0.0, 0.0);

		if (!expense.validate()) {
		    view.popup(error.getAmountNumber());
		    return;
		}

		if (!expenseDatabase.addExpense(expense)) {
		    view.popup(error.getInvalidUserPwd());
		    return;
		}
		expenses.clear();
		expenses = expenseDatabase.getAllExpenses(authorizedUserExpense);
		selectedRow = -1;
		expenseModel.setExpenses(expenses);
		expenseModel.fireTableDataChanged();
		view.setExpenseNameCombo(0);
		view.setExpenseAmountTextField("");
	    }
	});

	/**
	 * Validates input information and updates expense
	 */
	view.expenseUpdateButtonActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		if (selectedRow < 0) {
		    view.popup(error.getTableError());
		    return;
		}

		String name = view.getExpenseNameCombo().getSelectedItem()
		      .toString();
		double amt = 0.0;
		try {
		    amt = Double.valueOf(view.getExpenseAmountTextField()
			  .getText());
		} catch (NumberFormatException ex) {

		    view.popup(error.getAmountNumber());
		    return;
		}

		Expense expense = expenses.get(selectedRow);
		expense.setMonthlyAmount(amt);

		if (name.compareTo(expense.getName()) != 0) {
		    view.popup(error.getUpdateName());
		    return;
		}
		if (!expense.validate()) {
		    view.popup(error.getAmountNumber());
		    return;
		}

		if (!expenseDatabase.updateExpense(expense)) {
		    view.popup(error.getInvalidUserPwd());
		    return;
		}
		selectedRow = -1;
		expenses.clear();
		expenses = expenseDatabase.getAllExpenses(authorizedUserExpense);
		expenseModel.fireTableDataChanged();
		view.setExpenseNameCombo(0);
		view.setExpenseAmountTextField("");
	    }
	});

	/**
	 * Validates input information, confirms action, deletes expense
	 */
	view.expenseDeleteButtonActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		double amt = 0.0;

		if (selectedRow < 0) {
		    view.popup(error.getTableError());
		    return;
		}

		try {
		    amt = Double.valueOf(view.getExpenseAmountTextField()
			  .getText());
		} catch (NumberFormatException ex) {
		    view.popup(error.getAmountNumber());
		    return;
		}

		Expense expense = expenses.get(selectedRow);
		int selectedOption = 0;
		selectedOption = JOptionPane.showConfirmDialog(null,
		      "Delete " + expense.getName() + "?",
		      "Yes/No",
		      JOptionPane.YES_NO_OPTION);
		if (selectedOption == JOptionPane.YES_OPTION) {
		    expenseDatabase.deleteExpense(expense);
		    expenses.clear();
		    expenses = expenseDatabase.getAllExpenses(
			  authorizedUserExpense);
		    expenseModel.fireTableDataChanged();

		    selectedRow = -1;
		    view.setExpenseNameCombo(0);
		    view.setExpenseAmountTextField("");
		}
	    }
	});

	/**
	 * Validates input information adds to loans
	 */
	view.loanAddButtonActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		String uid = authorizedUser.getUserName();
		String name = view.getLoanNameCombo().getSelectedItem()
		      .toString();
		double amt = 0.0;
		double remaining = 0.0;
		double interest = 0.0;

		try {
		    amt = Double.valueOf(view.getLoanAmountTextField()
			  .getText());
		    remaining = Double.valueOf(view.getLoanRemainingTextField()
			  .getText());
		    interest = Double.valueOf(view.getLoanInterestTextField()
			  .getText());
		} catch (NumberFormatException ex) {
		    view.popup(error.getLoanNumbers());
		    return;
		}

		Expense expense = new Expense(uid, name, amt, remaining,
		      interest);

		if (!expense.validate()) {
		    view.popup(error.getAmountNumber());
		    return;
		}

		if (!expenseDatabase.addLoan(expense)) {
		    view.popup(error.getInvalidUserPwd());
		    return;
		}
		loans.clear();
		loans = expenseDatabase.getAllLoans(authorizedUserExpense);
		selectedRow = -1;
		loanModel.setExpenses(loans);
		loanModel.fireTableDataChanged();
		view.setLoanNameCombo(0);
		view.setLoanAmountTextField("");
		view.setLoanRemainingTextField("");
		view.setLoanInterestTextField("");
	    }

	});

	/**
	 * Validates input information and updates loan
	 */
	view.loanUpdateButtonActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		if (selectedRow < 0) {
		    view.popup(error.getTableError());
		    return;
		}

		String name = view.getLoanNameCombo().getSelectedItem()
		      .toString();
		double amount = 0.0;
		double interest = 0.0;
		double remainingBalance = 0.0;

		try {
		    amount = Double.valueOf(view.getLoanAmountTextField()
			  .getText());
		    remainingBalance = Double.valueOf(view
			  .getLoanRemainingTextField().getText());
		    interest = Double.valueOf(view.getLoanInterestTextField()
			  .getText());
		} catch (NumberFormatException ex) {
		    view.popup(error.getLoanNumbers());
		    return;
		}

		Expense loan = loans.get(selectedRow);
		loan.setMonthlyAmount(amount);
		loan.setInterest(interest);
		loan.setRemainingBalance(remainingBalance);

		if (name.compareTo(loan.getName()) != 0) {
		    view.popup(error.getUpdateName());
		    return;
		}
		if (!loan.validate()) {
		    view.popup(error.getAmountNumber());
		    return;
		}

		if (!expenseDatabase.updateLoan(loan)) {
		    view.popup(error.getInvalidUserPwd());
		    return;
		}
		loans.clear();
		loans = expenseDatabase.getAllLoans(authorizedUserExpense);
		selectedRow = -1;
		loanModel.fireTableDataChanged();
		view.setLoanNameCombo(0);
		view.setLoanAmountTextField("");
		view.setLoanRemainingTextField("");
		view.setLoanInterestTextField("");
	    }

	});

	/**
	 * Validates input information, confirms action, deletes loan
	 */
	view.loanDeleteButtonActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		double amt = 0.0;
		int selectedOption = 0;

		if (selectedRow < 0) {
		    view.popup(error.getTableError());
		    return;
		}

		try {
		    amt = Double.valueOf(view.getLoanAmountTextField()
			  .getText());
		} catch (NumberFormatException ex) {
		    view.popup(error.getLoanNumbers());
		    return;
		}

		Expense loan = loans.get(selectedRow);

		selectedOption = JOptionPane.showConfirmDialog(null,
		      "Delete " + loan.getName() + "?",
		      "Yes/No",
		      JOptionPane.YES_NO_OPTION);
		if (selectedOption == JOptionPane.YES_OPTION) {
		    expenseDatabase.deleteExpense(loan);
		    loans.clear();
		    loans = expenseDatabase.getAllLoans(authorizedUserExpense);
		    loanModel.fireTableDataChanged();
		    selectedRow = -1;
		    view.setLoanNameCombo(0);
		    view.setLoanAmountTextField("");
		    view.setLoanRemainingTextField("");
		    view.setLoanInterestTextField("");
		}
	    }

	});

	/**
	 * Displays expense analysis report
	 */
	view.analysisExpenseButtonActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		view.popup(expenseAnalysis(expenditures, authorizedUser));
	    }

	});

	/**
	 * Displays snowball analysis report
	 */
	view.analysisSnowballButtonActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		Double additionalPayment = 0.0;

		clear(linkedList);

		for (int i = 0; i < loans.size(); ++i) {
		    linkedList = insert(linkedList, loans.get(i));
		}

		snowballSort(linkedList);

		try {
		    additionalPayment = Double.valueOf(view
			  .getAnalysisAdditionalTextField().getText());
		} catch (NumberFormatException ex) {
		    view.popup(error.getAdditionalPmt());
		    return;
		}

		if (additionalPayment < 0.0) {
		    view.popup(error.getAdditionalPmt());
		    return;
		}

		view.popup(snowballAnalysis(linkedList, additionalPayment));
		loans.clear();

		loans = expenseDatabase.getAllLoans(authorizedUserExpense);
	    }

	});

	/**
	 * Explain expense analysis report
	 */
	view.expenseMenuItemActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		view.popup(notification.getExpenseMenu());
	    }

	});

	/**
	 * Explain snowball analysis report
	 */
	view.snowballMenuItemActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		view.popup(notification.getSnowballMenu());
	    }

	});

	/**
	 * Explain what this tool is about and why it was created
	 */
	view.aboutMenuItemActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		view.popup(notification.getAboutMenu());
	    }

	});

	/**
	 * The following actionlisteners are to exit the program
	 */
	view.exitMenuItemActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		System.exit(0);
	    }
	});
	view.userExitButtonActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		System.exit(0);
	    }
	});
	view.expenseExitButtonActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		System.exit(0);
	    }
	});
	view.loanExitButtonActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		System.exit(0);
	    }
	});
	view.analysisExitButtonActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		System.exit(0);
	    }
	});

	/**
	 * The following mouselisteners are to populate fields when an item is
	 * clicked
	 */
	view.expensesTableMouseListener(new MouseListener() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
		int row = view.getExpensesTable().rowAtPoint(e.getPoint());
		DefaultComboBoxModel dm = expenseComboModel();
		int index = 0;
		if (row >= 0) {
		    selectedRow = row;
		    index = dm.getIndexOf(expenseModel.getValueAt(row, 2)
			  .toString());
		    view.setExpenseAmountTextField(expenseModel
			  .getValueAt(row, 3).toString());
		    view.setExpenseNameCombo(index);
		}
	    }

	    @Override
	    public void mousePressed(MouseEvent e) {

	    }

	    @Override
	    public void mouseReleased(MouseEvent e) {

	    }

	    @Override
	    public void mouseEntered(MouseEvent e) {

	    }

	    @Override
	    public void mouseExited(MouseEvent e) {

	    }

	});
	view.loansTableMouseListener(new MouseListener() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
		int row = view.getLoansTable().rowAtPoint(e.getPoint());
		DefaultComboBoxModel dm = loanComboModel();
		int index = 0;
		if (row >= 0) {
		    selectedRow = row;
		    index = dm.getIndexOf(loanModel.getValueAt(row, 2)
			  .toString());
		    view.getLoanNameCombo().setSelectedIndex(index);
		    view.getLoanAmountTextField().setText(loanModel
			  .getValueAt(row, 3).toString());
		    view.getLoanRemainingTextField().setText(loanModel
			  .getValueAt(row, 4).toString());
		    view.getLoanInterestTextField().setText(loanModel
			  .getValueAt(row, 5).toString());
		}
	    }

	    @Override
	    public void mousePressed(MouseEvent e) {

	    }

	    @Override
	    public void mouseReleased(MouseEvent e) {

	    }

	    @Override
	    public void mouseEntered(MouseEvent e) {

	    }

	    @Override
	    public void mouseExited(MouseEvent e) {

	    }

	});
    }

    /**
     * calls for the list of expenses to go into combo box
     *
     * @return DefaultComboBoxModel - all of the expenses for the combo box
     */
    public DefaultComboBoxModel expenseComboModel() {
	DefaultComboBoxModel dm = new DefaultComboBoxModel(expenseCombo
	      .getExpenses());
	return dm;
    }

    /**
     * calls for the list of loans to go into the combo box
     *
     * @return DefaultComboBoxModel - all of the loans for the combo box
     */
    public DefaultComboBoxModel loanComboModel() {
	DefaultComboBoxModel dm = new DefaultComboBoxModel(loanCombo
	      .getLoans());
	return dm;
    }
}
