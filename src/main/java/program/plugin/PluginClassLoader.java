package program.plugin;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;

public class PluginClassLoader extends ClassLoader{
    private File pluginDirectory;

    public PluginClassLoader(File pluginDirectory) {
        this.pluginDirectory = pluginDirectory;
    }

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        File classFile = new File(pluginDirectory, name.replace('.', '/') + ".class");
        if (!classFile.exists()) {
            throw new ClassNotFoundException(name);
        }
        try {
            byte[] bytes = Files.readAllBytes(classFile.toPath());
            return defineClass(name, bytes, 0, bytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException(name, e);
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        PluginClassLoader plugin = new PluginClassLoader(new File("src/main/program/plugin/"));
//        Class<?> pluginClass = Class.forName("Run", true, plugin);
        Class<?> pluginClass = plugin.findClass("Run");
        Object plugin2 = pluginClass.newInstance();

        // Call a method on the plugin instance
        Method method = pluginClass.getMethod("run");
        method.invoke(plugin2);
    }
}

