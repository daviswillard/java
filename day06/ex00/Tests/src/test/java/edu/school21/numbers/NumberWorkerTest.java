package edu.school21.numbers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class NumberWorkerTest {

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 5, 7, 113, 67, 83, 251, 911, 607})
    public void isPrimeForPrimes(int prime) throws RuntimeException{
        assertTrue(new NumberWorker().isPrime(prime));
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 16, 1666, 0x7fffffff, 27993, 45005, 607 * 607})
    public void isPrimeForNotPrimes(int notPrime) throws RuntimeException{
        assertFalse(new NumberWorker().isPrime(notPrime));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, -1, 0xffffffff})
    public void isPrimeFoIncorrectNumbers(int incorrectPrime) throws RuntimeException{
        assertThrows(RuntimeException.class, () -> new NumberWorker().isPrime(incorrectPrime));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    public void isSumCorrect(int number, int expected) throws RuntimeException {
        assertEquals(expected, new NumberWorker().digitsSum(number));
    }
}

