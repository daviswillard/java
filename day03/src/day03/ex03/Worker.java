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
							new FileOutputStream(homeDir + "/" + current.substring(current.lastIndexOf('/')))) {
				byte[] buffer = new byte[1024];
				int bytesRead;
				while ((bytesRead = stream.read(buffer, 0, 1024)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
				}
			} catch (IOException e) {
			}
		}
	}

	@Override
	public void run() {
		work();
	}
}
