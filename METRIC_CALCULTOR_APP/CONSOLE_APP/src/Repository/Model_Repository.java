package Repository;

import Metrics.*;
import Model.Metric;
import java.util.ArrayList;
import java.util.List;

public class Model_Repository {
  ArrayList<Metric> classLevelMetrics;
  ArrayList<Metric> packageLevelMetrics;

  public Model_Repository() {
    classLevelMetrics =
        new ArrayList<>(
            List.of(
                    new ExampleMetric("Example Metric"),
                    new NumberOfImportedClasses("Number of Imported Classes"),
                    new NumberOfImportedClassesUsed("Number of Used Imported Classes"),
                    new handledExceptions("Number Of Handled Exceptions"),
                    new InterfaceCounter("Number of Implemented Interfaces"),
<<<<<<< HEAD
                    new numberOfUsedMethodsFromImports("Number of Used Methods From Imports")
=======
                    new numberOfUsedMethodsFromImports("Number of Used Methods From Imports"),
                    new testUsedMethodsFromImports("Test used methods")
//                    new usedMethodsFromImportsANTLR("ANTLR")
>>>>>>> 6427c1f (FrequenceClassModifcation)
                    ));

    packageLevelMetrics = new ArrayList<>(List.of());
  }

  public ArrayList<Metric> getClassLevelMetrics() {
    return classLevelMetrics;
  }

  public ArrayList<Metric> getPackageLevelMetrics() {
    return packageLevelMetrics;
  }
}
