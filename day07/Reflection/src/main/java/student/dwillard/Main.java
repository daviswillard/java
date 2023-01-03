package student.dwillard;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;
import student.dwillard.format.FormatReflection;
import student.dwillard.reflect.Reflect;

public class Main {
	static Class<?> aClass;
	public static final Scanner console = new Scanner(System.in);

	public static void main(String[] args)
			throws InvocationTargetException,
			InstantiationException,
			IllegalAccessException {
			FormatReflection.listClasses();
			aClass = FormatReflection.returnClass();
			Reflect.printFieldsAndMethods(aClass);
			System.out.println(Reflect.createNewObject(aClass));
	}
}
