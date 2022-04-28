package edu.school21.app;

import edu.school21.utils.*;

public class Program {
    public static void main(String[] args) {
        PreProcessor    preProcessor = new PreProcessorToUpperImpl();
        Renderer        renderer = new RendererErrImpl(preProcessor);
        PrinterWithPrefixImpl printer = new PrinterWithPrefixImpl(renderer);
        printer.setPrefix("Prefix ");
        printer.print("Hello");
    }
}
