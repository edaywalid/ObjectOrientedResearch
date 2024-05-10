package Metrics;

import Model.ClassLevelMetric;
import Model.MetricDefinition;
import Model.Result;

public class wmc extends ClassLevelMetric {
    public wmc(String metricName) {
        super(metricName);
    }

    @Override
    public float calculate(String file_path) {
        return 122;
    }

    @Override
    public Result execute(String file_path) {
        return new Result(this.metricName, String.valueOf(this.calculate(file_path)));
    }
}
