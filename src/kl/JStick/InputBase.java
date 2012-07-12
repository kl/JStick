//
// The base class for MouseInput and KeyboardInput.
// Has a static member Robot robot which is used to send mouse/keyboard input.
//

package kl.JStick;

import java.awt.AWTException;
import java.awt.Robot;

public class InputBase {

    static Robot robot;

    static {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}
