package student.dwillard.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import student.dwillard.Main;

public class Reflect {

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

  private static void printFields(Field[] fields) {
    for (Field field : fields) {
      printName(field.getName(), field.getType(), Modifier.toString(field.getModifiers()));
      System.out.println();
    }
  }

  private static void printMethods(Method[] methods) {
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

  public static void printFieldsAndMethods(Class<?> object) {
    {
      Field[] fields = object.getDeclaredFields();
      System.out.println("fields:");
      printFields(fields);
    }
    {
      Method[] methods = object.getDeclaredMethods();
      System.out.println("methods:");
      printMethods(methods);
    }
  }


  private static Object getTypeFromScanner(Parameter parameter) {
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

  public static Object createNewObject(Class<?> aClass)
      throws InvocationTargetException,
      InstantiationException,
      IllegalAccessException {
    System.out.println("Let's create an object.");

    Constructor<?>[] constructors = aClass.getConstructors();
    Object ret = null;
    for (Constructor<?> constructor : constructors) {

      Parameter[] parameters = constructor.getParameters();
      if (parameters.length == 0) {
        continue;
      }
      Object[] initList = new Object[parameters.length];
      for (int i = 0; i < initList.length; i++) {
        Parameter param = parameters[i];
        System.out.println(param.getName());
        initList[i] = getTypeFromScanner(param);
      }
      ret = constructor.newInstance(initList);
    }
    if (ret == null) {
      throw new RuntimeException("No constructor with parameters!");
    }
    return ret;
  }
}
