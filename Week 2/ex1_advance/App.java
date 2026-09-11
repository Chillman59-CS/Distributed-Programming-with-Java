package ex1_advance;

import java.lang.reflect.InvocationTargetException;
import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) throws InvocationTargetException, InterruptedException {
        // Run the GUI in the Event Dispatch Thread (EDT)
        SwingUtilities.invokeAndWait(() -> {
            new MyGUI().setVisible(true);
        });
    }
}
