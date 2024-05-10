package Repository;

import Metrics.ExampleMetric;
import Metrics.wmc;
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
            new wmc("Weighted Method Count"),
                new ExampleMetric("Example Metric")
        ));

        packageLevelMetrics = new ArrayList<>(List.of(

        ));
    }

    public ArrayList<Metric> getClassLevelMetrics() {
        return classLevelMetrics;
    }

    public ArrayList<Metric> getPackageLevelMetrics() {
        return packageLevelMetrics;
    }
}
