package Metrics;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class DynamicClassLoading{

    public static Class<?> DynamicClassLoad(String filePath) throws Exception {
        String UnixStylefilePath = filePath.replace("\\", "/");
        compileJavaFile(filePath);
        File classFile = new File(filePath);
        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{classFile.toURI().toURL()});
        String fileName;
        fileName = UnixStylefilePath.split("/")[filePath.split("/").length - 1];
        return classLoader.loadClass(fileName.split("\\.")[0]);
    }
    public static void compileJavaFile(String filePath) throws Exception {
//        Process compileProcess = Runtime.getRuntime().exec("javac " + filePath);
      Process compileProcess =  Runtime.getRuntime().exec("java -classpath " + filePath.replace("\\", "/") + ";");

        int exitCode = compileProcess.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("Compilation failed.");
        }
    }
}