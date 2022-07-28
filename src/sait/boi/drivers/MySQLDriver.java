package sait.boi.drivers;

import sait.boi.contracts.*;
import sait.boi.models.*;

import java.sql.*;
import java.util.*;

/**
 * Class description: Driver for connecting to and accessing a MySQL or MariaDB database.
 *
 * Author: Nick Hamnett
 */
public class MySQLDriver implements DatabaseDriver {
	private static final String SERVER = "localhost";
	private static final int PORT = 3306;
	private static final String DATABASE = "bankonit";
	private static final String USERNAME = "cprg251";
	private static final String PASSWORD = "password";
	
	private Connection connection = null;
	
	/**
	 * Initializes the MySQLDriver
	 */
	public MySQLDriver() {

	}
	
	/* (non-Javadoc)
	 * @see drivers.DatabaseDriver#connect()
	 */
	@Override
	public void connect() throws SQLException, ClassNotFoundException {
		Class.forName("org.mariadb.jdbc.Driver");

		String dsn = this.getDsn();
		connection = DriverManager.getConnection(dsn);
	}
	
	/* (non-Javadoc)
	 * @see drivers.DatabaseDriver#disconnect()
	 */
	@Override
	public void disconnect() throws SQLException
	{
		if (connection != null && !connection.isClosed())
			connection.close();
	}
	
	@Override
	public ResultSet get(String query) throws SQLException
	{
		Statement statement = connection.createStatement();
		ResultSet results = statement.executeQuery(query);
	
		return results;
	}

	@Override
	public int update(String query) throws SQLException
	{
		Statement statement = connection.createStatement();
		int ret = statement.executeUpdate(query);
	
		return ret;
	}

	@Override
	public ArrayList<Account> findAccounts() throws SQLException {
		ArrayList<Account> accounts = new ArrayList<>();

		String sql = "SELECT * FROM accounts";
		ResultSet resultSet = this.get(sql);

		while (resultSet.next()) {
			long cardNumber = resultSet.getLong(1);
			int pin = resultSet.getInt(2);

			Account account = new Account(cardNumber, pin);
			accounts.add(account);
		}

		return accounts;
	}

	@Override
	public Account findAccount(long cardNumber) throws SQLException {
		String sql = String.format("SELECT * FROM accounts WHERE card_number = %d", cardNumber);
		ResultSet resultSet = this.get(sql);

		if (!resultSet.next())
			return null;

		int pin = resultSet.getInt(2);

		Account account = new Account(cardNumber, pin);

		return account;
	}
	
	@Override
	public int insertAccount(Account account) throws SQLException {
		// Build INSERT SQL statement
		String sql = String.format(
			"INSERT INTO accounts (card_number, pin) VALUES(%d, %d)",
			account.getCardNumber(), account.getPin()
		);

		return this.update(sql);
	}
	
	@Override
	public ArrayList<Transaction> findTransactions() throws SQLException {
		ArrayList<Transaction> transactions = new ArrayList<>();

		String sql = "SELECT * FROM transactions";
		ResultSet resultSet = this.get(sql);

		while (resultSet.next()) {
			long cardNumber = resultSet.getLong(1);
			char type = resultSet.getString(2).charAt(0);
			double amount = resultSet.getDouble(3);
			String dateTime = resultSet.getString(4);

			Transaction transaction = new Transaction(cardNumber, type, amount, dateTime);
			transactions.add(transaction);
		}

		return transactions;
	}

	@Override
	public ArrayList<Transaction> findTransactionsForCardNumber(long cardNumber) throws SQLException {
		ArrayList<Transaction> transactions = new ArrayList<>();

		String sql = String.format("SELECT * FROM transactions WHERE card_number = %d", cardNumber);
		ResultSet resultSet = this.get(sql);

		while (resultSet.next()) {
			char type = resultSet.getString(2).charAt(0);
			double amount = resultSet.getDouble(3);
			String dateTime = resultSet.getString(4);

			Transaction transaction = new Transaction(cardNumber, type, amount, dateTime);
			transactions.add(transaction);
		}

		return transactions;
	}
	
	@Override
	public int insertTransaction(Transaction transaction) throws SQLException {
		// Build INSERT SQL statement
		String sql = String.format(
			"INSERT INTO transactions (card_number, type, amount, datetime) VALUES(%d, '%c', %.2f, '%s')",
			transaction.getCardNumber(), transaction.getType(), transaction.getAmount(), transaction.getDateTime()
		);

		return this.update(sql);
	}

	/**
	 * Gets the data source name to connect to the database.
	 * @return DSN
	 */
	private String getDsn() {
		// Data source name is formatted as follows: jdbc:mariadb://localhost:3306/DB?user=root&password=myPassword
		String dataSourceName = String.format("jdbc:mariadb://%s:%d/%s?user=%s&password=%s", SERVER, PORT, DATABASE, USERNAME, PASSWORD);
		
		return dataSourceName;
	}
}
