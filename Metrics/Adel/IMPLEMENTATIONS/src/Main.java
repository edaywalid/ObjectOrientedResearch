import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Main {

  public static void main(String[] args) {
    String filePath = args[0] + ".java";
    Set<String> importedPackages = getImportedPackages(filePath);

    // Uncomment the following loop to print the imported packages
    // System.out.println("\t------------- Imported Packages -------------");
    // for (String p : importedPackages) {
    //   System.out.println("[" + p + "]");
    // }
    System.out.println("number of imported packages: " + importedPackages.size());
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
            // packages.add(rootPackage);   // Uncomment this line to include the root package in
            // the imported packages
            addClassesOfPackage(rootPackage, packages);
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
        // subPackages.add(p.getName());    // Uncomment this line to include the subpackage in the
        // imported packages
        addClassesOfPackage(p.getName(), subPackages);
        addSubPackages(subPackages, p.getName());
      }
    }
  }

  public static void addClassesOfPackage(String packageName, Set<String> importedPackages) {
    String packagePath = packageName.replace('.', '/');
    String javaHome = System.getProperty("java.home");

    try (BufferedReader reader = new BufferedReader(new FileReader(javaHome + "/lib/classlist"))) {
      String line;
      while ((line = reader.readLine()) != null) {
        line = line.trim();
        if (line.startsWith(packagePath) && !line.contains("$")) {
          importedPackages.add(line);
        }
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
