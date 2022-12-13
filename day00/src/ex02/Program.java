package ex02;
import java.lang.System;
import java.util.Scanner;

public class Program
{
	static boolean	isPrime(int number) {
        if (number <= 1) {
            return false;
        }

        if (number <= 3) {
            return true;
        }

        if (number % 2 == 0 || number % 3 == 0) {
            return false;
        }

        for (int i = 5; i * i <= number; i = i + 6) {
            if (number % i == 0 || number % (i + 2) == 0) {
                return false;
            }
        }
        return true;
	}

	static int	toNumber(int number) {
		int	ret;

		ret = 0;
		while (number / 10 > 0 || number % 10 > 0) {
			ret += number % 10;
			number /= 10;
		}
		return ret;
	}

	public static void main(String[] args) {
		Scanner	console = new Scanner(System.in);
		int		number;
		short	result = 0;

		while (true){
			number = console.nextInt();
			if (number == 42) {
				break;
			}
			number = toNumber(number);
			if (isPrime(number)) {
				result++;
			}
		}
		System.out.printf("Count of coffee-request - %d\n", result);
	}
}
