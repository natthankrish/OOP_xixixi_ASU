package program.plugin;

import java.io.*;

public class CustomClassLoader extends ClassLoader {
    private String jarPath;
    public CustomClassLoader(ClassLoader parent, String jarpath) {
        super(parent);
        this.jarPath = jarpath;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        String classPath = this.jarPath + File.separator + name.replace(".", File.separator) + ".class";
        try (InputStream input = new FileInputStream(classPath)) {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int data = input.read();

            while (data != -1) {
                buffer.write(data);
                data = input.read();
            }

            byte[] classData = buffer.toByteArray();

            return defineClass(name, classData, 0, classData.length);
        } catch (IOException e) {
            return super.loadClass(name);
        }
    }
}
