package Service;

import Model.ClassLevelMetric;
import Model.Metric;
import Model.MetricDefinition;
import Model.PackageLevelMetric;
import Repository.Model_Repository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

public class Model_Service {
    private final Model_Repository model_repository;
    private final Map<String , ArrayList<Metric>> getMetrics;
    public Model_Service(){
        model_repository = new Model_Repository();
        getMetrics = Map.of(
                "ClassLevelMetric" , getClassLevelMetrics(),
                "PackageLevelMetric" , getPackageLevelMetrics()
        );
    }

    private ArrayList<Metric> getClassLevelMetrics(){
        return model_repository.getClassLevelMetrics();
    }

    private ArrayList<Metric> getPackageLevelMetrics(){
        return model_repository.getPackageLevelMetrics();
    }

    public Map<String,  ArrayList<Metric>> getMetrics(){
        return getMetrics;
    }
}
