package student.dwillard.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class PrinterFieldsMethods {
  private static void printName(String name, Class<?> type, String modifiers) {
    {
      if (name.indexOf('.') > 0) {
        name = name.substring(name.lastIndexOf('.') + 1);
      }
    }
    System.out.print("        ");
    if (!modifiers.isEmpty()) {
      System.out.print(modifiers + " ");
    }
    System.out.print(type.getSimpleName() + " " + name);
  }

  static void printFields(Field[] fields) {
    for (Field field : fields) {
      printName(field.getName(), field.getType(), Modifier.toString(field.getModifiers()));
      System.out.println();
    }
  }

  static void printMethods(Method[] methods) {
    for (Method method : methods) {

      printName(method.getName(), method.getReturnType(), Modifier.toString(method.getModifiers()));

      Class<?>[] paramTypes = method.getParameterTypes();
      if (paramTypes.length > 0) {
        System.out.print('(');
      }
      for (int i = 0; i < paramTypes.length; i++) {
        if (i > 0) {
          System.out.print(", ");
        }
        System.out.print(paramTypes[i].getSimpleName());
      }
      if (paramTypes.length > 0) {
        System.out.print(')');
      }
      System.out.println();
    }
  }
}
