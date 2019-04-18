package com.eventdriven.poc;


import com.eventdriven.poc.framework.Channel;
import com.eventdriven.poc.framework.EventRouter;
import com.eventdriven.poc.framework.Message;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class FireEventTest {

    @Test
    public void dispatchEventTest() {
        EventsDispatcher eventsDispatcher = new EventsDispatcher();
        eventsDispatcher.registerChannel( Event.class, new EventHandler() );
        eventsDispatcher.dispatch( new Event() );


    }

    private class Event implements Message {

        @Override
        public Class<? extends Message> getType() {
            return getClass();
        }
    }

    private class EventHandler implements Channel<Event> {

        @Override
        public void dispatch(Event message) {
            System.out.println( "event Type:" + message.getType() );
        }
    }

    private class EventsDispatcher implements EventRouter<Event> {
        private Map<Class<? extends Event>, EventHandler> handlersStore;

        public EventsDispatcher() {
            handlersStore = new HashMap<Class<? extends Event>, EventHandler>();
        }

        @Override
        public void registerChannel(Class<? extends Event> messageType, Channel<? extends Event> channel) {
            handlersStore.put( messageType, (EventHandler) channel );
        }


        @Override
        public void dispatch(Event message) {
            handlersStore.get( message.getType() ).dispatch( message );
        }
    }
}
