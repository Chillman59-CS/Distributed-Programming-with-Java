package ex1_advance;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

public class MyGUI extends JFrame {
    // Generated serial version UID for serialization
    private static final long serialVersionUID = 1L;

    // GUI components
    private JLabel label;
    private JProgressBar progressBar;
    private JButton button;

    public MyGUI() {
        // Set up the JFrame
        setTitle("SwingWorker Example");    // Set the title of the JFrame
        setSize(600, 200);  // Set the size of the JFrame
        setResizable(false);    // Make the JFrame non-resizable
        setLocationRelativeTo(null);    // Center the JFrame on the screen

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set the default close operation to exit the application

        Box b, b1, b2, b3;

        add(b = Box.createVerticalBox());   // Create a vertical box layout to hold the components
        b.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Add an empty border around the box for padding

        b.add(b1 = Box.createHorizontalBox());  // Create a horizontal box layout to hold the progress bar
        b.add(Box.createVerticalStrut(10)); // Add vertical spacing between components
        b1.add(progressBar = new JProgressBar());   // Create a progress bar and add it to the horizontal box
        progressBar.setStringPainted(true); // Set the progress bar to display a string representation of its progress

        b.add(b2 = Box.createHorizontalBox());  // Create another horizontal box layout to hold the label
        b.add(Box.createVerticalStrut(10)); // Add vertical spacing between components
        b2.add(label = new JLabel("Progress: 0"));  // Create a label to display the progress and add it to the horizontal box

        b.add(b3 = Box.createHorizontalBox());  // Create another horizontal box layout to hold the button
        b3.add(button = new JButton("Start"));  // Create a button to start the task and add it to the horizontal box

        button.addActionListener(e -> {
            // Action for the start button
            MyTask task = new MyTask(label);

            // Add a property change listener to the task to update the progress bar when the progress changes
            task.addPropertyChangeListener(new PropertyChangeListener() {
                @Override
                public void propertyChange(PropertyChangeEvent evt) {
                    if ("progress".equals(evt.getPropertyName())) {
                        progressBar.setValue((Integer) evt.getNewValue());
                    }
                }
            });

            task.execute(); // Execute the task in a background thread
        });
    }
}