package Model;

public class Result {
    private String metric_name;
    private String metric_value;

    public Result(String metric_name, String metric_value) {
        this.metric_name = metric_name;
        this.metric_value = metric_value;
    }

    public String getMetricName() {
        return metric_name;
    }

    public String getMetricValue() {
        return metric_value;
    }

    public void setMetricName(String metric_name) {
        this.metric_name = metric_name;
    }

    public void setMetricValue(String metric_value) {
        this.metric_value = metric_value;
    }

    @Override
    public String toString() {
        return metric_name + ": " + metric_value;
    }
}
