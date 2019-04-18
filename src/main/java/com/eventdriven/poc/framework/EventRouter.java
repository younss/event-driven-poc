package com.eventdriven.poc.framework;

public interface EventRouter<M extends Message> {
    void registerChannel(Class<? extends M> messageType, Channel<? extends M> channel);

    void dispatch(M message);
}
