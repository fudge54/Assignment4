package sait.boi.migrations;

import java.io.*;
import java.sql.SQLException;

import sait.boi.drivers.MySQLDriver;
import sait.boi.models.Transaction;

/**
 * Populates the transactions table from the supplied transactions random access
 * file.
 * 
 * @author xx
 *
 */
public class MigrateTransactionsTable {
	public static void main(String[] args) {
		// Write migration code here until all JUnit test cases pass.
		MySQLDriver driver = new MySQLDriver();
		try {
			driver.connect();

			RandomAccessFile raf = new RandomAccessFile("res/transactions.bin", "r");
			int length = 0;
			for (int i = 0; i < raf.length(); i += length) {
				long cardNum = raf.readLong();
				char type = raf.readChar();
				double amount = raf.readDouble();
				String date = raf.readUTF();
				length = 8 + 1 + (Double.toString(amount).length()) + (date.length());
				System.out.println("Inserting transaction to account: " + cardNum);
				Transaction transaction = new Transaction(cardNum, type, amount, date);
				driver.insertTransaction(transaction);
			}
			raf.close();
			driver.disconnect();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
