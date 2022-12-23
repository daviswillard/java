package day03.ex03;

public class DataObject {
	private final String[] linksPool;
	static int i = 0;

	public DataObject(String[] linksPool) {
		this.linksPool = linksPool;
	}

	private synchronized boolean isAtEnd() {
		return i == linksPool.length || linksPool[i] == null;
	}

	public String getLink() {
		if (isAtEnd()) {
			return "";
		}
		synchronized (linksPool) {
			return linksPool[i++];
		}
	}
}
