package kl.JStick;

import net.java.games.input.Component;
import net.java.games.input.Event;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.List;

public class MouseHandler {
    public enum MouseButton { LEFT, RIGHT, MIDDLE }
    public enum Pad { LEFT_TRIGGER_PRESS, RIGHT_TRIGGER_PRESS }

    private Robot robot;

    private int changeX, changeY, scrollY   = 0;

    private final int X_FACTOR              = 6;
    private final int Y_FACTOR              = 6;
    private final int SCROLL_FACTOR         = 1;
    private final int MOUSE_SLEEP           = 5;
    private final int SCROLL_SLEEP          = 40;

    private Thread mouseUpdater;
    private Thread scrollUpdater;

    public MouseHandler() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            System.out.println("Error: Could not create java.awt.Robot object:");
            e.printStackTrace();
        }

        mouseUpdater = new Thread(new UpdateMousePos());
        scrollUpdater = new Thread(new UpdateScrollWheel());

        mouseUpdater.start();
        scrollUpdater.start();
    }

    public void updateMouse(List<Event> events) throws InterruptedException {
        for (Event event : events) {
            handleEvent(event);
        }
    }

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
            case "6":   // Left trigger press
                updateHotkey(Pad.LEFT_TRIGGER_PRESS, event);
                break;
            case "7":   // Right trigger press
                updateHotkey(Pad.RIGHT_TRIGGER_PRESS, event);
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
