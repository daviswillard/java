package ex02.edu.school21.printer.logic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.BACK_COLOR;

public class Logic {

    public static void bmpImageToChar(Color white, Color black, String bmpImagePath) {
        BufferedImage   bmpImage = null;
        if (white == null || black == null) {
            throw new RuntimeException("Empty color parameters!");
        }
        try {
            bmpImage = ImageIO.read(new File(bmpImagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int height = bmpImage.getHeight();
        int width = bmpImage.getWidth();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (bmpImage.getRGB(j, i) == Color.BLACK.getRGB())
                    System.out.print(colorize("  ", BACK_COLOR(black.getRed(), black.getGreen(), black.getBlue())));
                else if (bmpImage.getRGB(j, i) == Color.WHITE.getRGB())
                    System.out.print(colorize("  ", BACK_COLOR(white.getRed(), white.getGreen(), white.getBlue())));
            }
            System.out.println();
        }
    }
}
