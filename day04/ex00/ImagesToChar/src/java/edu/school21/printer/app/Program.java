package edu.school21.printer.app;

import edu.school21.printer.logic.Logic;

public class Program {

    public static void main(String[] args) {

        if (args.length != 3) {
            System.out.println("Wrong arguments");
            System.exit(-1);
        }

        char colorWhite = args[0].charAt(0);
        char colorBlack = args[1].charAt(0);
        String bmpImagePath = args[2];

        Character[][] image = Logic.bmpImageToChar(colorWhite, colorBlack, bmpImagePath);

        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
                System.out.print(image[i][j]);
            }
            System.out.println();
        }
    }
}
