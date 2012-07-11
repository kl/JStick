//
// ControllerHandler keeps a list of all the game controllers (gamepads and joysticks)
// that jinput finds on the system.
//

package kl.JStick;

import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

import java.util.HashSet;
import java.util.Set;

public class ControllerHandler {

    private Set<Controller> controllers;

    public Set<Controller> getControllers() {
        return controllers;
    }

    public ControllerHandler() {
        controllers = getControllerList();
    }

    private Set<Controller> getControllerList() {
        Controller[] all = ControllerEnvironment.getDefaultEnvironment().getControllers();
        Set<Controller> pads = new HashSet<>();

        for (Controller controller : all) {
            Controller.Type type = controller.getType();
            if (type == Controller.Type.GAMEPAD || type == Controller.Type.STICK)
                pads.add(controller);
        }
        return pads;
    }
}
