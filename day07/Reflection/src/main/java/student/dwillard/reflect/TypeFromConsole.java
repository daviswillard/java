package student.dwillard.reflect;

import java.lang.reflect.Parameter;
import student.dwillard.Main;

public class TypeFromConsole {
  static Object getTypeFromConsole(Parameter parameter) {
    switch (parameter.getType().getSimpleName().toLowerCase()) {
      case ("string"):
        return Main.console.nextLine();
      case ("integer"):
        return Integer.parseInt(Main.console.nextLine());
      case ("double"):
        return Double.parseDouble(Main.console.nextLine());
      case ("boolean"):
        return Boolean.parseBoolean(Main.console.nextLine());
      case ("long"):
        return Long.parseLong(Main.console.nextLine());
      default:
        throw new RuntimeException("Couldn't understand this type!");
    }
  }

  static Object getTypeFromConsole(String type) {
    switch (type) {
      case ("string"):
        return Main.console.nextLine();
      case ("integer"):
        return Integer.parseInt(Main.console.nextLine());
      case ("double"):
        return Double.parseDouble(Main.console.nextLine());
      case ("boolean"):
        return Boolean.parseBoolean(Main.console.nextLine());
      case ("long"):
        return Long.parseLong(Main.console.nextLine());
      default:
        throw new RuntimeException("Couldn't understand this type!");
    }
  }
}
