package sait.boi.models;

/**
 * Represents a Transaction record.
 * @author Nick Hamnett
 * @version Aug 7, 2021
 */
public class Transaction {
    public static final char TYPE_DEPOSIT = 'D';
    public static final char TYPE_WITHDRAW = 'W';

    private long cardNumber;

    private char type;

    private double amount;

    private String dateTime;

    /**
     * Initializes Transaction object.
     * @param cardNumber Card # associated with transaction
     * @param type Type (TYPE_DEPOSIT or TYPE_WITHDRAW)
     * @param amount Amount
     * @param dateTime Date/time transaction was performed
     */
    public Transaction(long cardNumber, char type, double amount, String dateTime) {
        this.cardNumber = cardNumber;
        this.type = type;
        this.amount = amount;
        this.dateTime = dateTime;
    }

    /**
     * Gets the card number
     * @return Card number
     */
    public long getCardNumber() {
        return cardNumber;
    }

    /**
     * Gets transaction type
     * @return TYPE_DEPOSIT or TYPE_WITHDRAW
     */
    public char getType() {
        return type;
    }

    /**
     * Gets the amount
     * @return Transaction amount
     */
    public double getAmount() {
        return amount;
    }
    
    /**
     * Gets the amount in pennies.
     * @return Amount in pennies.
     */
    public long getAmountPennies() {
    	return (long) (this.amount * 100);
    }

    /**
     * Gets the date/time the transaction was performed.
     * @return Date/time in format "YYYY-MM-DD hh:mm:ss"
     */
    public String getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        String formatted = String.format(
            "Card #: %d, Type: %c, Amount: %.2f, Date/time: %s",
            this.cardNumber,
            this.type,
            this.amount,
            this.dateTime
        );
        return formatted;
    }
}
