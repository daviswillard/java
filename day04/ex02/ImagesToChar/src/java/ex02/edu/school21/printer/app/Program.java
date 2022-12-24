package ex02.edu.school21.printer.app;

import com.beust.jcommander.JCommander;
import ex02.edu.school21.printer.logic.Logic;
import ex02.edu.school21.printer.logic.Parser;
import ex02.edu.school21.printer.logic.Colors;

import java.io.File;

public class Program {

    public static void main(String[] args) {

        Parser parser = new Parser();
        JCommander.newBuilder().addObject(parser).build().parse(args);

        if (args.length != 2) {
            System.err.println("Wrong arguments");
            System.exit(-1);
        }

        File file = new File("./target/resources/image.bmp");
        Logic.bmpImageToChar(Colors.getColor(parser.getBlack()), Colors.getColor(parser.getWhite()), file.getPath());
    }
}
