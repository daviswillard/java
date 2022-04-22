package ex00;

public class Program {

	static int parseArg(String count) {
		int	ret;

		String sub = count.substring(count.indexOf('=') + 1);
		ret = Integer.parseInt(sub);
		if (ret <= 0) {
			return 50;
		}
		return ret;
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			return ;
		}
		int count = parseArg(args[0]);
		MyThread t1 = new MyThread("Egg", count);
		MyThread t2 = new MyThread("Hen", count);
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException exc) {
			System.err.println("One or both threads have been interrupted");
		}
		for (int i = 0; i < count; i++) {
			System.out.println("Human");
		}
	}
}
