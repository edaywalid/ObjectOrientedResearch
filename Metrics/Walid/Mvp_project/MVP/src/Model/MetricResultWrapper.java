package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MetricResultWrapper {
    private Map<String , ArrayList<Result>> metricResults = new HashMap<>();

    public MetricResultWrapper(){
    }


    public void addMetricResult(String metricType, ArrayList<Result> results){
        metricResults.put(metricType, results);
    }


    public Map<String, ArrayList<Result>> getMetricResults() {
        return metricResults;
    }

}
