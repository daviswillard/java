package ex02;

class Result {
	private int	sum = 0;

	public void setSum(int sum) {
		this.sum += sum;
	}

	public int getSum() {
		return sum;
	}
}

public class MyThread extends Thread {
	private final int		name;
	private final int[]		arr;
	private final int		count;
	private int				sum = 0;
	private final Result	sumObj;

	MyThread(int name, int count, int[] array, Result sum) {
		this.name = name;
		this.count = count;
		arr = array;
		sumObj = sum;
	}

	public void	run() {
		int	chunk = arr.length / count;
		int	offset = chunk * name;

		if (name == count - 1) {
			for (int i = 0; i + offset < arr.length; i++) {
				sum += arr[offset + i];
			}
		} else {
			for (int i = 0; i < chunk; i++) {
				sum += arr[offset + i];
			}
		}
		if (name != count - 1) {
			System.out.println("Thread " + name + ": from " + offset + " to " +
					(offset + chunk) + " is " + sum);
		} else {
			System.out.println("Thread " + name + "; from " + offset + " to " +
					arr.length + " is " + sum);
		}
		synchronized (sumObj) {
			sumObj.setSum(sum);
		}
	}
}
