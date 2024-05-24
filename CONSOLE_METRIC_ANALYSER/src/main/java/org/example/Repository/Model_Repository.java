package org.example.Repository;

import org.example.Metrics.*;
import org.example.Model.BaseMetric;
import java.util.ArrayList;
import java.util.List;

public class Model_Repository {
  ArrayList<BaseMetric> classLevelMetrics;
  ArrayList<BaseMetric> packageLevelMetrics;
  ArrayList<BaseMetric> gitLevelMetrics;

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
//                    new RUEMetric("CPU usage"),  // CPU USAGE COUNT DOESNT WORK IN WINDOWS
//                    new AUC("Attribute Usage Count") ,
                    new NumberOfInstantiableVariable("Number of Instantiable Variables")
//                    new VariableNamingCoherence("Variable Naming Coherence")
            ));

    packageLevelMetrics = new ArrayList<>(List.of());
    gitLevelMetrics = new ArrayList<>(List.of(
//            new ClassModificationFrequency("Class Modification Frequency")
    ));
  }

  public ArrayList<BaseMetric> getClassLevelMetrics() {
    return classLevelMetrics;
  }

  public ArrayList<BaseMetric> getGitLevelMetrics() {return gitLevelMetrics;}

  public ArrayList<BaseMetric> getPackageLevelMetrics() {
    return packageLevelMetrics;
  }
}
