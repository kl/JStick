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

    private Set<Controller> controllers;    // the internal set of game controllers found.

    public Set<Controller> getControllers() {
        return controllers;
    }

    public ControllerHandler() {
        controllers = getControllerSet();
    }

    //
    // Rescans the system for added game controllers and adds them to the set.
    //
    public boolean rescanControllers() {
        Set<Controller> newControllers = getControllerSet();
        return controllers.addAll(newControllers);
    }

    //
    // Gets all controllers on the system (including mice an keyboards) and then filters
    // out all the game controllers which are returned in a set.
    //
    private Set<Controller> getControllerSet() {
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
