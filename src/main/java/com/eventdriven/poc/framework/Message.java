package com.eventdriven.poc.framework;

public interface Message {

    Class<? extends Message> getType();
}
