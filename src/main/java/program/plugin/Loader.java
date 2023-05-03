package program.plugin;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.net.URL;

public class Loader {
    // returns an array list of class names
    public ArrayList<String> getClassNameFromJar(JarInputStream jarFile) throws Exception {
        ArrayList<String> classNames = new ArrayList<>();
        try {
            JarEntry jar;
            while (true) {
                jar = jarFile.getNextJarEntry();
                if (jar == null) {
                    break;
                }
                if (jar.getName().endsWith(".class")) {
                    String className = jar.getName().replace("/", ".");
                    String myClass = className.substring(0, className.lastIndexOf('.'));
                    classNames.add(myClass);
                }
            }
        } catch (Exception e) {
            throw new Exception("Error while getting class name from jarfile!");
        }
        return classNames;
    }

    // returns an array list of class names
    public ArrayList<String> getClassNameFromJar(String jarPath) throws Exception {
        return getClassNameFromJar(new JarInputStream(new FileInputStream(jarPath)));
    }

    // loads all the class in jar file into the main program
    public ArrayList<Class<?>> loadJarFile(String filePath) throws Exception {
        ArrayList<Class<?>> classes = new ArrayList<>();

        ArrayList<String> classNames = getClassNameFromJar(filePath);
        System.out.println(classNames);
//        File f = new File(filePath);
//        ClassLoader parent = this.getClass().getClassLoader();
//        CustomClassLoader classLoader = new CustomClassLoader(parent, filePath);
        File f = new File(filePath);
        URLClassLoader classLoader = new URLClassLoader(new URL[]{f.toURI().toURL()});
        for (String className : classNames) {
            try {
                if (!className.equals("module-info")) {
                    Class<?> c = classLoader.loadClass(className);
                    classes.add(c);
                }
            } catch (ClassNotFoundException e) {
                System.out.println("Class " + className + " was not found!");
            }
        }
        return classes;
    }
}
