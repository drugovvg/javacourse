package part1.lesson10.task01;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class DynamicClassLoader extends ClassLoader {

    public final String classPath;

    /**
     * Constructor
     * @param classPath .class file path
     */
    public DynamicClassLoader(String classPath) {
        this.classPath = classPath;
    }

    protected synchronized Class loadClass(String name,
                                           boolean resolve)
            throws ClassNotFoundException {

        Class result = findClass(name);
        if (resolve) resolveClass(result);

        return result;
    }

    protected Class findClass(String name)
            throws ClassNotFoundException {

        Class result;

        File f = findFile(name, ".class");

        if (f == null) {
            return findSystemClass(name); // try to use a system loader
        }

        try {
            byte[] classBytes = loadFileAsBytes(f);
            result = defineClass(name, classBytes, 0,
                    classBytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException(
                    "Cannot load class " + name + ": " + e);
        } catch (ClassFormatError e) {
            throw new ClassNotFoundException(
                    "Format of class file incorrect for class "
                            + name + " : " + e);
        }

        return result;
    }

    private File findFile(String name, String extension) {
        File f;

        f = new File((new File(classPath)).getPath()
                + File.separatorChar
                + name.replace('/',
                File.separatorChar)
                + extension);
        if (f.exists())
            return f;

        return null;
    }

    public static byte[] loadFileAsBytes(File file)
            throws IOException {
        byte[] result = new byte[(int) file.length()];
        FileInputStream f = new FileInputStream(file);
        try {
            f.read(result, 0, result.length);
        } finally {
            f.close();
        }
        return result;
    }

}
