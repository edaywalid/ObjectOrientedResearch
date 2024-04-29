import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


public class NumberOfImportedClassesUsed {
    private final String filePath;

    public NumberOfImportedClassesUsed(String filePath) {
        this.filePath = filePath + ".java";
    }

    public Set<String> getImportedPackages() {
        Set<String> packages = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("import ")) {
                    String packageName = line.substring(7, line.length() - 1); // 7 is the length of "import "
                    if (packageName.endsWith(".*")) {
                        String rootPackage = packageName.substring(0, packageName.length() - 2);
                        // addSubPackages(packages, rootPackage);   // Uncomment this line to include the
                        // subpackages
                        // packages.add(rootPackage);   // Uncomment this line to include the root package in
                        // the imported packages
                        addClassesOfPackage(rootPackage, packages);
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


    public static Set<String> NumberOfUsedPackages(String filePath, Set<String> importedPackages) {
        Set<String> ClassesToSearch = new HashSet<>();
        for (String p : importedPackages) {
            String CurrentClass = p.substring(p.lastIndexOf("/") + 1);
            if (ClassUsed(filePath, CurrentClass)) {
                ClassesToSearch.add(CurrentClass);
            }
        }
        return ClassesToSearch;
    }

    public static boolean ClassUsed(String filePath, String ClassName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(" " + ClassName) && !line.contains("import")) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}