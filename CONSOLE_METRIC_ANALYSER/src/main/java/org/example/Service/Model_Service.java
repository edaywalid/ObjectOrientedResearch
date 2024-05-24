package org.example.Service;

import org.example.Model.BaseMetric;
import org.example.Model.DefaultMetric.Metric;
import org.example.Repository.Model_Repository;

import java.util.ArrayList;
import java.util.Map;

public class Model_Service {
    private final Model_Repository model_repository;
    private final Map<String , ArrayList<BaseMetric>> getMetrics;
    public Model_Service(){
        model_repository = new Model_Repository();
        getMetrics = Map.of(
                "ClassLevelMetric" , getClassLevelMetrics(),
                "PackageLevelMetric" , getPackageLevelMetrics(),
                "GitLevelMetric" , getGitMetrics()
        );
    }

    private ArrayList<BaseMetric> getClassLevelMetrics(){
        return model_repository.getClassLevelMetrics();
    }

    private ArrayList<BaseMetric> getPackageLevelMetrics(){
        return model_repository.getPackageLevelMetrics();
    }

    private ArrayList<BaseMetric> getGitMetrics(){
        return model_repository.getGitLevelMetrics();
    }

    public Map<String,  ArrayList<BaseMetric>> getMetrics(){
        return getMetrics;
    }
}
