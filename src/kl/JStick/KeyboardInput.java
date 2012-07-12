//
// Handles keyboard input.
//

package kl.JStick;

import net.java.games.input.Component;
import net.java.games.input.Event;

import java.awt.event.KeyEvent;
import java.util.List;

public class KeyboardInput extends InputBase {

    public enum Pad { LEFT_TRIGGER_PRESS, RIGHT_TRIGGER_PRESS }     // represents buttons on a gamepad.

    //
    // Takes a list of events from a controller and passes each event to handleEvent().
    //
    public void updateKeyboard(List<Event> events) {
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
            case "6":   // Left trigger press
                updateHotkey(Pad.LEFT_TRIGGER_PRESS, event);
                break;
            case "7":   // Right trigger press
                updateHotkey(Pad.RIGHT_TRIGGER_PRESS, event);
                break;
        }
    }

    //
    // Sends a keyboard key depending on the game controller button that was pressed.
    //
    private void updateHotkey(Pad button, Event event) {
        int keyCode = 0;
        switch(button) {
            case LEFT_TRIGGER_PRESS:
                keyCode = KeyEvent.VK_CONTROL;
                break;
            case RIGHT_TRIGGER_PRESS:
                keyCode = KeyEvent.VK_ALT;
                break;
        }

        if (event.getValue() == 1.0f) {
            robot.keyPress(keyCode);
        } else {
            robot.keyRelease(keyCode);
        }
    }
}
