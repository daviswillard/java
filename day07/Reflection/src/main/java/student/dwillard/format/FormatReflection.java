package student.dwillard.format;

import static student.dwillard.Main.separator;

import student.dwillard.Main;
import student.dwillard.classes.Family;
import student.dwillard.classes.Player;

public class FormatReflection {
  public static void listClasses() {
    System.out.println("Classes:");
    System.out.printf("%s%s\n", "  - ", Family.class.getSimpleName());
    System.out.printf("%s%s\n", "  - ", Player.class.getSimpleName());
    System.out.println(separator);
  }

  public static Class<?> returnClass() {
    System.out.println("Enter class name:");
    String answer = Main.console.nextLine();
    System.out.println(separator);
    switch (answer) {
      case ("Family"):
        return Family.class;
      case ("Player"):
        return Player.class;
      default:
        throw new RuntimeException("No such available classes!");
    }
  }
}
