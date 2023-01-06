package com.mvc.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.ServletRequestHandledEvent;

/**
 * @author tyb
 * @Description
 * @create 2021-07-29 18:52
 */
@Component
public class MyEvent implements ApplicationListener<ServletRequestHandledEvent> {
    @Override
    public void onApplicationEvent(ServletRequestHandledEvent event) {
        System.out.println("url :" + event.getRequestUrl() +" time: " + event.getProcessingTimeMillis());
    }
}
