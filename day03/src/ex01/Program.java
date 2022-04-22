package ex01;

import java.util.concurrent.Semaphore;

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
		if (args.length != 1 || !args[0].startsWith("--count=")) {
			return ;
		}
		Semaphore mutexS = new Semaphore(1);
		Semaphore mutexE = new Semaphore(1);
		Semaphore mutexF = new Semaphore(1);

		int count = parseArg(args[0]);
		try {
			mutexF.acquire();
		} catch (InterruptedException exc) {}
		Thread t1 = new MyThread("Egg", count, mutexS, mutexE, mutexF);
		Thread t2 = new MyThread("Hen", count, mutexS, mutexE, mutexF);
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException exc) {
			System.err.println("One or both threads have been interrupted");
		}
	}
}
