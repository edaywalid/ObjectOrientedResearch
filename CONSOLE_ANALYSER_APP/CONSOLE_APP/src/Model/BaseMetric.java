package Model;

public interface BaseMetric {
    float calculate(String... paths);
    Result execute(String... paths);
}
