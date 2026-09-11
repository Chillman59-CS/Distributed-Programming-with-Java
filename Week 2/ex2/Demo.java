package ex2;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets; // Thêm import bảng mã UTF-8 [1]
import java.util.List;

public class Demo extends JFrame {
    private JTextArea textArea; // Text area to display the contents of the file
    private JFileChooser fileChooser;   // File chooser to select a file

    public Demo() {
        setTitle("Notepad Application");

        // Create a text area and add it to the frame
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Auto scroll to the bottom of the text area when new text is appended
        DefaultCaret caret = (DefaultCaret) textArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        add(scrollPane, BorderLayout.CENTER);   // Add the scroll pane to the center of the frame

        // Create a file chooser
        fileChooser = new JFileChooser();

        // Menu bar
        JMenuBar menuBar = new JMenuBar();  // Create a menu bar
        JMenu fileMenu = new JMenu("File"); // Create a "File" menu

        JMenuItem openItem = new JMenuItem("Open"); // Create an "Open" menu item
        JMenuItem exitItem = new JMenuItem("Exit"); // Create an "Exit" menu item

        fileMenu.add(openItem); // Add the "Open" menu item to the "File" menu
        fileMenu.addSeparator();    // Add a separator between the "Open" and "Exit" menu items
        fileMenu.add(exitItem); // Add the "Exit" menu item to the "File" menu
        menuBar.add(fileMenu);  // Add the "File" menu to the menu bar
        setJMenuBar(menuBar);   // Set the menu bar for the frame

        // Action listener for the "Open" menu item
        openItem.addActionListener(e -> openFile());
        // Action listener for the "Exit" menu item
        exitItem.addActionListener(e -> System.exit(0));

        // Set the size of the frame and make it visible
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true); // Đã kích hoạt hiển thị tại đây
    }

    private void openFile() {
        int result = fileChooser.showOpenDialog(this);  // Show the file chooser dialog
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();  // Get the selected file
            textArea.setText("");

            // SwingWorker to read the file in a background thread
            SwingWorker<Void, String> worker = new SwingWorker<>() {
                @Override
                protected void process(List<String> chunks) {
                    for (String line : chunks) {
                        textArea.append(line);
                    }
                }

                @Override
                protected void done() {
                }

                @Override
                protected Void doInBackground() throws Exception {
                    try (BufferedReader reader = new BufferedReader(
                            new InputStreamReader(
                                    new FileInputStream(file), StandardCharsets.UTF_8
                            )
                    )) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            publish(line + "\n");
                        }
                    }
                    return null;
                }
            };

            worker.execute();
        }
    }

    public static void main(String[] args) {
        // Run the GUI in the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> new Demo()); // Chỉ cần khởi tạo đối tượng
    }
}