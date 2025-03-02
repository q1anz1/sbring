package sbringframwork.context.event.events;

import java.util.EventObject;

/**
 * 应用事件。
 */
public abstract class ApplicationEvent extends EventObject {
    public ApplicationEvent(Object source) {
        super(source);
    }
}
