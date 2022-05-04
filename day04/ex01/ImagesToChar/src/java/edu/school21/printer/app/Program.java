package edu.school21.printer.app;

import edu.school21.printer.logic.Logic;
import java.io.File;

public class Program {

    public static void main(String[] args) {

        if (args.length != 2) {
            System.err.println("Wrong arguments");
            System.exit(-1);
        }

        char colorWhite = args[0].charAt(0);
        char colorBlack = args[1].charAt(0);
        File file = new File("./target/resources/image.bmp");

        Character[][] image = Logic.bmpImageToChar(colorWhite, colorBlack, file.getPath());

        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
                System.out.print(image[i][j]);
            }
            System.out.println();
        }
    }
}
