package hankscapstone.view;

import hankscapstone.model.*;
import hankscapstone.controller.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Run the user interface and manages how the interface interacts and displays
 * information to the user
 *
 * @author Bryant Hanks
 * @version 1.0 - 2023-04-26
 */
public class MainUI extends javax.swing.JFrame {

    private Controller controlCall = new Controller(1);

    /**
     * Creates new form MainUI, sets listeners, sets grey out properties
     */
    public MainUI() {
	this.setVisible(true);
	this.setTitle("Income Expense and Loan Analysis Tool");
	initComponents();
	userLoginButton.setEnabled(false);
	userCreateButton.setEnabled(false);
	tabbedPane.setEnabledAt(1, false);
	tabbedPane.setEnabledAt(2, false);
	tabbedPane.setEnabledAt(3, false);

	//FocusListers are to allow selecting all of the texts for ease of use
	usernameTextField.addFocusListener(new FocusListener() {
	    @Override
	    public void focusGained(FocusEvent e) {
		usernameTextField.selectAll();

	    }

	    @Override
	    public void focusLost(FocusEvent e) {

	    }
	});

	passwordField.addFocusListener(new FocusListener() {
	    @Override
	    public void focusGained(FocusEvent e) {
		passwordField.selectAll();
	    }

	    @Override
	    public void focusLost(FocusEvent e) {

	    }
	});

	expenseIncomeTextField.addFocusListener(new FocusListener() {
	    @Override
	    public void focusGained(FocusEvent e) {
		expenseIncomeTextField.selectAll();

	    }

	    @Override
	    public void focusLost(FocusEvent e) {

	    }
	});

	expenseAmountTextField.addFocusListener(new FocusListener() {
	    @Override
	    public void focusGained(FocusEvent e) {
		expenseAmountTextField.selectAll();

	    }

	    @Override
	    public void focusLost(FocusEvent e) {

	    }
	});

	loanAmountTextField.addFocusListener(new FocusListener() {
	    @Override
	    public void focusGained(FocusEvent e) {
		loanAmountTextField.selectAll();

	    }

	    @Override
	    public void focusLost(FocusEvent e) {

	    }
	});

	loanRemainingTextField.addFocusListener(new FocusListener() {
	    @Override
	    public void focusGained(FocusEvent e) {
		loanRemainingTextField.selectAll();

	    }

	    @Override
	    public void focusLost(FocusEvent e) {

	    }
	});

	loanInterestTextField.addFocusListener(new FocusListener() {
	    @Override
	    public void focusGained(FocusEvent e) {
		loanInterestTextField.selectAll();

	    }

	    @Override
	    public void focusLost(FocusEvent e) {

	    }
	});

	analysisAdditionalTextField.addFocusListener(new FocusListener() {
	    @Override
	    public void focusGained(FocusEvent e) {
		analysisAdditionalTextField.selectAll();

	    }

	    @Override
	    public void focusLost(FocusEvent e) {

	    }
	});

	//getDocuments ensure the create/login buttons are only available when 
	//appropriate fields are filled
	usernameTextField.getDocument().addDocumentListener(new DocumentListener() {
	    @Override
	    public void insertUpdate(DocumentEvent e) {
		check();
	    }

	    @Override
	    public void removeUpdate(DocumentEvent e) {
		check();
	    }

	    @Override
	    public void changedUpdate(DocumentEvent e) {
		check();
	    }

	    public void check() {
		if (usernameTextField.getText().length() > 0) {
		    if (passwordField.getPassword().length > 0) {
			userLoginButton.setEnabled(true);
			userCreateButton.setEnabled(true);
		    }
		} else {
		    userLoginButton.setEnabled(false);
		    userCreateButton.setEnabled(false);
		}
	    }
	});

	passwordField.getDocument().addDocumentListener(new DocumentListener() {
	    @Override
	    public void insertUpdate(DocumentEvent e) {
		check();
	    }

	    @Override
	    public void removeUpdate(DocumentEvent e) {
		check();
	    }

	    @Override
	    public void changedUpdate(DocumentEvent e) {
		check();
	    }

	    public void check() {
		if (passwordField.getPassword().length > 0) {
		    if (usernameTextField.getText().length() > 0) {
			userLoginButton.setEnabled(true);
			userCreateButton.setEnabled(true);
		    }
		} else {
		    userLoginButton.setEnabled(false);
		    userCreateButton.setEnabled(false);
		}
	    }
	});
    }

