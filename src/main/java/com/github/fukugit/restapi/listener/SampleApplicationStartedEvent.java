package com.github.fukugit.restapi.listener;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class SampleApplicationStartedEvent implements ApplicationListener<ApplicationStartedEvent> {
  @Override
  public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
    System.out.println("ApplicationStartedEvent Listener is working...");
  }
}
