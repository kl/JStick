package kl.JStick;

import net.java.games.input.Controller;
import net.java.games.input.Event;
import net.java.games.input.EventQueue;

import java.util.ArrayList;

public class EventPoller {

    public ArrayList<Event> getEvents(Controller controller) {
        controller.poll();

        ArrayList<Event> events = new ArrayList<>();
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