    /**
     * Brings a pop up to the user
     *
     * @param str - sting to display in pop up
     */
    public void popup(String str) {
	JOptionPane.showMessageDialog(null, str);
    }

    /**
     * Listen for when button is clicked
     *
     * @param listener
     */
    public void userCreateButtonActionListener(ActionListener listener) {
	userCreateButton.addActionListener(listener);
    }

    /**
     * Listen for when button is clicked
     *
     * @param listener
     */
    public void userLoginButtonActionListener(ActionListener listener) {
	userLoginButton.addActionListener(listener);
    }

    /**
     * Listen for when button is clicked
     *
     * @param listener
     */
    public void expenseUpdateIncomeButtonActionListener(ActionListener listener) {
	expenseUpdateIncomeButton.addActionListener(listener);
    }

    /**
     * Listen for when button is clicked
     *
     * @param listener
     */
    public void expenseAddButtonActionListener(ActionListener listener) {
	expenseAddButton.addActionListener(listener);
    }

    /**
     * Listen for when button is clicked
     *
     * @param listener
     */
    public void expenseUpdateButtonActionListener(ActionListener listener) {
	expenseUpdateButton.addActionListener(listener);
    }

    /**
     * Listen for when button is clicked
     *
     * @param listener
     */
    public void expenseDeleteButtonActionListener(ActionListener listener) {
	expenseDeleteButton.addActionListener(listener);
    }

    /**
     * Listen for when button is clicked
     *
     * @param listener
     */
    public void loanAddButtonActionListener(ActionListener listener) {
	loanAddButton.addActionListener(listener);
    }

    /**
     * Listen for when button is clicked
     *
     * @param listener
     */
    public void loanUpdateButtonActionListener(ActionListener listener) {
	loanUpdateButton.addActionListener(listener);
    }

    /**
     * Listen for when button is clicked
     *
     * @param listener
     */
    public void loanDeleteButtonActionListener(ActionListener listener) {
	loanDeleteButton.addActionListener(listener);
    }

    /**
     * Listen for when button is clicked
     *
     * @param listener
     */
    public void analysisExpenseButtonActionListener(ActionListener listener) {
	analysisExpenseButton.addActionListener(listener);
    }

    /**
     * Listen for when button is clicked
     *
     * @param listener
     */
    public void analysisSnowballButtonActionListener(ActionListener listener) {
	analysisSnowballButton.addActionListener(listener);
    }

    /**
     * Listen for when an item is clicked in the table
     *
     * @param listener
     */
    public void expensesTableMouseListener(MouseListener listener) {
	expensesTable.addMouseListener(listener);
    }

    /**
     * Listen for when an item is clicked in the table
     *
     * @param listener
     */
    public void loansTableMouseListener(MouseListener listener) {
	loansTable.addMouseListener(listener);
    }

    /**
     * Listen for when the tabs state has changed
     *
     * @param listener
     */
    public void tabbedPaneChangeListener(ChangeListener listener) {
	tabbedPane.addChangeListener(listener);
    }

    /**
     * Listen for when menu item is clicked
     *
     * @param listener
     */
    public void expenseMenuItemActionListener(ActionListener listener) {
	expenseMenuItem.addActionListener(listener);
    }

    /**
     * Listen for when menu item is clicked
     *
     * @param listener
     */
    public void snowballMenuItemActionListener(ActionListener listener) {
	snowballMenuItem.addActionListener(listener);
    }

