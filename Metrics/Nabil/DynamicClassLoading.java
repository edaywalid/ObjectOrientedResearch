import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class DynamicClassLoading{

    public static void main(String[] args) throws Exception {
        String filePath = "/home/nabilkara/ObjectOrientedResearch/Metrics/Nabil/test.java";
        compileJavaFile(filePath);
        File classFile = new File(filePath);
        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{classFile.toURI().toURL()});
        String fileName =  filePath.split("/")[filePath.split("/").length - 1];
        Class<?> clazz = classLoader.loadClass(fileName.split("\\.")[0]);
        System.out.println(InterfaceCounter.countInterfaces(clazz));
   }
    private static void compileJavaFile(String filePath) throws Exception {
        Process compileProcess = Runtime.getRuntime().exec("javac " + filePath);
        int exitCode = compileProcess.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("Compilation failed.");
        }
    }
}