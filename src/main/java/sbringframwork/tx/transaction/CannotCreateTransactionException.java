package sbringframwork.tx.transaction;

/**
 *
 */
public class CannotCreateTransactionException extends TransactionException{


    public CannotCreateTransactionException(String message) {
        super(message);
    }

    public CannotCreateTransactionException(String message, Throwable cause) {
        super(message, cause);
    }


}
