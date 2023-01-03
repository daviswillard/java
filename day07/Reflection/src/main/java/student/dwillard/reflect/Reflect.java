package student.dwillard.reflect;

import static student.dwillard.Main.separator;

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

  private static Object getTypeFromScanner(String type) {
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
    System.out.println("Object created: " + ret);
    System.out.println(separator);
    return ret;
  }


  public static void changeField(Object obj) throws NoSuchFieldException, IllegalAccessException {
    System.out.println("Enter name of the field for changing:");
    String fieldName = Main.console.nextLine();
    Class<?> aClass = obj.getClass();
    Field field = aClass.getDeclaredField(fieldName);

    String s = field.getType().getSimpleName();
    System.out.println("Enter " + s + " value:");
    Object newValue = getTypeFromScanner(s.toLowerCase());

    field.setAccessible(true);
    field.set(obj, newValue);
    System.out.println("Object updated: " + obj);
    System.out.println(separator);
  }


  public static void callMethod(Object obj)
      throws InvocationTargetException, IllegalAccessException {
    System.out.println("Enter name of the method for call:");
    String methodName = Main.console.nextLine();

    Method[] methods = obj.getClass().getDeclaredMethods();
    for (Method method : methods) {
      StringBuilder signature = new StringBuilder(method.getName());

      Class<?>[] paramTypes = method.getParameterTypes();
      if (paramTypes.length > 0) {
        signature.append('(');
        for (int i = 0; i < paramTypes.length; i++) {
          if (i > 0) {
            signature.append(", ");
          }
          signature.append(paramTypes[i].getSimpleName());
        }
        signature.append(')');
      }
      if (!signature.toString().equals(methodName)) {
        continue;
      }
      Object[] callList = new Object[paramTypes.length];
      for (int i = 0; i < paramTypes.length; ++i) {
        String s = paramTypes[i].getSimpleName();
        System.out.println("Enter " + s + " value");
        callList[i] = getTypeFromScanner(s.toLowerCase());
      }

      Object result = null;
      if (Modifier.isStatic(method.getModifiers())) {
        obj = null;
      }
      if (method.getReturnType() == void.class) {
        method.invoke(obj, callList);
      } else {
        result = method.invoke(obj,callList);
      }
      if (result != null) {
        System.out.println("Method returned:");
        System.out.println(result);
      }
    }
  }
}
