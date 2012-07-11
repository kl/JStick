package kl.JStick;

import java.util.List;
import java.util.Set;

import net.java.games.input.Controller;
import net.java.games.input.Event;

public class JStick {

    public static void main(String[] args) throws Exception {

        ControllerHandler controllerHandler = new ControllerHandler();
        MouseHandler mouseHandler = new MouseHandler();

        Set<Controller> controllers = controllerHandler.getControllers();

        while (true) {

            for (Controller controller : controllers) {

                List<Event> events = EventPoller.getEvents(controller);
                mouseHandler.updateMouse(events);

                Thread.sleep(10);
            }
        }
    }

}

