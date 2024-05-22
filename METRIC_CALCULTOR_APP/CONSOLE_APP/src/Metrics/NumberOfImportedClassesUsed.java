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
    NumberOfImportedClasses NOIC =
        new NumberOfImportedClasses("Number of Imported Classes");
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
      String line;
      boolean inComment = false;
      boolean inString = false;
      while ((line = reader.readLine()) != null) {
        line = line.trim();
        if (line.startsWith("//")) {
            continue; // Skip the line if it is a comment
        }
        if (line.contains("\"")) {
          inString = !inString; // Toggle the inString flag
        }
        if (line.contains("/*")) {
          inComment = true; // Start of the block comment
        }
        if (inComment) {
          if (line.contains("*/")) {
            inComment = false; // End of the block comment
          }
          continue;
        }
        if (line.contains(ClassName) && !line.contains("import")) {
          if (inString) {
            continue;
          }
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
