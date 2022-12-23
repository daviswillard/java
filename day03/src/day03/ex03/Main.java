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
			if (! directory.exists() ) {
				directory.mkdir();
			}
			if (! directory.isDirectory()) {
				throw new RuntimeException("File " + downloadDir + " is not a directory!");
			}
		}

		private static String[] parseFile(String filePath) {

			String[] ret = new String[10];

			try (
					FileReader reader = new FileReader(filePath);
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
			} catch (IOException ex) {

			}
			return ret;
		}

		private static String[] parse(String filePath) {
			createDir();
			return parseFile(filePath);
		}
	}

	public static void main(String[] args) {
		String[] linksPool;

		if (args == null || args.length != 1) {
			throw new RuntimeException("No or too much arguments!");
		}
		linksPool = FileParser.parse(args[0]);
	}
}
