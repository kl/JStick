//
// Handles mouse input.
//

package kl.JStick;

import net.java.games.input.Component;
import net.java.games.input.Event;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.InputEvent;
import java.util.List;

public class MouseInput extends InputBase {

    public enum MouseButton { LEFT, RIGHT, MIDDLE }     // represents different mouse buttons.

    // These variables are updated with the values of the analog sticks.
    private int changeX, changeY, scrollY   = 0;

    private final int X_FACTOR          = 6;        //  Mouse x-axis multiplication
    private final int Y_FACTOR          = 6;        //  Mouse y-axis multiplication
    private final int SCROLL_FACTOR     = 1;        //  Scroll wheel multiplication
    private final int MOUSE_SLEEP       = 5;        //  How long the mouse update thread should sleep each loop
    private final int SCROLL_SLEEP      = 45;       //  How long the scroll update thread should sleep each loop

    private Thread mouseUpdater;    // Updates mouse movement
    private Thread scrollUpdater;   // Updates mouse scrolling

    public MouseInput() {

        mouseUpdater = new Thread(new UpdateMousePos());
        scrollUpdater = new Thread(new UpdateScrollWheel());

        mouseUpdater.start();
        scrollUpdater.start();
    }

    //
    // Takes a list of events from a controller and passes each event to handleEvent().
    //
    public void updateMouse(List<Event> events) {
        for (Event event : events) {
            handleEvent(event);
        }
    }

    //
    // Maps controller events to actions.
    //
    private void handleEvent(Event event) {
        Component comp = event.getComponent();
        Component.Identifier type = comp.getIdentifier();

        System.out.println("PRESSED: " + type.getName());

        switch(type.getName()) {
            case "x":
                changeX = Math.round(event.getValue() * X_FACTOR);
                break;
            case "y":
                changeY = Math.round(event.getValue() * Y_FACTOR);
                break;
            case "ry":
                scrollY = Math.round(event.getValue() * SCROLL_FACTOR);
                break;
            case "4":   // L1
                updateMouseButton(MouseButton.LEFT, event);
                break;
            case "5":   // R1
                updateMouseButton(MouseButton.RIGHT, event);
                break;
            case "11":  // Right stick button
                updateMouseButton(MouseButton.MIDDLE, event);
                break;
        }
    }

    //
    // Presses/releases the supplied mouse button depending on the value of the event.
    //
    private void updateMouseButton(MouseButton button, Event event) {
        int buttonCode = 0;
        switch(button) {
            case LEFT:
                buttonCode = InputEvent.BUTTON1_MASK;
                break;
            case RIGHT:
                buttonCode = InputEvent.BUTTON3_MASK;
                break;
            case MIDDLE:
                buttonCode = InputEvent.BUTTON2_MASK;
                break;
        }

        if (event.getValue() == 1.0f) {
            robot.mousePress(buttonCode);
        } else {
            robot.mouseRelease(buttonCode);
        }
    }

    //
    // Updates mouse position.
    //
    private class UpdateMousePos implements Runnable {

        public void run() {
            while (true) {
                if (changeX != 0 || changeY != 0) {
                    Point currentPos = MouseInfo.getPointerInfo().getLocation();
                    robot.mouseMove(currentPos.x + changeX, currentPos.y + changeY);
                }

                try {
                    Thread.sleep(MOUSE_SLEEP);
                } catch (InterruptedException e) {

                }
            }
        }
    }

    //
    // Updates scroll position.
    //
    private class UpdateScrollWheel implements Runnable {

        public void run() {
            while (true) {
                if (scrollY != 0) {
                    robot.mouseWheel(scrollY);
                }

                try {
                    Thread.sleep(SCROLL_SLEEP);
                } catch (InterruptedException e) {

                }
            }
        }
    }
}
