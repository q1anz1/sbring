package sbringframwork.context.event;

import sbringframwork.context.event.events.ApplicationEvent;

/**
 * 事件发布接口。
 */
public interface ApplicationEventPublisher {
    /**
     * Notify all listeners registered with this application of an application
     * event. Events may be framework events (such as RequestHandledEvent)
     * or application-specific events.
     * @param event the event to publish
     */
    void publishEvent(ApplicationEvent event);
}
