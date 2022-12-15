package day00.ex04;
import java.lang.System;
import java.util.Scanner;

public class Program
{
    static int  compareElements(int comp, int[][] array) {
        int index = 0;

        while (index < 10) {
            if (comp > array[index][0]) {
                return index;
            } else if (comp == array[index][0]) {
                return index + 10;
            } else {
                index++;
            }
        }
        return -1;
    }

    static void swapElements(int[][] array, int arr_i, final int[] src,
                             int src_i) {
        while (arr_i < 10 && array[arr_i][0] == src[src_i]
                && array[arr_i][1] < src_i) {
            arr_i++;
        }
        if (arr_i == 10) {
            return;
        }
        shiftElement(array, arr_i, src, src_i);
    }

    static void shiftElement(int[][] array, int arr_i, final int[] src,
                             int src_i) {
        int temp = 8;

        while (temp >= arr_i) {
            array[temp + 1][0] = array[temp][0];
            array[temp + 1][1] = array[temp][1];
            temp--;
        }
        array[arr_i][0] = src[src_i];
        array[arr_i][1] = src_i;
    }

    static void countChars(int[][] result, final int[] src) {
        for (int i = 0; i < 65536; i++) {
            int j;

            if (src[i] == 0) {
                continue;
            }

            j = compareElements(src[i], result);

            if (j >= 0 && j < 10) {
                shiftElement(result, j, src, i);
            } else if (j >= 10 && j < 20) {
                swapElements(result, j - 10, src, i);
            }
        }
    }

    static int  getSharps(int mod, double scale) {
        int     times;

        if (mod < scale) {
            return 0;
        }
        times = (int)(mod / scale);
        return times;
    }

    static void printHistogram(int sharp, int[] array_element, int iter) {
        int sum = sharp + iter;
        if (array_element[0] == 0 && array_element[1] == 0)
            return;
        if (sum == 10) {
            System.out.printf("%3d", array_element[0]);
        } else if (sum > 10 && sum < 21 && sharp != 0) {
            System.out.printf("%3c", '#');
        } else if (sharp == 0) {
            System.out.printf("%3c", array_element[1]);
        }
    }

    static void printResult(int[] sharps, int[][] array) {
        if (array[0][0] == 0) {
            return;
        }
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 10; j++) {
                printHistogram(sharps[j], array[j], i);
            }
            System.out.println();
        }
        for (int i = 0; i < 10; i++) {
            if (array[i][0] == 0) {
                break;
            }
            System.out.printf("%3c", array[i][1]);
        }
        System.out.println();
    }

	public static void main(String[] args) {
        int     strlen;
		int[]   char_map = new int[65536];
        int[][] result = new int[10][2];
        char[]  c_str;
        Scanner console = new Scanner(System.in);
        String  input;

        input = console.nextLine();
        strlen = input.length();
        c_str = input.toCharArray();
        for (int i = 0; i < strlen; i++) {
            if (char_map[c_str[i]] == 999) {
                continue;
            }
            char_map[c_str[i]]++;
        }
        countChars(result, char_map);

        {
            double  scale;
            int[]   sharps = new int[10];

            scale = (double)result[0][0] / 10;
            for (int i = 0; i < 10; i++) {
                if (result[i][1] == 0) {
                    break;
                }
                sharps[i] = getSharps(result[i][0], scale);
            }
            printResult(sharps, result);
        }
	}
}
