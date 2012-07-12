//
// This is the class that starts the JStick application.
//

package kl.JStick;

import java.util.List;

import net.java.games.input.Controller;
import net.java.games.input.Event;

public class JStick {

    public static void main(String[] args) {

        final ControllerHandler   controllerHandler     = new ControllerHandler();
        final MouseInput          mouseInput            = new MouseInput();
        final KeyboardInput       keyboardInput         = new KeyboardInput();

        Thread mainLoop = new Thread(new Runnable() {
            public void run() {

                List<Controller> controllers = controllerHandler.getControllers();

                while (true) {
                    for (Controller controller : controllers) {
                        List<Event> events = EventPoller.getEvents(controller);

                        mouseInput.updateMouse(events);
                        keyboardInput.updateKeyboard(events);

                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        mainLoop.start();
    }
}

