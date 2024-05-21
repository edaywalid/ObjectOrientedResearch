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
                    new NumberOfImportedClasses("Number of Imported Classes"),
                    new NumberOfImportedClassesUsed("Number of Used Imported Classes"),
                    new handledExceptions("Number Of Handled Exceptions"),
                    new NumberOfInterfacesImplemented("Number of Interfaces Implemented"),
                    new numberOfUsedMethodsFromImports("Number of Used Methods From Imports"),
                    new WebImportCounter("Web Import Counter"),
//                    new RUEMetric("CPU usage")
                    new AUC("Attribute Usage Count") , // does not work in windows,
                    new NumberOfInstantiableVariable("Number of Instantiable Variables"),
                    new VariableNamingCoherence("Variable Naming Coherence")
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
