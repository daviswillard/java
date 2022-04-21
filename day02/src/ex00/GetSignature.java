package ex00;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.math.BigInteger;

public class GetSignature {

    static class EmptyFileException extends RuntimeException {
        EmptyFileException() {
            super("File is empty");
        }
    }

    private static GetSignature instance;
    private final static String[] strArr = new String[10];

    private GetSignature() {}
    public static GetSignature getInstance() {
        if (instance == null) {
            instance = new GetSignature();
        }
        return instance;
    }

    private Map<Integer, Long> table = new HashMap<>();

    private Long convertToLong(String line) {
        Long    value;

        if (line == null) {
            throw new EmptyFileException();
        }

        line = line.toLowerCase(Locale.ROOT)
                    .substring((line.indexOf(',') + 1), line.length())
                    .replaceAll(" ", "");
        System.out.printf("%-20s", line);
        value = new BigInteger(line, 16).longValue();
        System.out.println(value);
        return value;
    }

    public String[] getTypes() {
        return strArr;
    }

    public Map<Integer, Long> getSignature(String filename) {
        String line;
        BufferedReader fin;

        try {
            fin = new BufferedReader(new FileReader(filename));
            int i = 0;
            while ((line = fin.readLine()) != null) {
                strArr[i] = line.substring(0, line.indexOf(','));
                table.put(i, convertToLong(line));
                i++;
            }
        } catch (IOException exc) {
            System.err.println("Couldn't read line from file");
            System.exit(1);
        }
        return table;
    }
}
