package hankscapstone.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Proper format for expenditure table in the analysis tab
 *
 * @author Bryant Hanks
 * @version 1.0 2023-04-26
 */
public class ExpenditureTable extends AbstractTableModel {

    private List<Expense> expenses;
    private final String[] columnNames = {"Username", "Name", "Amount"};

    /**
     * Default constructor
     */
    public ExpenditureTable() {
	this.expenses = null;
    }

    /**
     *
     * @param exp list of expenses and loans
     */
    public void setExpenses(List<Expense> exp) {
	this.expenses = exp;
    }

    public void clearAll() {

    }

    /**
     * Get the total count of the expenses table
     *
     * @return int - number of expenses and loans
     */
    @Override
    public int getRowCount() {
	return expenses.size();
    }

    /**
     * get total number of columns
     *
     * @return int - number of columns
     */
    @Override
    public int getColumnCount() {
	return columnNames.length;
    }

    /**
     * return the value in the database base on row and column
     *
     * @param row - number that identifies what row in the database
     * @param column - number that identifies what column in the database
     * @return object - return the objects information based on column
     */
    @Override
    public Object getValueAt(int row, int column) {
	Expense e = new Expense() {
	};
	e = expenses.get(row);

	switch (column) {
	    case 0:
		return e.getUsername();
	    case 1:
		return e.getName();
	    case 2:
		return e.getMonthlyAmount();
	    case 3:
		return e.getRemainingBalance();
	    case 4:
		return e.getInterest();
	    default:
		return "";
	}
    }

    /**
     * Returns the name of the columns
     *
     * @param column int - column number to return
     * @return string - name of the column number
     */
    @Override
    public String getColumnName(int column) {
	return columnNames[column];
    }
}
