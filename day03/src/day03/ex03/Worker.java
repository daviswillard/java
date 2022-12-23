package day03.ex03;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class Worker implements Runnable {

	private final String homeDir = System.getProperty("user.home");

	private final DataObject data;

	public Worker(DataObject data) {
		this.data = data;
	}

	private void work() {
		String current;
		while (!(current = data.getLink()).isEmpty()) {
			try (BufferedInputStream stream = new BufferedInputStream(new URL(current).openStream());
					FileOutputStream outputStream =
							new FileOutputStream(homeDir + "/download/" + current.substring(current.lastIndexOf('/') + 1))) {
				System.out.println(Thread.currentThread().getName() +
						" start download " +
						current.substring(current.lastIndexOf('/') + 1));
				byte[] buffer = new byte[1024];
				int bytesRead;
				while ((bytesRead = stream.read(buffer, 0, 1024)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
				}
				System.out.println(Thread.currentThread().getName() +
						" completed download " +
						current.substring(current.lastIndexOf('/') + 1));
			} catch (IOException e) {
				System.err.println("Thrown exception!\n" + e.getMessage());
			}
		}
	}

	@Override
	public void run() {
		work();
	}
}
