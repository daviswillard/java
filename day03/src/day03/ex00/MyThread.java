package day03.ex00;

public class MyThread extends Thread {

	private final int count;
	private final String name;

	MyThread(String name, int count) {
		this.count = count;
		this.name = name;
	}

	public void	run() {
		for (int i = 0; i < count; i++) {
			System.out.println(name);
		}
	}
}
