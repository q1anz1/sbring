package sbringframwork.core.io;

/**
 * 资源加载器
 */
public interface ResourceLoader {

    /**
     * 假如是URL就加上此前缀。
     * */
    String CLASSPATH_URL_PREFIX = "classpath:";

    Resource getResource(String location);
}
