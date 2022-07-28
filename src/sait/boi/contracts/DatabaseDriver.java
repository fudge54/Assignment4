package sait.boi.contracts;

import java.sql.*;
import java.util.*;

import sait.boi.models.*;

/**
 * Database driver contract.
 * @author Nick Hamnett
 *
 */
public interface DatabaseDriver {

	/**
	 * Connects to the database.
	 * @throws SQLException
	 */
	void connect() throws SQLException, ClassNotFoundException;

	/**
	 * Performs a retrieval from the database (ie: SELECT)
	 * @param query Query to send to database.
	 * @return Returns the results as a ResultSet
	 * @throws SQLException Thrown if problem performing query.
	 */
	ResultSet get(String query) throws SQLException;

	/**
	 * Performs an update query (UPDATE, DELETE, DROP, etc.) on the database.
	 * @param query Query to send to database.
	 * @return Number of rows modified.
	 * @throws SQLException
	 */
	int update(String query) throws SQLException;

	/**
	 * Finds all Account records
	 * @return ArrayList of Account objects
	 * @throws SQLException
	 */
	ArrayList<Account> findAccounts() throws SQLException;

	/**
	 * Finds a single account record with card number.
	 * @param cardNumber Card number to find
	 * @return Account object, otherwise null if not found.
	 * @throws SQLException
	 */
	Account findAccount(long cardNumber) throws SQLException;

	/**
	 * Finds all Transaction records
	 * @return ArrayList of Transaction objects
	 * @throws SQLException
	 */
	ArrayList<Transaction> findTransactions() throws SQLException;

	/**
	 * Finds a transaction(s) associated with card number.
	 * @param cardNumber Card number to find transactions for
	 * @return ArrayList of Transaction objects
	 * @throws SQLException
	 */
	ArrayList<Transaction> findTransactionsForCardNumber(long cardNumber) throws SQLException;

	/**
	 * Inserts a record into the accounts table
	 * @param account Account object
	 * @return 1 if account was inserted.
	 * @throws SQLException
	 */
	int insertAccount(Account account) throws SQLException;

	/**
	 * Inserts a record into the transactions table
	 * @param transaction Transaction object
	 * @throws SQLException
	 */
	int insertTransaction(Transaction transaction) throws SQLException;

	/**
	 * Disconnects from the database.
	 * @throws SQLException
	 */
	void disconnect() throws SQLException;

}