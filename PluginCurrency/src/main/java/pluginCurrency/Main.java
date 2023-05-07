package pluginCurrency;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.net.URL;
import java.io.File;
import java.lang.reflect.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import org.example.program.entities.Product;

public class Main {

    public static ArrayList<String> getClassNameFromJar(JarInputStream jarFile) throws Exception {
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

    public static ArrayList<String> getClassNameFromJarString(String jarPath) throws Exception {
        return getClassNameFromJar(new JarInputStream(new FileInputStream(jarPath)));
    }

    public ArrayList<Class<?>> loadJarFile(String filePath) throws Exception {
        ArrayList<Class<?>> classes = new ArrayList<>();

        ArrayList<String> classNames = getClassNameFromJarString(filePath);
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

    public static void main(String[] args) throws Exception{

        ArrayList<String> test = getClassNameFromJarString( new java.io.File("").getAbsolutePath() + "/PluginCurrency/target/PluginCurrency-1.0-SNAPSHOT.jar");
        for (String s : test){
            System.out.println(s);
        }
//        System.out.println("Hello world!");
//        Product p = new Product(20, 5, "Hehe", 40000.0, 25000.0, "test", "", true);
//        p.display();

    }
}