    /**
     * Listen for when menu item is clicked
     *
     * @param listener
     */
    public void aboutMenuItemActionListener(ActionListener listener) {
	aboutMenuItem.addActionListener(listener);
    }

    /**
     * Listen for when menu item is clicked
     *
     * @param listener
     */
    public void exitMenuItemActionListener(ActionListener listener) {
	exitMenuItem.addActionListener(listener);
    }

    /**
     * Listen for when button is clicked
     *
     * @param listener
     */
    public void userExitButtonActionListener(ActionListener listener) {
	userExitButton.addActionListener(listener);
    }

    /**
     * Listen for when button is clicked
     *
     * @param listener
     */
    public void expenseExitButtonActionListener(ActionListener listener) {
	expenseExitButton.addActionListener(listener);
    }

    /**
     * Listen for when button is clicked
     *
     * @param listener
     */
    public void loanExitButtonActionListener(ActionListener listener) {
	loanExitButton.addActionListener(listener);
    }

    /**
     * Listen for when button is clicked
     *
     * @param listener
     */
    public void analysisExitButtonActionListener(ActionListener listener) {
	analysisExitButton.addActionListener(listener);
    }

    /**
     * Provide a list of all acceptable expense names sorted alphabetically
     *
     * @return list of available expense names
     */
    private DefaultComboBoxModel getExpenses() {
	return controlCall.expenseComboModel();
    }

