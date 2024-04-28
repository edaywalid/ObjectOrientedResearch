import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    String[] classNames = listClassesInPackage("java.io");
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

    try (BufferedReader reader = new BufferedReader(new FileReader(javaHome + "/lib/classlist"))) {
      String line;
      while ((line = reader.readLine()) != null) {
        line = line.trim();
        if (line.startsWith(packagePath) && !line.contains("$")) {
          classNames.add(line);
        }
      }

    } catch (IOException e) {
      e.printStackTrace();
    }

    return classNames.toArray(new String[0]);
  }
}
