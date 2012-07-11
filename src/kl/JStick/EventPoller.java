//
// EventPoller is a class that is used to extract events from a controller.
// A jinput event gets fired whenever a button, trigger, stick etc changes value on the
// controller. This means that when you press an release a button two events are generated,
// one when the button goes from 'off' to 'on', and another when it goes back to 'off'.
//

package kl.JStick;

import net.java.games.input.Controller;
import net.java.games.input.Event;
import net.java.games.input.EventQueue;

import java.util.ArrayList;
import java.util.List;

public class EventPoller {

    //
    // Polls the specified controller and returns a list of generated events.
    //
    public static List<Event> getEvents(Controller controller) {
        controller.poll();

        List<Event> events = new ArrayList<>();
        EventQueue eventQueue = controller.getEventQueue();

        while (true) {
            Event event = new Event();
            boolean more = eventQueue.getNextEvent(event);  // writes event data to object.
            if (!more) {
                break;
            } else {
                events.add(event);
            }
        }
        return events;
    }
}
