package day00.ex03;
import java.lang.System;
import java.util.Scanner;

public class Program
{
	static long	checkMin(long cont, Scanner console) {
		int	temp = 9;

		cont *= 10;
		for (int i = 0; i < 5; i++)	{
			int temp_min = console.nextInt();

			if (temp_min < temp) {
                temp = temp_min;
            }
		}
		console.nextLine();
		return cont + temp;
	}

	static long	printGraphic(long cont, int count, int iter) {
		long	div = 1;
		int		chars;

		while (--count > 0) {
			div *= 10;
		}
		chars = (int)(cont / div);
		cont -= chars * div;
		System.out.print("Week " + iter);
		while (chars-- > 0) {
			System.out.print('=');
		}
		System.out.println('>');
		return cont;
	}

	public static void main(String[] args) {
		Scanner	console = new Scanner(System.in);
		long	cont = 0;
		int		count = 0;

		for (int i = 1; i <= 18; i++) {
			String	week = "Week " + i;
			String	console_week = console.nextLine();

			if (console_week.equals("42")) {
				break ;
			}

			if (!week.equals(console_week))	{
				System.err.println("IllegalArgument");
				System.exit(-1);
			}

			cont = checkMin(cont, console);
			count++;
		}

		for (int i = 1; count > 0; count--) {
			cont = printGraphic(cont, count, i);
			i++;
		}
	}
}
