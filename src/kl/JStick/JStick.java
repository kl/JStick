package kl.JStick;

import java.util.ArrayList;

import net.java.games.input.Controller;
import net.java.games.input.Event;

public class JStick {

    public static void main(String[] args) throws Exception {

        ControllerHandler controllerHandler = new ControllerHandler();
        EventPoller eventPoller = new EventPoller();
        MouseHandler mouseHandler = new MouseHandler();

        ArrayList<Controller> controllers = controllerHandler.getControllers();

        while (true) {

            for (Controller controller : controllers) {

                ArrayList<Event> events = eventPoller.getEvents(controller);
                mouseHandler.updateMouse(events);

                Thread.sleep(10);

            }
        }
    }

}

