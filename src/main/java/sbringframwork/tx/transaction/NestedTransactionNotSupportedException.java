package sbringframwork.tx.transaction;

/**
 *
 */
public class NestedTransactionNotSupportedException extends CannotCreateTransactionException{
    public NestedTransactionNotSupportedException(String message) {
        super(message);
    }

    public NestedTransactionNotSupportedException(String message, Throwable cause) {
        super(message, cause);
    }
}
