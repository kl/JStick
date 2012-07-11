package kl.JStick;

import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

import java.util.ArrayList;
import java.util.List;

public class ControllerHandler {

    private List<Controller> controllers;

    public List<Controller> getControllers() {
        return controllers;
    }

    public ControllerHandler() {
        controllers = getControllerList();
    }

    private List<Controller> getControllerList() {
        Controller[] all = ControllerEnvironment.getDefaultEnvironment().getControllers();
        List<Controller> pads = new ArrayList<>();

        for (Controller controller : all) {
            Controller.Type type = controller.getType();
            if (type == Controller.Type.GAMEPAD || type == Controller.Type.STICK)
                pads.add(controller);
        }
        return pads;
    }
}
