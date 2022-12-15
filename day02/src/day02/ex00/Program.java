package day02.ex00;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;
import java.util.Map;


public class Program {

    public static void main(String[] args) throws IOException {
        String[]            arr;
        FileInputStream[]   in = null;
        FileOutputStream    out = null;
        File                myFile = null;

        Map<Integer, Long> table = GetSignature
                .getInstance()
                .getSignature("/Users/dwillard/Desktop/jaaav/day02/src/ex00/signatures.txt");
        arr = GetSignature.getInstance().getTypes();
        myFile = new File("result.txt");
        out = new FileOutputStream(myFile);
        try  {
            for (int i = 0; i < args.length; i++) {
                in[i] = new FileInputStream(args[i]);

            }
        } finally {
            if (in != null) {
                for (int i = 0; i < 10 && in[i] != null; i++) {
                    in[i].close();
                }
            }
            if (out != null) {
                out.close();
            }
        }
    }
}
