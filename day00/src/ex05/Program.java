package ex05;

import java.util.Scanner;

class Program {

    static final String[] days = {"TU", "WE", "TH", "FR", "SA", "SU", "MO"};
    static int[] studentsByte;
    static int[][][] attendanceList = new int[2][30][5];
    static int currentStudent = 0;


    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        String[] students = getNameArray(console);
        int[][] lessons = getLessonsArray(console);
        fillAttendanceList(students, console);
        printAttendanceList(students, lessons);
        console.close();
    }

    private static void printAttendanceList(String[] students, int[][] lessons) {
        for (int i = 0; i < students.length + 1; i++) {
            if (i == 0) {
                printFirstLine(lessons);
                System.out.println();
                continue;
            }
            System.out.printf("%10s", students[i - 1]);
            currentStudent = studentsByte[i - 1];
            printAttendance(lessons);
            System.out.println();
        }
    }

    private static void printAttendance(int[][] lessons) {
        for (int j = 0; j < 30; j++) {
            for (int k = 0; k < 5; k++) {
                if (lessons[j % 7][k] == 1) {
                    if ((attendanceList[0][j][k] & currentStudent) != 0 ||
                            (attendanceList[1][j][k] & currentStudent) != 0) {
                        int a = 0;
                        if ((attendanceList[0][j][k] & currentStudent) != 0) {
                            a = 1;
                        } else if ((attendanceList[1][j][k] & currentStudent) != 0){
                            a = -1;
                        }
                        System.out.printf("%10d|", a);
                    } else if ((attendanceList[0][j][k] & currentStudent) == 0 &&
                            (attendanceList[1][j][k] & currentStudent) == 0) {
                        System.out.printf("%11s", "|");
                    }
                }
            }
        }
    }

    private static void printFirstLine(int[][] lessons) {
        System.out.printf("%10s", "");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 7; j++) {
                for (int k = 0; k < 5; k++) {
                    if (lessons[j][k] == 1 && (j + 1) + 7 * i <= 30) {
                        System.out.printf("%1d:00%3s%3d|", k + 1, days[j], (j + 1) + 7 * i);
                    }
                }
            }
        }
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
        if (entry.length() == 0) {
            System.err.println("Entry is empty!");
            return true;
        }
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
        return i < arr.length;
    }

    private static boolean isWrongAttendanceFormat(String entry) {
        char[] probe = entry.toCharArray();
        int i = 0;

        while (i < entry.length() && probe[i] != ' ') {
            i++;
        }
        if (i == entry.length()) {
            return true;
        } else {
            i++;
        }

        int resHour;
        if (i < entry.length() && probe[i] >= '1' && probe[i] <= '5') {
            resHour = probe[i] - 49;
            i++;
        } else {
            return true;
        }
        i++;
        int resDay = 0;
        for (int j = 0; i < entry.length() && probe[i] >= '0' && probe[i] <= '9'; ++j, ++i) {
            resDay = resDay * 10 + (probe[i] - 48);
            if (resDay > 30) {
                return true;
            }
        }
        if (i == entry.length()) {
            return true;
        } else {
            i++;
        }
        if (entry.length() - i == "HERE".length()) {
            char[] here = "HERE".toCharArray();
            for (int j = 0; i + j < entry.length(); ++j) {
                if (here[j] != probe[i + j]) {
                    return true;
                }
            }
            if ((attendanceList[1][resDay - 1][resHour] & currentStudent) != 0) {
                System.err.println("Student is present and not present simultaneously!");
                System.exit(0);
            }
            attendanceList[0][resDay - 1][resHour] |= currentStudent;
        } else if (entry.length() - i == "NOT_HERE".length()) {
            char[] not_here = "NOT_HERE".toCharArray();
            for (int j = 0; i + j < entry.length(); ++j) {
                if (not_here[j] != probe[i + j]) {
                    return true;
                }
            }
            if ((attendanceList[0][resDay - 1][resHour] & currentStudent) != 0) {
                System.err.println("Student is present and not present simultaneously!");
                System.exit(0);
            }
            attendanceList[1][resDay - 1][resHour] |= currentStudent;
        } else {
            return true;
        }
        return false;
    }

    private static boolean isStudent(char[] student, char[] entry) {
        if (student == null) {
            return false;
        }
        for (int i = 0 ; i < student.length; ++i) {
            if (i == entry.length || student[i] != entry[i]) {
                return false;
            }
        }
        return true;
    }

    private static boolean isWrongAttendanceEntry(String entry, String[] students) {
        for (int i = 0; i < students.length; ++i) {
            if (isStudent(students[i].toCharArray(), entry.toCharArray())) {
                currentStudent = studentsByte[i];
                break;
            }
            if (i + 1 == students.length) {
                System.err.println("No such student!");
                return true;
            }
        }
        if (isWrongAttendanceFormat(entry)) {
            System.err.println("Wrong entry format!");
            return true;
        }
        return false;
    }

    private static void fillAttendanceList(String[] students, Scanner console) {
        String temp;
        studentsByte = new int[students.length];
        for (int i = 0; i < students.length; ++i) {
            studentsByte[i] = 1 << i;
        }

        while (!(temp = console.nextLine()).equals(".")) {
            if (isWrongAttendanceEntry(temp, students)) {
                System.exit(0);
            }
        }
    }

    private static int[][] fillLessons(String[] tokenArray) {

        int[][] result = new int[7][5];
        for (String token : tokenArray) {
            if (token == null) {
                break;
            }
            char [] buf = token.toCharArray();
            int res = (int) buf[0] - 49;
            if (buf[2] == 'T' && buf[3] == 'U') {
                if (result[0][res] == 1) {
                    System.err.println("Time and date already vacated!");
                }
                result[0][res] = 1;
            } else if (buf[2] ==  'W') {
                if (result[1][res] == 1) {
                    System.err.println("Time and date already vacated!");
                }
                result[1][res] = 1;
            } else if (buf[2] == 'T' && buf[3] == 'H') {
                if (result[2][res] == 1) {
                    System.err.println("Time and date already vacated!");
                }
                result[2][res] = 1;
            } else if (buf[2] == 'F') {
                if (result[3][res] == 1) {
                    System.err.println("Time and date already vacated!");
                }
                result[3][res] = 1;
            } else if (buf[2] == 'S' && buf[3] == 'A') {
                if (result[4][res] == 1) {
                    System.err.println("Time and date already vacated!");
                }
                result[4][res] = 1;
            } else if (buf[2] == 'S' && buf[3] == 'U') {
                if (result[5][res] == 1) {
                    System.err.println("Time and date already vacated!");
                }
                result[5][res] = 1;
            } else {
                if (result[6][res] == 1) {
                    System.err.println("Time and date already vacated!");
                }
                result[6][res] = 1;
            }
        }
        return result;
    }

    private static int[][] getLessonsArray(Scanner console) {
        String temp;
        int i = 0;
        String[] tokenArray = new String[10];

        while (!(temp = console.nextLine()).equals(".")) {
            if (temp.equals("help")) {
                System.out.println("Entry format: \"h DD\"");
                System.out.println("hour DAY");
                continue ;
            }
            if (i == 10) {
                System.err.println("Too many lessons per week!");
                System.exit(0);
            } else if (isWrongEntry(temp)) {
                System.err.println("Wrong class format!");
                System.exit(0);
            }
            tokenArray[i] = temp;
            i++;
        }
        if (i == 0) {
            System.err.println("No lessons specified!");
            System.exit(0);
        }
        return fillLessons(tokenArray);
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
        if (i == 0) {
            System.err.println("No students specified!");
            System.exit(0);
        }

        String[] ret = new String[i];
        for (int j = 0; j < i; j++) {
            ret[j] = students[j];
        }
        return ret;
    }
}
