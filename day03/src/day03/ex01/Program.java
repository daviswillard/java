package day03.ex01;

class Printer {

	static int flag = 0;

	public synchronized void print(String message, int i) throws InterruptedException {
		while (message.equals("Egg") && flag == 1 || message.equals("Hen") && flag == 0) {
			wait();
		}
		notify();

		System.out.println(message);
		flag += i;
	}
}

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
		int count;

		if (args.length < 1 || !args[0].startsWith("--count=")) {
			count = 20;
		} else {
			count = parseArg(args[0]);
		}
		Printer printer = new Printer();
		Runnable egg = () -> {
			for (int i = 0; i < count; i++) {
				try {
					printer.print("Egg", 1);
				} catch (InterruptedException e) {}
			}
		};
		Runnable hen = () -> {
			for (int i = 0; i < count; i++) {
				try {
					printer.print("Hen", -1);
				} catch (InterruptedException e) {}
			}
		};
		Thread thread1 = new Thread(egg);
		Thread thread2 = new Thread(hen);
		thread1.start();
		thread2.start();
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException ex) {}
	}
}
