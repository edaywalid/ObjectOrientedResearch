package Controller;

import Model.Metric;
import Model.MetricResultWrapper;
import Model.Result;
import Service.Model_Service;
import View.ConsoleView;

import java.util.ArrayList;
import java.util.Map;

public class Metric_Controller {
    public final ConsoleView consoleView;
    public final Model_Service model_service ;

    public Metric_Controller(){
        consoleView = new ConsoleView();
        model_service = new Model_Service();
    }

    public String launch_and_get_file_path(){
        return consoleView.getFilePath();
    }
    private MetricResultWrapper getResults(){
        MetricResultWrapper metricResultWrapper = new MetricResultWrapper();
        String file_path = launch_and_get_file_path();
        Map<String , ArrayList<Metric>> getMetric = model_service.getMetrics();
        for (Map.Entry<String , ArrayList<Metric>> entry : getMetric.entrySet()){
            String metricType = entry.getKey();
            ArrayList<Metric> metrics = entry.getValue();
            ArrayList<Result> results = new ArrayList<Result>();
            for (Metric metric : metrics){
                Result result = metric.execute(file_path);
                results.add(result);
            }
            metricResultWrapper.addMetricResult(metricType, results);
        }
        return metricResultWrapper;
    }

    public void displayResults(){
        MetricResultWrapper metricResultWrapper = getResults();
        Map<String , ArrayList<Result>> metricResults = metricResultWrapper.getMetricResults();
        for (Map.Entry<String , ArrayList<Result>> entry : metricResults.entrySet()){
            String metricType = entry.getKey();
            ArrayList<Result> results = entry.getValue();
            consoleView.printMetricClass(metricType);
            for (Result result : results){
                consoleView.printMetricResult(result.getMetricName(), result.getMetricValue());
            }
        }
    }

}
