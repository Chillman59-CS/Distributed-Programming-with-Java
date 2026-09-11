package ex1;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // 1. Khởi tạo Frame giao diện chính
            JFrame frame = new JFrame("Event Dispatcher Thread, SwingWorker");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 200);
            frame.setLocationRelativeTo(null);

            // 2. Khởi tạo các thành phần giao diện (Label, ProgressBar, Button)
            JLabel lbl = new JLabel("Chưa bắt đầu", SwingConstants.CENTER);
            JProgressBar progressBar = new JProgressBar(0, 100);
            progressBar.setStringPainted(true);
            progressBar.setPreferredSize(new Dimension(400, 100));
            progressBar.setFont(progressBar.getFont().deriveFont(Font.BOLD, 14f));

            JButton btnEDT = new JButton("EDT");
            JButton btnSwingWorker = new JButton("SwingWorker");

            // ================================================================
            // CÁCH 1: Chạy trực tiếp trên EDT Thread (Làm treo giao diện - UI treo)
            // ================================================================
            btnEDT.addActionListener(e -> {
                lbl.setText("Đang xử lý...");
                progressBar.setValue(0);
                for (int i = 0; i <= 100; i++) {
                    try {
                        Thread.sleep(50); // Giả lập tác vụ xử lý lâu
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    lbl.setText("Tiến trình: " + i + "%");
                    progressBar.setValue(i);
                }
                lbl.setText("Hoàn thành!");
            });

            // ================================================================
            // CÁCH 2: Dùng SwingWorker (Chạy bất đồng bộ dưới nền - UI mượt mà)
            // ================================================================
            btnSwingWorker.addActionListener(e -> {
                lbl.setText("Đang xử lý...");
                progressBar.setValue(0);

                SwingWorker<Void, Integer> worker = new SwingWorker<Void, Integer>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        for (int i = 0; i <= 100; i++) {
                            Thread.sleep(50); // Xử lý dưới luồng nền độc lập
                            publish(i);       // Gửi tạm dữ liệu tiến trình về luồng EDT
                        }
                        return null;
                    }

                    @Override
                    protected void process(List<Integer> chunks) {
                        // Nhận dữ liệu cập nhật liên tục để vẽ giao diện an toàn
                        int latest = chunks.get(chunks.size() - 1);
                        lbl.setText("Tiến trình: " + latest + "%");
                        progressBar.setValue(latest);
                    }

                    @Override
                    protected void done() {
                        // Tác vụ hoàn tất, cập nhật trạng thái cuối cùng
                        lbl.setText("Hoàn thành!");
                    }
                };

                worker.execute(); // Kích hoạt chạy luồng nền
            });

            // 3. Thiết lập bố cục panel chứa nút bấm
            JPanel panel = new JPanel();
            panel.add(btnEDT);
            panel.add(btnSwingWorker);

            // 4. Bố cục tổng thể của Frame và hiển thị
            frame.setLayout(new BorderLayout());
            frame.add(lbl, BorderLayout.WEST);
            frame.add(progressBar, BorderLayout.CENTER);
            frame.add(panel, BorderLayout.SOUTH);
            frame.setVisible(true);
        });
    }
}
