package Metrics;

import Model.DefaultMetric.ClassLevelMetric;
import Model.Result;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class NumberOfImportedClassesUsed extends ClassLevelMetric {
  public NumberOfImportedClassesUsed(String Name) {
    super(Name);
  }

  public Set<String> getNumberOfUsedClasses(String filePath) {
    NumberOfImportedClasses NOIC = new NumberOfImportedClasses("Number of Imported Classes");
    Set<String> importedPackages = NOIC.getImportedPackages(filePath);
    Set<String> ClassesToSearch = new HashSet<>();
    for (String p : importedPackages) {
      String CurrentClass = p.substring(p.lastIndexOf("/") + 1);
      if (ClassUsed(CurrentClass, filePath)) {
        ClassesToSearch.add(CurrentClass);
      }
    }
    return ClassesToSearch;
  }

  public boolean ClassUsed(String ClassName, String filePath) {
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
      String line, lineTrimmed;
      boolean inComment = false;
      while ((line = reader.readLine()) != null) {
        lineTrimmed = line.trim();
        if (lineTrimmed.startsWith("//")) {
          continue; // Skip the line if it is a comment
        }
        if (lineTrimmed.startsWith("/*")) {
          inComment = true; // Start of the block comment
        }
        if (inComment) {
          if (lineTrimmed.contains("*/") || lineTrimmed.startsWith("*/")) {
            inComment = false; // End of the block comment
          }
          continue;
        }
        if (line.contains(" " + ClassName) && !lineTrimmed.startsWith("import")) {
          return true;
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public float calculate(String file_path) {
    try {
      return this.getNumberOfUsedClasses(file_path).size();
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public Result execute(String file_path) {
    return new Result(this.metricName, String.valueOf(this.calculate(file_path)));
  }
}
