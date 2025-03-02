package sbringframwork.beans.factory;

/**
 * 一次性Bean，为了销毁这个操作。
 */
public interface DisposableBean {
    void destroy() throws Exception;
}
