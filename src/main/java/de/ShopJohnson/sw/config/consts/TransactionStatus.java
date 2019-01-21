package de.ShopJohnson.sw.config.consts;

/**
 * Possible Transaction Status texts
 */
public class TransactionStatus {
    public static String TRANSACTION_NOT_CONFIRMED = "Not payed";
    public static String TRANSACTION_DATA_CONFIRMED = "Account confirmed, not payed";
    public static String TRANSACTION_CONFIRMED = "Payed";
    public static String TRANSACTION_FAILED = "Failed";
    public static String COULD_NOT_REACH_PAY_JOHNSON = "Could not reach Pay Johnson";
}
