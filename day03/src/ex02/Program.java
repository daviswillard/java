package ex02;

import java.util.Random;

public class Program {

    static private int[] generateRandomArray(int count) {
        Random  random = new Random();
        int[]   ret;

        ret = new int[count];
        for (int i = 0; i < count; i++) {
            ret[i] = random.nextInt(2000) - 1000;
        }
        return ret;
    }

    static int parseArg(String count) {
        int	ret;

        String sub = count.substring(count.indexOf('=') + 1);
        ret = Integer.parseInt(sub);
        return ret;
    }

    public static void main(String[] args) {
        int         arraySize;
        int         threadsCount;
        int[]       array;
        int         resultST = 0;
        int         resultMT = 0;
        Thread[]    threadArr = null;
        Result      sum = new Result();


        if (args.length != 2 || !args[0].startsWith("--arraySize=")
                || !args[1].startsWith("--threadsCount=")) {
            return;
        }
        arraySize = parseArg(args[0]);
        threadsCount = parseArg(args[1]);
        if (arraySize > 2000000) {
            arraySize = 2000000;
        }
        if (threadsCount > arraySize) {
            threadsCount = arraySize;
        }

        array = generateRandomArray(arraySize);

        for (int i = 0; i < arraySize; i++) {
            resultST += array[i];
        }

        System.out.println("Sum " + resultST);

        threadArr = new Thread[arraySize];
        for (int i = 0; i < threadsCount; i++) {
            threadArr[i] = new MyThread(i, threadsCount, array, sum);
            threadArr[i].start();
        }
        try {
            for (int i = 0; i < threadsCount; i++) {
                threadArr[i].join();
            }
        } catch (InterruptedException exc) {}
        resultMT = sum.getSum();
        System.out.println("Sum by threads: " + resultMT);

        if (resultMT != resultST) {
            System.err.println("Results don't match");
        }
    }
}
