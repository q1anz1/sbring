package sbringframwork.test.ioc.bean;

import lombok.Data;
import sbringframwork.context.event.events.ApplicationEvent;

/**
 *
 */
public class MyEvent extends ApplicationEvent {
    private Long id;

    private String message;

    public MyEvent(Object source, Long id, String message) {
        super(source);
        this.id = id;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

}
