package ex5;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.lang.reflect.InvocationTargetException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

public class CopyFile extends JFrame implements ActionListener {

	private JButton btnCopy;
	public JProgressBar progressBar;
	private Box b, b1, b2, b3, b4;
	private JTextField txtFromDir, txtToDir;

	public CopyFile() {
		setSize(400, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		b = Box.createVerticalBox();
		b.add(b1 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(5));
		b.add(b2 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(5));
		b.add(b3 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(5));
		b.add(b4 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(15));
		add(b);

		b1.setBorder(BorderFactory.createTitledBorder("From"));
		b1.add(txtFromDir = new JTextField());
		b2.setBorder(BorderFactory.createTitledBorder("To"));
		b2.add(txtToDir = new JTextField());
		b3.add(btnCopy = new JButton("Copy..."));
		b4.add(progressBar = new JProgressBar());
		progressBar.setStringPainted(true);

		txtFromDir.setText("E:\\Major Courses\\Lap Trinh Phan Tan Voi Cong Nghe Java\\TH\\Week2\\src\\ex5\\sample.txt");
		txtToDir.setText("E:\\Major Courses\\Lap Trinh Phan Tan Voi Cong Nghe Java\\TH\\Week2\\src\\ex5\\dest.txt");

		btnCopy.addActionListener(this);
	}

	public static void main(String[] args) throws InvocationTargetException, InterruptedException {
		new CopyFile().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnCopy)) {
			File fromDir = new File(txtFromDir.getText());
			File toDir = new File(txtToDir.getText());

			copyFileTask task = new copyFileTask(fromDir, toDir, progressBar);
			task.execute();
			task.addPropertyChangeListener((evt) -> {
				if ("progress".equals(evt.getPropertyName())) {
					progressBar.setValue((int) evt.getNewValue());
				}
			});
		}
	}
}