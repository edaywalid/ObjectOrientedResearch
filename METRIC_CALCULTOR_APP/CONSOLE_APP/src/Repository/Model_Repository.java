package Repository;

import Metrics.ExampleMetric;
import Metrics.NumberOfImportedClasses;
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
                new NumberOfImportedClasses("Number of Imported Classes")));

    packageLevelMetrics = new ArrayList<>(List.of());
  }

  public ArrayList<Metric> getClassLevelMetrics() {
    return classLevelMetrics;
  }

  public ArrayList<Metric> getPackageLevelMetrics() {
    return packageLevelMetrics;
  }
}
