package org.example.Model.gitMetric;

import org.example.Model.Result;

public abstract class gitMetricDefinition implements gitMetric {
    protected String metric_name ;

    @Override
    public float calculate(String... paths) {
        if (paths.length != 2) {
            throw new IllegalArgumentException("Expected exactly 2 paths: gitFilePath and fileName");
        }
        return calculate(paths[0], paths[1]);
    }

    @Override
    public Result execute(String... paths) {
        if (paths.length != 2) {
            throw new IllegalArgumentException("Expected exactly 2 paths: gitFilePath and fileName");
        }
        return execute(paths[0], paths[1]);
    }
}
