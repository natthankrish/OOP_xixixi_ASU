package program.plugin;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class CustomClassLoader extends ClassLoader {
    private String jarPath;
    public CustomClassLoader(ClassLoader parent, String jarpath) {
        super(parent);
        this.jarPath = jarpath;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
//        File classFile = new File(new File(this.jarPath), name.replace('.', '/') + ".class");
//        System.out.println(classFile.getAbsolutePath());
//        String classPath = this.jarPath + "/" + name.replace(".", "/") + ".class";
//        System.out.println(classPath);
        if (name.equals("module-info")) {
            return null;
        }
//        if (!classFile.exists()) {
//            System.out.println("here");
//            throw new ClassNotFoundException(name);
//        }
        try {
            String url = this.jarPath + "/" + name.replace(".", "/") + ".class";
            System.out.println(url);

            URL myUrl = new URL(url);
            URLConnection connection = myUrl.openConnection();
            InputStream input = connection.getInputStream();
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int data = input.read();

            while(data != -1){
                buffer.write(data);
                data = input.read();
            }

            input.close();

            byte[] classData = buffer.toByteArray();

            return defineClass("reflection.MyObject",
                    classData, 0, classData.length);

        } catch (IOException e) {
            throw new ClassNotFoundException(name, e);
//            return super.loadClass(name);
        }
    }
}
