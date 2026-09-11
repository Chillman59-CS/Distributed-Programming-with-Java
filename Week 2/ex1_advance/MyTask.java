package ex1_advance;

import javax.swing.*;

public class MyTask extends SwingWorker<Integer, Integer> {
    private JLabel label;

    // Constructor to initialize the JLabel
    public MyTask(JLabel label) {
        this.label = label;
    }

    @Override
    // This method is executed in a background thread and performs the long-running task.
    protected Integer doInBackground() throws Exception {
        int total = 0;
        int progress = 0;

        for (int i=0; i <= 1000; i+=10) {
            total += i;
            publish(total);

            progress ++;
            setProgress(progress);
            Thread.sleep(50);
        }
        return total;
    }

    @Override
    // This method is executed in the Event Dispatch Thread (EDT) and updates the JLabel with the progress.
    protected void process(java.util.List<Integer> chunks) {
        super.process(chunks);

        int value = chunks.get(chunks.size() - 1);
        label.setText("Progress: " + value);
    }

    @Override
    // This method is executed in the Event Dispatch Thread (EDT) after the background task is completed.
    protected void done() {
        super.done();
    }
}
