package kl.JStick;

import java.util.List;

import net.java.games.input.Controller;
import net.java.games.input.Event;

public class JStick {

    public static void main(String[] args) throws Exception {

        ControllerHandler controllerHandler = new ControllerHandler();
        EventPoller eventPoller = new EventPoller();
        MouseHandler mouseHandler = new MouseHandler();

        List<Controller> controllers = controllerHandler.getControllers();

        while (true) {

            for (Controller controller : controllers) {

                List<Event> events = eventPoller.getEvents(controller);
                mouseHandler.updateMouse(events);

                Thread.sleep(10);
            }
        }
    }

}

