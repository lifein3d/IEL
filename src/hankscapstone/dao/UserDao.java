package hankscapstone.dao;

import java.util.List;

/**
 * DAO for updating user information
 *
 * @author Bryant Hanks
 */
public interface UserDao<T> {

    Boolean getUser(T item);

    Boolean addUser(T item);

    Boolean updateIncome(T item);

    int getIncome(T item);

}
