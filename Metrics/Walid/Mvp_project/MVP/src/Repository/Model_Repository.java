package Repository;

import Model.ClassLevelMetric;
import Model.Metric;
import Model.PackageLevelMetric;

import java.util.ArrayList;
import java.util.List;

public class Model_Repository{
    ArrayList<Metric> classLevelMetrics ;
    ArrayList<Metric> packageLevelMetrics;


    public Model_Repository(){
        classLevelMetrics = new ArrayList<>(List.of(
                new ClassLevelMetric("LOC", "src/Model/MetricDefinition.java"),
                new ClassLevelMetric("CLOC", "src/Model/MetricDefinition.java"),
                new ClassLevelMetric("NCLOC", "src/Model/MetricDefinition.java"),
                new ClassLevelMetric("NOM", "src/Model/MetricDefinition.java"),
                new ClassLevelMetric("NOF", "src/Model/MetricDefinition.java"),
                new ClassLevelMetric("NOPF", "src/Model/MetricDefinition.java"),
                new ClassLevelMetric("NOS", "src/Model/MetricDefinition.java")
        ));

        packageLevelMetrics = new ArrayList<>(List.of(
               new PackageLevelMetric("DIT", "src/Model/MetricDefinition.java"),
                new PackageLevelMetric("NOC", "src/Model/MetricDefinition.java"),
                new PackageLevelMetric("NOD", "src/Model/MetricDefinition.java")
        ));
    }

    public ArrayList<Metric> getClassLevelMetrics() {
        return classLevelMetrics;
    }

    public ArrayList<Metric> getPackageLevelMetrics() {
        return packageLevelMetrics;
    }
}
