//
// ControllerHandler keeps a list of all the game controllers (gamepads and joysticks)
// that jinput finds on the system.
//

package kl.JStick;

import net.java.games.input.Controller;
import net.java.games.input.DirectAndRawInputEnvironmentPlugin;

import java.util.List;
import java.util.ArrayList;

public class ControllerHandler {

    private List<Controller> controllers;    // the internal List of game controllers found.

    public List<Controller> getControllers() {
        return controllers;
    }

    public ControllerHandler() {
        controllers = getControllerList();
    }

    //
    // Rescans the system for added game controllers and adds them to the List.
    //
    // TODO: not really working very well. The mouse pointer gets floaty whenever this method is called. Fix this.
    //
    public boolean rescanControllers() {
        List<Controller> newControllers = getControllerList();

        if (newControllers.size() != controllers.size()) {
            controllers = newControllers;
            return true;
        } else {
            return false;
        }
    }

    //
    // Gets all controllers on the system (including mice an keyboards) and then filters
    // out all the game controllers which are returned in a List.
    //
    private List<Controller> getControllerList() {
        // The following line is needed in order to get the current controllers connected to
        // the system each time this method is called. See: http://www.java-gaming.org/index.php?topic=23964
        DirectAndRawInputEnvironmentPlugin plugin = new DirectAndRawInputEnvironmentPlugin();

        Controller[] all = plugin.getControllers();
        List<Controller> pads = new ArrayList<>();

        for (Controller controller : all) {
            Controller.Type type = controller.getType();
            if (type == Controller.Type.GAMEPAD || type == Controller.Type.STICK)
                pads.add(controller);
        }
        return pads;
    }
}
