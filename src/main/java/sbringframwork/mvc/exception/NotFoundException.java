package sbringframwork.mvc.exception;

/**
 * 没找到映射路径对应的方法异常
 */
public class NotFoundException extends Exception{

    public NotFoundException(String message) {
        super(message);
    }
}
