package sbringframwork.beans.factory;

/**
 * 在属性设置后，执行Bean的初始化方法的接口。
 */
public interface InitializingBean {
    /**
     * Bean 处理了属性填充后调用
     */
    void afterPropertiesSet() throws Exception;
}
