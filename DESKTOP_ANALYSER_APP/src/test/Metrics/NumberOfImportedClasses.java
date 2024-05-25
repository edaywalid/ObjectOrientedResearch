package test.Metrics;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class NumberOfImportedClasses {
  // Default constructor :
  public String name;
  public NumberOfImportedClasses(String metricName) {
    name =metricName;
  }



  public Set<String> getImportedPackages(String filePath) {
    // ArrayList
    // MathContext

    /*
    ArrayList<String> packages = new ArrayList<>();
    MathContext mc = new MathContext(0);
    */


    Set<String> packages = new HashSet<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = reader.readLine()) != null) {
        line = line.trim();
        if (line.startsWith("import ")) {
          String packageName = line.substring(7, line.length() - 1); // 7 is the length of "import "
          if (packageName.endsWith(".*")) {
            String rootPackage = packageName.substring(0, packageName.length() - 2);
            addClassesOfPackage(rootPackage, packages);
            // addSubPackages(packages, rootPackage);   // Uncomment this line to include the
            // subpackages
            // packages.add(rootPackage);   // Uncomment this line to include the root package in
            // the imported packages
          } else {
            packages.add(packageName.replace(".", "/"));
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    return packages;
  }

  // Method to add the classes of a package to the imported packages :
  public void addClassesOfPackage(String packageName, Set<String> importedPackages) {
    String packagePath = packageName.replace('.', '/');
    String javaHome = System.getProperty("java.home");

    try (BufferedReader reader = new BufferedReader(new FileReader(javaHome + "/lib/classlist"))) {
      String line;
      while ((line = reader.readLine()) != null) {
        line = line.trim();
        if (line.startsWith(packagePath) && !line.contains("$")) {
          line = line.substring(packagePath.length() + 1);
          if (!line.contains("/")) {
            importedPackages.add(line);
          }
        }
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // Methods to override: calculate and execute
  public float calculate(String file_path) {
    try {
      return this.getImportedPackages(file_path).size();
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }


}
