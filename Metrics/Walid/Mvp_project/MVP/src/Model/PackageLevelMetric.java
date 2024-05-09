package Model;

public class PackageLevelMetric extends MetricDefinition{

    public PackageLevelMetric(String metricName, String Path) {
        this.metricName = metricName;
        this.Path = Path;
    }

    @Override
    public float calculate(String file_path) {
        return 0;
    }

    @Override
    public Result execute(String file_path) {
        return new Result(this.metricName , String.valueOf(calculate(file_path)));
    }
}
