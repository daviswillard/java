package day00.ex01;
import java.lang.System;
import java.util.Scanner;

public class Program
{

	static int	isPrime(int number) {
		if (number <= 1) {
			return 0;
		}
		if (number <= 3) {
			return 1;
		}
		if (number % 2 == 0) {
			return -2;
		}
		if (number % 3 == 0) {
			return -3;
		}
		int	steps = 4;
		for (int i = 5; i * i <= number; i = i + 6) {
			if (number % i == 0){
				return -steps;
			}
			steps++;
			if (number % (i + 2) == 0) {
				return -steps;
			}
			steps++;
		}
		return steps;
	}

	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);

		int number = console.nextInt();
		int ret = isPrime(number);
		if (ret == 0) {
			System.err.println("Illegal Argument");
	        System.exit(-1);
		} else if (ret < 0) {
			System.out.printf("false %d\n", -ret);
		} else {
			System.out.printf("true %d\n", ret);
		}
	}
}
