package ex00;

public class MyThread extends Thread {

	private final int	count;
	private final String name;

	MyThread(String name, int count) {
		super(name);
		this.count = count;
		this.name = name;
	}

	public void	run() {
		try {
			for (int i = 0; i < count; i++) {
				System.out.println(name);
				sleep(1);
			}
		} catch (InterruptedException exc) {
			System.err.println("Thread " + name + " has been interrupted");
		}
	}
}
