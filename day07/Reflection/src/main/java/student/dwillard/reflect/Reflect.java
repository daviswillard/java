package student.dwillard.reflect;

import static student.dwillard.Main.separator;
import static student.dwillard.reflect.PrinterFieldsMethods.printFields;
import static student.dwillard.reflect.PrinterFieldsMethods.printMethods;
import static student.dwillard.reflect.TypeFromConsole.getTypeFromConsole;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import student.dwillard.Main;

public class Reflect {

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
        initList[i] = getTypeFromConsole(param);
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


  public static void changeField(Object obj)
      throws NoSuchFieldException,
      IllegalAccessException {
    System.out.println("Enter name of the field for changing:");
    String fieldName = Main.console.nextLine();
    Class<?> aClass = obj.getClass();
    Field field = aClass.getDeclaredField(fieldName);

    String s = field.getType().getSimpleName();
    System.out.println("Enter " + s + " value:");
    Object newValue = getTypeFromConsole(s.toLowerCase());

    field.setAccessible(true);
    field.set(obj, newValue);
    System.out.println("Object updated: " + obj);
    System.out.println(separator);
  }

  private static boolean isRightSignature(Method method, Class<?>[] paramTypes) {
    StringBuilder signature = new StringBuilder(method.getName());
    String methodName = Main.console.nextLine();

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
    return (signature.toString().equals(methodName));
  }

  public static void callMethod(Object obj)
      throws InvocationTargetException,
      IllegalAccessException {
    System.out.println("Enter name of the method for call:");
    Method[] methods = obj.getClass().getDeclaredMethods();


    for (Method method : methods) {

      Class<?>[] paramTypes = method.getParameterTypes();
      if (!isRightSignature(method, paramTypes)) {
        continue;
      }

      Object[] callList = new Object[paramTypes.length];
      for (int i = 0; i < paramTypes.length; ++i) {
        String s = paramTypes[i].getSimpleName();
        System.out.println("Enter " + s + " value");
        callList[i] = getTypeFromConsole(s.toLowerCase());
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
      if (method.getReturnType() != void.class) {
        System.out.println("Method returned:");
        System.out.println(result);
      }
      return;
    }
    throw new RuntimeException("No method with such signature!");
  }
}
