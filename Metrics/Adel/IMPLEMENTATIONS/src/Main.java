import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String filePath = args[0] + ".java";
        Set<String> importedPackages = getImportedPackages(filePath);
        System.out.println("\t------------- Imported Packages -------------");
        for (String p : importedPackages) {
            System.out.println("[" + p + "]");
        }
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

}