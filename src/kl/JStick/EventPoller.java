package kl.JStick;

import net.java.games.input.Controller;
import net.java.games.input.Event;
import net.java.games.input.EventQueue;

import java.util.ArrayList;
import java.util.List;

public class EventPoller {

    public List<Event> getEvents(Controller controller) {
        controller.poll();

        List<Event> events = new ArrayList<>();
        EventQueue eventQueue = controller.getEventQueue();

        while (true) {
            Event event = new Event();
            boolean more = eventQueue.getNextEvent(event);
            if (!more) {
                break;
            } else {
                events.add(event);
            }
        }
        return events;
    }
}
