package sbringframwork.aop;

/**
 * Aop异常。
 */
public class AopException extends Exception {
    public AopException(String message) {
        super(message);
    }

    public AopException(String message, Throwable cause) {
        super(message, cause);
    }
}
