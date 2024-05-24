package org.example.Controller;

import org.example.Model.BaseMetric;
import org.example.Model.DefaultMetric.Metric;
import org.example.Model.MetricResultWrapper;
import org.example.Model.Result;
import org.example.Model.gitMetric.gitMetricDefinition;
import org.example.Service.Model_Service;
import org.example.View.ConsoleView;

import java.util.ArrayList;
import java.util.Map;

public class Metric_Controller {
    public final ConsoleView consoleView;
    public final Model_Service model_service;

    public Metric_Controller() {
        consoleView = new ConsoleView();
        model_service = new Model_Service();
    }

    public ArrayList<String> launch_and_get_file_paths() {
        return consoleView.getFilePaths();
    }


    private MetricResultWrapper getResults() {
        MetricResultWrapper metricResultWrapper = new MetricResultWrapper();
        Map<String, ArrayList<BaseMetric>> getMetric = model_service.getMetrics();
        ArrayList<String> file_paths = launch_and_get_file_paths();
        String filePath = file_paths.get(1);
        for (Map.Entry<String, ArrayList<BaseMetric>> entry : getMetric.entrySet()) {
            String metricType = entry.getKey();
            ArrayList<BaseMetric> metrics = entry.getValue();
            ArrayList<Result> results = new ArrayList<>();

            for (BaseMetric metric : metrics) {
                Result result;
                if (metric instanceof gitMetricDefinition) {
                    String gitFilePath = file_paths.get(0);
                    String fileName = file_paths.get(1).substring(file_paths.get(1).lastIndexOf("/")+1);
                    result = ((gitMetricDefinition) metric).execute(gitFilePath, fileName);
                } else if (metric instanceof Metric) {

                    result = ((Metric) metric).execute(filePath);
                } else {
                    throw new IllegalArgumentException("Unknown metric type");
                }
                results.add(result);
            }
            metricResultWrapper.addMetricResult(metricType, results);
        }

        return metricResultWrapper;
    }

    public void displayResults() {
        MetricResultWrapper metricResultWrapper = getResults();
        Map<String, ArrayList<Result>> metricResults = metricResultWrapper.getMetricResults();

        for (Map.Entry<String, ArrayList<Result>> entry : metricResults.entrySet()) {
            String metricType = entry.getKey();
            ArrayList<Result> results = entry.getValue();
            consoleView.printMetricClass(metricType);
            for (Result result : results) {
                consoleView.printMetricResult(result.getMetricName(), result.getMetricValue());
            }
        }
    }

}
