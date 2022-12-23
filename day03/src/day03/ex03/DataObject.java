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

	public LinkWithNumber getLink() {
		if (isAtEnd()) {
			return new LinkWithNumber("", -1);
		}
		synchronized (linksPool) {
			return new LinkWithNumber(linksPool[i], i++);
		}
	}
}
