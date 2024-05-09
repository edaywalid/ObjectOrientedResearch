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

  public Set<String> getNumberOfUsedClasses() {
    NumberOfImportedClasses NOIC =
        new NumberOfImportedClasses(filePath.substring(0, filePath.lastIndexOf(".")));
    Set<String> importedPackages = NOIC.getImportedPackages();
    Set<String> ClassesToSearch = new HashSet<>();
    for (String p : importedPackages) {
      String CurrentClass = p.substring(p.lastIndexOf("/") + 1);
      if (ClassUsed(CurrentClass)) {
        ClassesToSearch.add(CurrentClass);
      }
    }
    return ClassesToSearch;
  }

  public boolean ClassUsed(String ClassName) {
    try (BufferedReader reader = new BufferedReader(new FileReader(this.filePath))) {
      String line;
      boolean inComment = false;
      boolean inString = false;
      while ((line = reader.readLine()) != null) {
        line = line.trim();
        if (line.contains("/*")) {
          inComment = true; // Start of the block comment
        }
        if (inComment) {
          if (line.contains("*/")) {
            inComment = false; // End of the block comment
          }
          continue;
        }
        if (line.startsWith(ClassName)) {
          return true;
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return false;
  }
}
