package ex01;

import javax.crypto.SealedObject;
import java.util.concurrent.Semaphore;
import java.util.function.IntToDoubleFunction;

public class MyThread extends Thread {
	private final Semaphore		mutexS;
	private final Semaphore		mutexE;
	private final Semaphore		mutexF;
	private final int			count;
	private final String		name;

	MyThread(String name, int count, Semaphore mutexS,
			 Semaphore mutexE, Semaphore mutexF) {
		this.mutexS = mutexS;
		this.mutexE = mutexE;
		this.mutexF = mutexF;
		this.count = count;
		this.name = name;
	}

	public void	run() {
		try {
			for (int i = 0; i < count; i++) {
				if (name.equals("Egg")) {
					mutexE.acquire();
					mutexS.acquire();
					System.out.println(name);
					mutexS.release();
					mutexF.release();
				} else {
					mutexF.acquire();
					mutexS.acquire();
					System.out.println(name);
					mutexS.release();
					mutexE.release();
				}
			}
		} catch (InterruptedException exc) {}
	}
}
