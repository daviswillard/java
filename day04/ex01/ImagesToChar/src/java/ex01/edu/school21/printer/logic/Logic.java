package ex01.edu.school21.printer.logic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Logic {
    public static Character[][] bmpImageToChar(char white, char black, String bmpImagePath) {
        BufferedImage   bmpImage = null;
        try {
            bmpImage = ImageIO.read(new File(bmpImagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int height = bmpImage.getHeight();
        int width = bmpImage.getWidth();
        Character[][] charImage = new Character[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int color = bmpImage.getRGB(j, i);
                if (color == Color.WHITE.getRGB()) {
                    charImage[i][j] = white;
                } else if (color == Color.BLACK.getRGB())
                    charImage[i][j] = black;
            }
        }
        return charImage;
    }
}
