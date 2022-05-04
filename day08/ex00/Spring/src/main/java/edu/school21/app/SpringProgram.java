package edu.school21.app;

import edu.school21.utils.Printer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringProgram {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        Printer printer = context.getBean("printerStdToUpperPrefix", Printer.class);
        printer.print("Hello!");
    }
}
