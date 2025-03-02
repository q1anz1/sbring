package sbringframwork.context.event;

import sbringframwork.context.event.events.ApplicationEvent;

import java.util.EventListener;

/**
 *
 */
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {
    /**
     * Handle an application event.
     * @param event the event to respond to
     */
    void onApplicationEvent(E event);
}
