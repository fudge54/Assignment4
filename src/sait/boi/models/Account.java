package sait.boi.models;

/**
 * Represents an Account record.
 * @author Nick Hamnett
 * @version Aug 7, 2021
 */
public class Account {
    private long cardNumber;

    private int pin;

    /**
     * Initializes Account object
     * @param cardNumber Card number
     * @param pin PIN
     */
    public Account(long cardNumber, int pin) {
        this.cardNumber = cardNumber;
        this.pin = pin;
    }

    /**
     * Gets the card number
     * @return Card number
     */
    public long getCardNumber() {
        return cardNumber;
    }

    /**
     * Gets the PIN
     * @return Personal Identification Number
     */
    public int getPin() {
        return pin;
    }

    @Override
    public String toString() {
        String formatted = String.format("Card #: %d, PIN: %d", this.cardNumber, this.pin);
        return formatted;
    }
}
