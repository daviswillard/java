package ex05;

import java.util.Arrays;
import java.util.Scanner;

public class Program {

    static final String[] calendar;
    static {
        String[] days = {"TU", "WE", "TH", "FR", "SA", "SU", "MO"};
        calendar = new String[30];
        for (int i = 0; i < 30; i++) {
            calendar[i] = days[i % 7];
        }
    }



    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        String[] students = getNameArray(console);
        int[][] lessons = getLessonsArray(console);
        Arrays.stream(students).forEach(System.out::println);
    }

    private static boolean isWithWhiteSpace(String probe) {
        char[] test = probe.toCharArray();
        for (int i = 0; i < probe.length(); i++) {
            if (test[i] == ' ' || test[i] == '\t') {
                return true;
            }
        }
        return false;
    }

    private static boolean isWrongEntry(String entry) {
        char[] arr = entry.toCharArray();
        int i = 1;

        if (arr[0] < '1' || arr[0] > '5') {
            return true;
        }
        if (arr[i] == ' ') {
            i++;
        } else {
            return true;
        }
        if (i + 1 < arr.length
                && ((arr[i] == 'T' && arr[i + 1] == 'U' )
                || (arr[i] == 'W' && arr[i + 1] == 'E' )
                || (arr[i] == 'T' && arr[i + 1] == 'H' )
                || (arr[i] == 'F' && arr[i + 1] == 'R' )
                || (arr[i] == 'S' && arr[i + 1] == 'A' )
                || (arr[i] == 'S' && arr[i + 1] == 'U' )
                || (arr[i] == 'M' && arr[i + 1] == 'O' ))) {
            i += 2;
        }
         if (i < arr.length) {
             return true;
         }
        return false;
    }

    private static int[][] getLessonsArray(Scanner console) {
        String temp;
        int i = 0;
        int[][] lessons = new int[30][5];
//        String[] table = new String[10]; - че с этим делать
        while (!(temp = console.nextLine()).equals(".")) {
            if (i == 10) {
                System.err.println("Too many lessons per week!");
                System.exit(0);
            } else if (isWrongEntry(temp)) {
                System.err.println("Wrong class format!");
                System.exit(0);
            }
            i++;
        }
        return lessons;
     }

    private static String[] getNameArray(Scanner console) {
        String[] students = new String[10];
        String temp;
        int i = 0;
        while (!(temp = console.nextLine()).equals(".")) {
            if (i == 10) {
                System.err.println("Too many students!");
                System.exit(0);
            } else if (temp.length() > 10) {
                System.err.println("Name is too long!");
                System.exit(0);
            } else if (isWithWhiteSpace(temp)) {
                System.err.println("Name contains space(s)!");
                System.exit(0);
            }
            students[i++] = temp;
        }
        String[] ret = new String[i];
        for (int j = 0; j < i; j++) {
            ret[j] = students[j];
        }
        return ret;
    }
}
