package com.eventdriven.poc.framework;

public interface Channel<M extends Message> {
    void dispatch(M message);
}
