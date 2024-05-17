import Controller.Metric_Controller;
import Metrics.FrequenceClassModifications;
import Model.MetricDefinition;
import Model.MetricResultWrapper;
import View.ConsoleView;

public class Main {
//    public static void main(String[] args) {
//        Metric_Controller metric_controller = new Metric_Controller();
//        metric_controller.displayResults();
//    }

    public static void main(String[] args) {
        FrequenceClassModifications metric = new FrequenceClassModifications("FrequenceClassModifications");
        metric.result("/home/abderrahmane/Documents/ObjectOrientedResearch");
//        System.out.println(metric.execute("src/test/java/Model/Result.java", "src/test/java/Model"));
    }
}