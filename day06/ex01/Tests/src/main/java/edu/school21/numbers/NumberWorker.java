package edu.school21.numbers;

class IllegalNumberException extends RuntimeException {
    public IllegalNumberException() {
        super("Illegal argument");
    }
}

public class NumberWorker {

    public boolean	isPrime(int number) {
        if (number <= 1) {
            throw new IllegalNumberException();
        }
        if (number <= 3) {
            return true;
        }
        if (number % 2 == 0) {
            return false;
        }
        if (number % 3 == 0) {
            return false;
        }
        for (int i = 5; i * i <= number; i = i + 6) {
            if ((number % i == 0) | (number % (i + 2) == 0)) {
                return false;
            }
        }
        return true;
    }

    public int	digitsSum(int number) {
        int	ret;

        ret = 0;
        while (number / 10 > 0 || number % 10 > 0) {
            ret += number % 10;
            number /= 10;
        }
        return ret;
    }
}
