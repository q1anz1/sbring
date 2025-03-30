package sbringframwork.mvc;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 *
 */
public class HttpServletBean extends HttpServlet {
    @Override
    public final void init() throws ServletException {
        initServletBean();
    }

    /**
     * Subclasses may override this to perform custom initialization.
     * All bean properties of this servlet will have been set before this
     * method is invoked.
     * <p>This default implementation is empty.
     * @throws ServletException if subclass initialization fails
     */
    protected void initServletBean() throws ServletException {
    }
}