    /**
     * Provide a list of all acceptable loan names sorted alphabetically
     *
     * @return list of available loan names
     */
    private DefaultComboBoxModel getLoans() {
	return controlCall.loanComboModel();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabbedPane = new javax.swing.JTabbedPane();
        userPanel = new javax.swing.JPanel();
        usernameLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        userCreateButton = new javax.swing.JButton();
        userLoginButton = new javax.swing.JButton();
        userExitButton = new javax.swing.JButton();
        usernameTextField = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        expensePanel = new javax.swing.JPanel();
        expenseNameCombo = new javax.swing.JComboBox<>();
        expenseNameLabel = new javax.swing.JLabel();
        expenseAmountLabel = new javax.swing.JLabel();
        expenseAmountTextField = new javax.swing.JTextField();
        expenseAddButton = new javax.swing.JButton();
        expenseUpdateButton = new javax.swing.JButton();
        expenseDeleteButton = new javax.swing.JButton();
        expenseExitButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        expensesTable = new javax.swing.JTable();
        expenseUpdateIncomeButton = new javax.swing.JButton();
        expenseIncomeTextField = new javax.swing.JTextField();
        expenseIncomeLabel = new javax.swing.JLabel();
        loanPanel = new javax.swing.JPanel();
        loanNameLabel = new javax.swing.JLabel();
        loanAmountLabel = new javax.swing.JLabel();
        loanRemainingLabel = new javax.swing.JLabel();
        loanInterestLabel = new javax.swing.JLabel();
        loanNameCombo = new javax.swing.JComboBox<>();
        loanAmountTextField = new javax.swing.JTextField();
        loanRemainingTextField = new javax.swing.JTextField();
        loanInterestTextField = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        loansTable = new javax.swing.JTable();
        loanAddButton = new javax.swing.JButton();
        loanUpdateButton = new javax.swing.JButton();
        loanDeleteButton = new javax.swing.JButton();
        loanExitButton = new javax.swing.JButton();
        analysisPanel = new javax.swing.JPanel();
        analysisExpenseButton = new javax.swing.JButton();
        analysisSnowballButton = new javax.swing.JButton();
        analysisExitButton = new javax.swing.JButton();
        analysis = new javax.swing.JScrollPane();
        analysisTable = new javax.swing.JTable();
        analysisAdditionalLabel = new javax.swing.JLabel();
        analysisAdditionalTextField = new javax.swing.JTextField();
        fileMenuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        exitMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        expenseMenuItem = new javax.swing.JMenuItem();
        snowballMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabbedPane.setToolTipText("");
        tabbedPane.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        usernameLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        usernameLabel.setLabelFor(usernameTextField);
        usernameLabel.setText("Username");
        usernameLabel.setToolTipText("Enter your username");

        passwordLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        passwordLabel.setText("Password");
        passwordLabel.setToolTipText("Enter your password");

        userCreateButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        userCreateButton.setMnemonic('c');
        userCreateButton.setText("Create");
        userCreateButton.setToolTipText("Click here to create user");

        userLoginButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        userLoginButton.setMnemonic('l');
        userLoginButton.setText("Login");
        userLoginButton.setToolTipText("Click here to login");

        userExitButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        userExitButton.setMnemonic('x');
        userExitButton.setText("Exit");
        userExitButton.setToolTipText("Click here to exit program");

        usernameTextField.setToolTipText("This is where you enter your username");
        usernameTextField.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        passwordField.setToolTipText("This is where you enter your password");

        javax.swing.GroupLayout userPanelLayout = new javax.swing.GroupLayout(userPanel);
        userPanel.setLayout(userPanelLayout);
        userPanelLayout.setHorizontalGroup(
            userPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userPanelLayout.createSequentialGroup()
                .addContainerGap(256, Short.MAX_VALUE)
                .addGroup(userPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(userPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(usernameLabel)
                        .addComponent(userCreateButton, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(passwordLabel))
                .addGap(18, 18, 18)
                .addGroup(userPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(passwordField)
                    .addGroup(userPanelLayout.createSequentialGroup()
                        .addComponent(userLoginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(userExitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(usernameTextField, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(279, 279, 279))
        );
        userPanelLayout.setVerticalGroup(
            userPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userPanelLayout.createSequentialGroup()
                .addGap(181, 181, 181)
                .addGroup(userPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usernameLabel))
                .addGap(18, 18, 18)
                .addGroup(userPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordLabel)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(userPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userCreateButton)
                    .addComponent(userLoginButton)
                    .addComponent(userExitButton))
                .addContainerGap(254, Short.MAX_VALUE))
        );

        usernameLabel.getAccessibleContext().setAccessibleName("UsernameLabel");
        usernameTextField.getAccessibleContext().setAccessibleParent(usernameLabel);
        passwordField.getAccessibleContext().setAccessibleParent(passwordLabel);

        tabbedPane.addTab("User", userPanel);

        expenseNameCombo.setModel(getExpenses());
        expenseNameCombo.setToolTipText("List of expenses to add");

        expenseNameLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        expenseNameLabel.setText("Expense");
        expenseNameLabel.setToolTipText("Choose the type of expense");

        expenseAmountLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        expenseAmountLabel.setText("Amount");
        expenseAmountLabel.setToolTipText("Enter the amount of the expense");

        expenseAmountTextField.setToolTipText("Amount of the selected expense");
        expenseAmountTextField.setMinimumSize(new java.awt.Dimension(30, 20));

        expenseAddButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        expenseAddButton.setMnemonic('a');
        expenseAddButton.setText("Add");
        expenseAddButton.setToolTipText("Click to add expense");

        expenseUpdateButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        expenseUpdateButton.setMnemonic('u');
        expenseUpdateButton.setText("Update");
        expenseUpdateButton.setToolTipText("Click to update expense amount");

        expenseDeleteButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        expenseDeleteButton.setMnemonic('d');
        expenseDeleteButton.setText("Delete");
        expenseDeleteButton.setToolTipText("Delete selected expense");

        expenseExitButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        expenseExitButton.setMnemonic('x');
        expenseExitButton.setText("Exit");
        expenseExitButton.setToolTipText("Click to exit program");

        expensesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(expensesTable);

        expenseUpdateIncomeButton.setMnemonic('i');
        expenseUpdateIncomeButton.setText("Update Income");
        expenseUpdateIncomeButton.setToolTipText("Updates your monthly income");

        expenseIncomeTextField.setToolTipText("This is where you put your monthly income");

        expenseIncomeLabel.setDisplayedMnemonic('I');
        expenseIncomeLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        expenseIncomeLabel.setText("Monthly Take Home Income");
        expenseIncomeLabel.setToolTipText("Enter your monthly income");

        javax.swing.GroupLayout expensePanelLayout = new javax.swing.GroupLayout(expensePanel);
        expensePanel.setLayout(expensePanelLayout);
        expensePanelLayout.setHorizontalGroup(
            expensePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(expensePanelLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(expensePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(expenseAddButton)
                    .addComponent(expenseAmountLabel)
                    .addComponent(expenseNameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(expensePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(expenseNameCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(expenseAmountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(expensePanelLayout.createSequentialGroup()
                        .addGroup(expensePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(expenseExitButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(expenseUpdateButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(expenseDeleteButton)))
                .addGap(10, 10, 10)
                .addGroup(expensePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(expensePanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 43, Short.MAX_VALUE))
                    .addGroup(expensePanelLayout.createSequentialGroup()
                        .addGroup(expensePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(expenseIncomeLabel)
                            .addGroup(expensePanelLayout.createSequentialGroup()
                                .addComponent(expenseIncomeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(expenseUpdateIncomeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        expensePanelLayout.setVerticalGroup(
            expensePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(expensePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(expenseIncomeLabel)
                .addGap(10, 10, 10)
                .addGroup(expensePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(expenseIncomeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(expenseUpdateIncomeButton))
                .addGroup(expensePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(expensePanelLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(expensePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(expenseNameCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(expenseNameLabel))
                        .addGap(18, 18, 18)
                        .addGroup(expensePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(expenseAmountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(expenseAmountLabel))
                        .addGap(18, 18, 18)
                        .addGroup(expensePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(expenseUpdateButton)
                            .addComponent(expenseAddButton)
                            .addComponent(expenseDeleteButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(expenseExitButton)
                        .addGap(209, 209, 209))
                    .addGroup(expensePanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(52, Short.MAX_VALUE))))
        );

        expenseAmountTextField.getAccessibleContext().setAccessibleParent(expenseAmountLabel);

        tabbedPane.addTab("Income & Expenses", expensePanel);

        loanNameLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        loanNameLabel.setText("Loan Type");

        loanAmountLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        loanAmountLabel.setText("Monthly Payment");

        loanRemainingLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        loanRemainingLabel.setText("Remaining Balance");

        loanInterestLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        loanInterestLabel.setText("Interest Rate");

        loanNameCombo.setModel(getLoans());
        loanNameCombo.setToolTipText("Select the loan you want to add");

        loanAmountTextField.setToolTipText("Enter the monthly payment amount for the selected loan");

        loanRemainingTextField.setToolTipText("Enter the remaining balance from the selected loan");

        loanInterestTextField.setToolTipText("Enter the interest rate of the selected loan");

        loansTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(loansTable);

        loanAddButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        loanAddButton.setMnemonic('a');
        loanAddButton.setText("Add");
        loanAddButton.setToolTipText("Click to add loan");

        loanUpdateButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        loanUpdateButton.setMnemonic('u');
        loanUpdateButton.setText("Update");
        loanUpdateButton.setToolTipText("Click to update loans payment, balance, and/or interest rate");

        loanDeleteButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        loanDeleteButton.setMnemonic('d');
        loanDeleteButton.setText("Delete");
        loanDeleteButton.setToolTipText("Click to delete selected loan");

        loanExitButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        loanExitButton.setMnemonic('x');
        loanExitButton.setText("Exit");
        loanExitButton.setToolTipText("Click to exit program");

        javax.swing.GroupLayout loanPanelLayout = new javax.swing.GroupLayout(loanPanel);
        loanPanel.setLayout(loanPanelLayout);
        loanPanelLayout.setHorizontalGroup(
            loanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loanPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(loanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(loanPanelLayout.createSequentialGroup()
                        .addGroup(loanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(loanRemainingLabel)
                            .addComponent(loanInterestLabel))
                        .addGap(18, 18, 18)
                        .addGroup(loanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(loanNameCombo, 0, 125, Short.MAX_VALUE)
                            .addComponent(loanAmountTextField)
                            .addComponent(loanRemainingTextField)
                            .addComponent(loanInterestTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(loanAmountLabel)
                    .addComponent(loanNameLabel)
                    .addGroup(loanPanelLayout.createSequentialGroup()
                        .addComponent(loanAddButton)
                        .addGap(18, 18, 18)
                        .addGroup(loanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(loanExitButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(loanUpdateButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(loanDeleteButton)))
                .addGap(31, 31, 31)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        loanPanelLayout.setVerticalGroup(
            loanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loanPanelLayout.createSequentialGroup()
                .addGroup(loanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(loanPanelLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(loanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(loanNameLabel)
                            .addComponent(loanNameCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(loanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(loanAmountLabel)
                            .addComponent(loanAmountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(loanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(loanRemainingLabel)
                            .addComponent(loanRemainingTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(loanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(loanInterestLabel)
                            .addComponent(loanInterestTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(loanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(loanAddButton)
                            .addComponent(loanUpdateButton)
                            .addComponent(loanDeleteButton))
                        .addGap(97, 97, 97)
                        .addComponent(loanExitButton))
                    .addGroup(loanPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(106, Short.MAX_VALUE))
        );

        loanNameCombo.getAccessibleContext().setAccessibleParent(loanNameLabel);
        loanAmountTextField.getAccessibleContext().setAccessibleParent(loanAmountLabel);
        loanRemainingTextField.getAccessibleContext().setAccessibleParent(loanRemainingLabel);
        loanInterestTextField.getAccessibleContext().setAccessibleParent(loanInterestLabel);

        tabbedPane.addTab("Loans", loanPanel);

        analysisExpenseButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        analysisExpenseButton.setMnemonic('e');
        analysisExpenseButton.setText("Expense Analysis");
        analysisExpenseButton.setToolTipText("Click to run the expense analysis report");

        analysisSnowballButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        analysisSnowballButton.setMnemonic('d');
        analysisSnowballButton.setText("Debt Snowball");
        analysisSnowballButton.setToolTipText("click to run the debt snowball report");

        analysisExitButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        analysisExitButton.setMnemonic('x');
        analysisExitButton.setText("Exit");
        analysisExitButton.setToolTipText("Click to exit program");

        analysisTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        analysis.setViewportView(analysisTable);

        analysisAdditionalLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        analysisAdditionalLabel.setText("Additional Payment for Debt Snowball");

        analysisAdditionalTextField.setToolTipText("Enter the additional amount of money you want to apply to your smallest loan every month");

        javax.swing.GroupLayout analysisPanelLayout = new javax.swing.GroupLayout(analysisPanel);
        analysisPanel.setLayout(analysisPanelLayout);
        analysisPanelLayout.setHorizontalGroup(
            analysisPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(analysisPanelLayout.createSequentialGroup()
                .addGroup(analysisPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(analysisPanelLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(analysisPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(analysisExitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(analysisExpenseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(analysisPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(analysisAdditionalTextField, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(analysisSnowballButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))))
                    .addGroup(analysisPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(analysisAdditionalLabel)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(analysis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70))
        );
        analysisPanelLayout.setVerticalGroup(
            analysisPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(analysisPanelLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(analysisPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(analysisPanelLayout.createSequentialGroup()
                        .addComponent(analysisExpenseButton)
                        .addGap(18, 18, 18)
                        .addComponent(analysisAdditionalLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(analysisAdditionalTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(analysisSnowballButton)
                        .addGap(89, 89, 89)
                        .addComponent(analysisExitButton))
                    .addComponent(analysis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(74, Short.MAX_VALUE))
        );

        analysisAdditionalTextField.getAccessibleContext().setAccessibleParent(analysisAdditionalLabel);

        tabbedPane.addTab("Analysis", analysisPanel);

        getContentPane().add(tabbedPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 580));
        tabbedPane.getAccessibleContext().setAccessibleName("User tab");

        fileMenu.setMnemonic('f');
        fileMenu.setText("File");
        fileMenu.setToolTipText("File menu to exit program");

        exitMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, 0));
        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("Exit");
        exitMenuItem.setToolTipText("Exit the program");
        fileMenu.add(exitMenuItem);

        fileMenuBar.add(fileMenu);

        helpMenu.setMnemonic('h');
        helpMenu.setText("Help");
        helpMenu.setToolTipText("Help menu");

        expenseMenuItem.setMnemonic('e');
        expenseMenuItem.setText("Expense Analysis");
        expenseMenuItem.setToolTipText("Explains the expense analysis button");
        helpMenu.add(expenseMenuItem);

        snowballMenuItem.setMnemonic('s');
        snowballMenuItem.setText("Snowball Analysis");
        snowballMenuItem.setToolTipText("Explains the snowball analysis button");
        helpMenu.add(snowballMenuItem);

        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setText("About");
        aboutMenuItem.setToolTipText("Information about the creation of this tool");
        helpMenu.add(aboutMenuItem);

        fileMenuBar.add(helpMenu);

        setJMenuBar(fileMenuBar);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
	/* Set the Nimbus look and feel */
	//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
	/* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
	 */
	try {
	    for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
		if ("Nimbus".equals(info.getName())) {
		    javax.swing.UIManager.setLookAndFeel(info.getClassName());
		    break;
		}
	    }
	} catch (ClassNotFoundException ex) {
	    java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (InstantiationException ex) {
	    java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (IllegalAccessException ex) {
	    java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	} catch (javax.swing.UnsupportedLookAndFeelException ex) {
	    java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	}
	//</editor-fold>

	/* Create and display the form */
	java.awt.EventQueue.invokeLater(new Runnable() {
	    public void run() {
		new MainUI().setVisible(true);
	    }
	});
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JScrollPane analysis;
    private javax.swing.JLabel analysisAdditionalLabel;
    private javax.swing.JTextField analysisAdditionalTextField;
    private javax.swing.JButton analysisExitButton;
    private javax.swing.JButton analysisExpenseButton;
    private javax.swing.JPanel analysisPanel;
    private javax.swing.JButton analysisSnowballButton;
    private javax.swing.JTable analysisTable;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JButton expenseAddButton;
    private javax.swing.JLabel expenseAmountLabel;
    private javax.swing.JTextField expenseAmountTextField;
    private javax.swing.JButton expenseDeleteButton;
    private javax.swing.JButton expenseExitButton;
    private javax.swing.JLabel expenseIncomeLabel;
    private javax.swing.JTextField expenseIncomeTextField;
    private javax.swing.JMenuItem expenseMenuItem;
    private javax.swing.JComboBox<String> expenseNameCombo;
    private javax.swing.JLabel expenseNameLabel;
    private javax.swing.JPanel expensePanel;
    private javax.swing.JButton expenseUpdateButton;
    private javax.swing.JButton expenseUpdateIncomeButton;
    private javax.swing.JTable expensesTable;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuBar fileMenuBar;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton loanAddButton;
    private javax.swing.JLabel loanAmountLabel;
    private javax.swing.JTextField loanAmountTextField;
    private javax.swing.JButton loanDeleteButton;
    private javax.swing.JButton loanExitButton;
    private javax.swing.JLabel loanInterestLabel;
    private javax.swing.JTextField loanInterestTextField;
    private javax.swing.JComboBox<String> loanNameCombo;
    private javax.swing.JLabel loanNameLabel;
    private javax.swing.JPanel loanPanel;
    private javax.swing.JLabel loanRemainingLabel;
    private javax.swing.JTextField loanRemainingTextField;
    private javax.swing.JButton loanUpdateButton;
    private javax.swing.JTable loansTable;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JMenuItem snowballMenuItem;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JButton userCreateButton;
    private javax.swing.JButton userExitButton;
    private javax.swing.JButton userLoginButton;
    private javax.swing.JPanel userPanel;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JTextField usernameTextField;
    // End of variables declaration//GEN-END:variables

    /**
     * getters and setters for all fields
     */
    
    public JTextField getUsernameTextField() {
	return usernameTextField;
    }

    public void setUsernameTextField(String str) {
	this.usernameTextField.setText(str);
    }

    public JPasswordField getPasswordField() {
	return passwordField;
    }

    public void setPasswordField(String str) {
	passwordField.setText(str);
    }

    public int getTabbedPane() {
	return tabbedPane.getSelectedIndex();
    }

    public void setTabbedPane(int tab) {
	tabbedPane.setSelectedIndex(tab);
    }

    public void setTabbedPaneBool(int tab, boolean bool) {
	tabbedPane.setEnabledAt(tab, bool);
    }

    public JTextField getAnalysisAdditionalTextField() {
	return analysisAdditionalTextField;
    }

    public void setAnalysisAdditionalTextField(String str) {
	analysisAdditionalTextField.setText(str);
    }

    public JComboBox<String> getExpenseNameCombo() {
	return expenseNameCombo;
    }

    public void setExpenseNameCombo(int index) {
	expenseNameCombo.setSelectedIndex(index);
    }

    public JTextField getExpenseAmountTextField() {
	return expenseAmountTextField;
    }

    public void setExpenseAmountTextField(String str) {
	expenseAmountTextField.setText(str);
    }

    public JTextField getExpenseIncomeTextField() {
	return expenseIncomeTextField;
    }

    public void setExpenseIncomeTextField(String str) {
	expenseIncomeTextField.setText(str);
    }

    public JTable getExpensesTable() {
	return expensesTable;
    }

    public void setExpensesTableModel(ExpenseTable expenseModel) {
	expensesTable.setModel(expenseModel);
    }

    public void setAnalysisTableModel(ExpenditureTable expenditureModel) {
	analysisTable.setModel(expenditureModel);
    }

    public void setExpensesTableColRow(boolean col, boolean row) {
	expensesTable.setColumnSelectionAllowed(col);
	expensesTable.setRowSelectionAllowed(row);
    }

    public void setAnalysisTableColRow(boolean col, boolean row) {
	analysisTable.setColumnSelectionAllowed(col);
	analysisTable.setRowSelectionAllowed(row);
    }

    public JTable getLoansTable() {
	return loansTable;
    }

    public void setLoansTableModel(LoanTable loanModel) {
	loansTable.setModel(loanModel);
    }

    public void setLoansTableColRow(boolean col, boolean row) {
	loansTable.setColumnSelectionAllowed(col);
	loansTable.setRowSelectionAllowed(row);
    }

    public JTextField getLoanAmountTextField() {
	return loanAmountTextField;
    }

    public void setLoanAmountTextField(String str) {
	loanAmountTextField.setText(str);
    }

    public JTextField getLoanInterestTextField() {
	return loanInterestTextField;
    }

    public void setLoanInterestTextField(String str) {
	loanInterestTextField.setText(str);
    }

    public JComboBox<String> getLoanNameCombo() {
	return loanNameCombo;
    }

    public void setLoanNameCombo(int index) {
	loanNameCombo.setSelectedIndex(index);
    }

    public JTextField getLoanRemainingTextField() {
	return loanRemainingTextField;
    }

    public void setLoanRemainingTextField(String str) {
	loanRemainingTextField.setText(str);
    }

}
