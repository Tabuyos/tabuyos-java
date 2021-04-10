package com.tabuyos.java.proxy5;

import com.tabuyos.java.proxy4.CustomInvocationHandler;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.*;
import java.lang.reflect.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

/**
 * @Author Tabuyos
 * @Time 2020/3/21 14:02
 * @Site www.tabuyos.com
 * @Email tabuyos@outlook.com
 * @Description
 */
public class ProxyUtilTool {
    private static final String PACKAGE_DEFINE = "com.tabuyos.java.proxy";
    private static final String NEWLINE = "\n";
    private static final String TAB = "\t";
    private static final String SPACE = " ";
    private static final String SEMICOLON = ";";
    private static final String OPEN_BRACE = "{";
    private static final String CLOSE_BRACE = "}";

    public static Object newInstance(Class<?>[] targetInterface, CustomInvocationHandler handler) {
        // header
        Object proxy = null;
        String packageContent = "";
        String classContent = "";
        StringBuilder importContent = new StringBuilder();
        StringBuilder interfaceName = new StringBuilder();
        String implementsName = "";
        String filedContent = "";
        String constructorContent = "";
        StringBuilder methodContent = new StringBuilder();
        StringBuilder content = new StringBuilder();

        packageContent += "package " + PACKAGE_DEFINE + SEMICOLON + NEWLINE;
        for (Class<?> clazz : targetInterface) {
            interfaceName.append(clazz.getSimpleName()).append(", ");
            importContent.append("import ").append(clazz.getName()).append(SEMICOLON).append(NEWLINE);

            for (Method method : clazz.getDeclaredMethods()) {
//                String returnTypeName = method.getReturnType().getSimpleName();
                Type type = method.getGenericReturnType();
                String methodName = method.getName();
                Class<?>[] args = method.getParameterTypes();
                StringBuilder argsContent = new StringBuilder();
                StringBuilder paramsContent = new StringBuilder();
                StringBuilder returnContent = new StringBuilder();
                StringBuilder argsClass = new StringBuilder();
                int flag = 0;
                for (Class<?> arg : args) {

                    argsContent.append(arg.getName()).append(" var");
                    argsContent.append(flag);
                    argsContent.append(", ");
                    paramsContent.append("var").append(flag).append(", ");
                    argsClass.append(arg.getName()).append(".class").append(", ");
                    flag++;
                }
                if (argsContent.length() > 0) {
                    argsContent = new StringBuilder(argsContent.substring(0, argsContent.lastIndexOf(",")));
                    paramsContent = new StringBuilder(paramsContent.substring(0, paramsContent.lastIndexOf(",")));
                    argsClass = new StringBuilder(argsClass.substring(0, argsClass.lastIndexOf(",")));
                }
                if (!type.toString().equals("void")){
                    methodContent.append(TAB).append("@SuppressWarnings(\"unchecked\")").append(NEWLINE);
                }
//                methodContent.append(TAB).append(Modifier.toString(method.getModifiers())).append(SPACE);
                methodContent.append(TAB).append("public ");
                methodContent.append(type.toString()).append(SPACE).append(methodName).append("(");
                methodContent.append(argsContent).append(") {").append(NEWLINE);
                methodContent.append(TAB).append(TAB).append("Object[] args = new Object[]{").append(paramsContent).append("};").append(NEWLINE);
                methodContent.append(TAB).append(TAB).append("Method method = null;").append(NEWLINE);
                methodContent.append(TAB).append(TAB).append("try {").append(NEWLINE);
                methodContent.append(TAB).append(TAB).append(TAB).append("method = Class.forName(\"");
                methodContent.append(clazz.getName()).append("\").getDeclaredMethod(\"").append(methodName).append("\"");
                if (argsClass.length() != 0) {
                    methodContent.append(", ").append(argsClass.toString());
                }
                methodContent.append(");").append(NEWLINE);

                methodContent.append(TAB).append(TAB).append(TAB);
                if (!type.toString().equals("void")) {
                    importContent.append("import ").append(method.getReturnType().getName()).append(SEMICOLON).append(NEWLINE);
                    methodContent.append("return (").append(type.toString()).append(") ");
                    returnContent.append(TAB).append(TAB).append("return null;");
                }
                methodContent.append("handler.invoke(method, args)").append(SEMICOLON).append(NEWLINE);

                methodContent.append(TAB).append(TAB).append("} catch (Throwable e) {").append(NEWLINE);
                methodContent.append(TAB).append(TAB).append(TAB).append("e.printStackTrace();").append(NEWLINE);
                methodContent.append(TAB).append(TAB).append(CLOSE_BRACE).append(NEWLINE);
                methodContent.append(returnContent.toString());
                methodContent.append(TAB).append(CLOSE_BRACE).append(NEWLINE);
            }
        }
        implementsName += interfaceName.subSequence(0, interfaceName.lastIndexOf(",")).toString();
        importContent.append("import ").append(CustomInvocationHandler.class.getName()).append(SEMICOLON).append(NEWLINE);
        importContent.append("import ").append(Method.class.getName()).append(SEMICOLON).append(NEWLINE);
        classContent += "public class $Proxy implements " + implementsName + SPACE + OPEN_BRACE + NEWLINE;
        filedContent += TAB + "private " + CustomInvocationHandler.class.getSimpleName() + " handler" + SEMICOLON + NEWLINE;
        constructorContent += TAB + "public $Proxy (" + CustomInvocationHandler.class.getSimpleName() + " handler" + ") { this.handler = handler; }" + NEWLINE;

        content.append(packageContent).append(importContent.toString()).append(classContent).append(filedContent);
        content.append(constructorContent).append(methodContent.toString()).append(CLOSE_BRACE).append(NEWLINE);

        String basePath = "D:" + File.separator + PACKAGE_DEFINE.replace(".", File.separator);
        String fileName = "$Proxy.java";
        String path = basePath + File.separator + fileName;

        File file = new File(basePath);
        if (!file.exists()) {
            boolean flag = file.mkdirs();
        }
        File proxyFile = new File(path);
        try {
            FileWriter fileWriter = new FileWriter(proxyFile);
            fileWriter.write(content.toString());
            fileWriter.flush();
            fileWriter.close();
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
            Iterable<? extends JavaFileObject> units = fileManager.getJavaFileObjects(proxyFile);
            JavaCompiler.CompilationTask compilationTask = compiler.getTask(null, fileManager, null, null, null, units);
            compilationTask.call();
            fileManager.close();

            URL[] urls = new URL[]{new URL("file:D:\\")};
            URLClassLoader urlClassLoader = new URLClassLoader(urls);
            Class<?> clazz = urlClassLoader.loadClass("com.tabuyos.java.proxy.$Proxy");

            Constructor<?> constructor = clazz.getConstructor(CustomInvocationHandler.class);
            proxy = constructor.newInstance(handler);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return proxy;
    }

    private static Method[] merge(Method[] methods1, Method[] methods2) {
        Method[] tempMethods = new Method[methods1.length + methods2.length];
        System.arraycopy(methods1, 0, tempMethods, 0, methods1.length);
        System.arraycopy(methods2, 0, tempMethods, methods1.length, methods2.length);
        return tempMethods;
    }
}
