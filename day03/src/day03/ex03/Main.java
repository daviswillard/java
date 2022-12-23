package day03.ex03;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	private static class FileParser {
		private static final String downloadDir = System.getProperty("user.home") + "/download";

		private static void createDir() {
			File directory = new File(downloadDir);
			if (!directory.exists()) {
				if (!directory.mkdir()) {
					throw new RuntimeException("Couldn't create download directory");
				}
			}
			if (!directory.isDirectory()) {
				throw new RuntimeException("File " + downloadDir + " is not a directory!");
			}
		}

		private static String[] parseFile() {

			String[] ret = new String[10];

			try (
					FileReader reader = new FileReader("day03/ex03/files_urls.txt");
					BufferedReader bufferedReader = new BufferedReader(reader)
			) {
				int i = 0;
				while (bufferedReader.ready()) {
					ret[i++] = bufferedReader.readLine();
					if (i == ret.length) {
						i *= 2;
						String[] temp = new String[i];
						System.arraycopy(ret, 0, temp, 0, ret.length);
						ret = temp;
					}
				}
			} catch (IOException ignored) {

			}
			return ret;
		}

		private static String[] parse() {
			createDir();
			return parseFile();
		}
	}

	public static void main(String[] args) {
		String[] linksPool;
		int threadCount;

		if (args == null || args.length != 1 || !args[0].startsWith("--threadCount=")) {
			throw new RuntimeException("No or too much arguments!");
		}
		linksPool = FileParser.parse();
		DataObject dataObject = new DataObject(linksPool);
		threadCount = Integer.parseInt(args[0].substring(args[0].indexOf('=') + 1));
		Worker[] workers = new Worker[threadCount];
		for (int i = 0; i < workers.length; i++) {
			workers[i] = new Worker(dataObject);
		}
		Thread[] threads = new Thread[threadCount];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(workers[i]);
			threads[i].start();
		}
		try {
			for (Thread thread : threads) {
				thread.join();
			}
		} catch (InterruptedException ignored) {
		}
	}
}
