package sbringframwork.test.ioc.bean;

import sbringframwork.context.event.ApplicationListener;

/**
 *
 */
public class MyEventListener implements ApplicationListener<MyEvent> {
    @Override
    public void onApplicationEvent(MyEvent event) {
        System.out.println("收到: " + event.getSource() + " 的消息");
        System.out.println("id: " + event.getId());
        System.out.println("消息为: " + event.getMessage());
    }
}
