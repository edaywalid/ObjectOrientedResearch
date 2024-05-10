package Metrics;

import Model.ClassLevelMetric;
import Model.Result;

public class ExampleMetric extends ClassLevelMetric {

    public ExampleMetric(String metricName) {
        super(metricName);
    }

    int result(){
        // This is a dummy method to test the functionality of the test cases
        return 124;
    }

    @Override
    public float calculate(String file_path) {
        return result();
    }

    @Override
    public Result execute(String file_path) {
        return new Result(this.metricName, String.valueOf(this.calculate(file_path)));
    }
}
