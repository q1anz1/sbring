package sbringframwork.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * 获取资源接口。
 */
public interface Resource {

    InputStream getInputStream() throws IOException;
}
