package kl.JStick;

import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

import java.util.ArrayList;

public class ControllerHandler {

    private ArrayList<Controller> controllers;

    public ArrayList<Controller> getControllers() {
        return controllers;
    }

    public ControllerHandler() {
        controllers = getControllerList();
    }

    private ArrayList<Controller> getControllerList() {
        Controller[] all = ControllerEnvironment.getDefaultEnvironment().getControllers();
        ArrayList<Controller> pads = new ArrayList<>();

        for (Controller controller : all) {
            Controller.Type type = controller.getType();
            if (type == Controller.Type.GAMEPAD || type == Controller.Type.STICK)
                pads.add(controller);
        }
        return pads;
    }
}
