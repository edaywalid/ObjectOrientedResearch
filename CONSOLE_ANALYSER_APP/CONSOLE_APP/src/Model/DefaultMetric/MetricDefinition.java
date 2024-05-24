package Model.DefaultMetric;

import Model.Result;

public abstract class MetricDefinition implements Metric{
    protected String metricName ;

    @Override
    public float calculate(String... paths) {
        if (paths.length != 1) {
            throw new IllegalArgumentException("Expected exactly 1 path: file_path");
        }
        return calculate(paths[0]);
    }

    @Override
    public Result execute(String... paths) {
        if (paths.length != 1) {
            throw new IllegalArgumentException("Expected exactly 1 path: file_path");
        }
        return execute(paths[0]);
    }
}
