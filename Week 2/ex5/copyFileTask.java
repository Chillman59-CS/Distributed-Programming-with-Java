package ex5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.swing.*;

public class copyFileTask extends SwingWorker<Integer, Integer> {

	private File sourceFile;
	private File destFile;
	private JProgressBar progressBar;

	public copyFileTask(File sourceFile, File destFile, JProgressBar progressBar) {
		this.sourceFile = sourceFile;
		this.destFile = destFile;
		this.progressBar = progressBar;
	}

	@Override
	protected Integer doInBackground() throws Exception {
		InputStream fis = null;
		OutputStream fos = null;
		byte[] buffer = new byte[1024];
		int length;

		long totalBytesRead = 0L;
		long fileSize = sourceFile.length();

		try {
			fis = new FileInputStream(sourceFile);
			fos = new FileOutputStream(destFile);

			while ((length = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, length);

				totalBytesRead += length;

				int progress = (int) ((double) totalBytesRead / (double) fileSize * 100.0);

				publish(progress);
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (fis != null)
					fis.close();
				if (fos != null)
					fos.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	@Override
	protected void process(List<Integer> chunks) {
		int percent = chunks.get(chunks.size() - 1);
		progressBar.setValue(percent);
	}

	@Override
	protected void done() {
		try {
			JOptionPane.showMessageDialog(null, "Copy file thành công");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
