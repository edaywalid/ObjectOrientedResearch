import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Main {

  public static void main(String[] args) {
    String filePath = args[0] + ".java";
    Set<String> importedPackages = getImportedPackages(filePath);
    System.out.println("\t------------- Imported Packages -------------");
    for (String p : importedPackages) {
      System.out.println("[" + p + "]");
    }
    System.out.println("number of imported packages: " + importedPackages.size());

    System.out.println("\n\n\t------------- Classes in java.io package -------------");
    String[] classNames = listClassesInPackage("java.util");
    for (String className : classNames) {
      System.out.println(className);
    }
    System.out.println("Total classes: " + classNames.length);
  }

  public static Set<String> getImportedPackages(String filePath) {
    Set<String> packages = new HashSet<>();

    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = reader.readLine()) != null) {
        line = line.trim();
        if (line.startsWith("import ")) {
          String packageName = line.substring(7, line.length() - 1); // 7 is the length of "import "
          if (packageName.endsWith(".*")) {
            String rootPackage = packageName.substring(0, packageName.length() - 2);
            addSubPackages(packages, rootPackage);
            packages.add(rootPackage);
          } else {
            packages.add(packageName);
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    return packages;
  }

  // This method is used to count the number of subpackages in a given package
  public static void addSubPackages(Set<String> subPackages, String packageName) {
    Package[] packages = Package.getPackages();
    for (Package p : packages) {
      if (p.getName().startsWith(packageName + ".")) {
        subPackages.add(p.getName());
        addSubPackages(subPackages, p.getName());
      }
    }
  }

  public static String[] listClassesInPackage(String packageName) {
    List<String> classNames = new ArrayList<>();
    String packagePath = packageName.replace('.', '/');
    String javaHome = System.getProperty("java.home");
    File jreLibDir = new File(javaHome, "lib");

    // Search for .class files in the specified package within JRE lib directory
    findClassesInDirectory(jreLibDir, packagePath, classNames);

    return classNames.toArray(new String[0]);
  }

  private static void findClassesInDirectory(
      File directory, String packagePath, List<String> classNames) {
    if (!directory.exists() || !directory.isDirectory()) {
      return;
    }

    File[] files = directory.listFiles();
    if (files == null) {
      return;
    }

    for (File file : files) {
      if (file.isDirectory()) {
        findClassesInDirectory(file, packagePath, classNames);
      } else if (file.isFile() && file.getName().endsWith(".jar")) {
        scanJarFileForClasses(file, packagePath, classNames);
      }
    }
  }

  private static void scanJarFileForClasses(
      File jarFile, String packagePath, List<String> classNames) {
    try (JarFile jar = new JarFile(jarFile)) {
      Enumeration<JarEntry> entries = jar.entries();
      while (entries.hasMoreElements()) {
        JarEntry entry = entries.nextElement();
        String entryName = entry.getName();
        if (entryName.startsWith(packagePath) && entryName.endsWith(".class")) {
          String className = entryName.substring(0, entryName.length() - 6).replace('/', '.');
          classNames.add(className);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
