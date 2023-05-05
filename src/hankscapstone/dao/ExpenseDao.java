package hankscapstone.dao;

import java.util.List;

/**
 * DAO for update expenses and loans
 *
 * @author Bryant Hanks
 */
public interface ExpenseDao<T> {

    List<T> getAllExpenses(T item);

    List<T> getAll(T item);

    List<T> getAllLoans(T item);

    Boolean addExpense(T item);

    Boolean updateLoan(T item);

    Boolean addLoan(T item);

    Boolean updateExpense(T item);

    Boolean deleteExpense(T item);
}
