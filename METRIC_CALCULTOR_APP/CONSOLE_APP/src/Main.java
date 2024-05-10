import Controller.Metric_Controller;
import Model.MetricDefinition;
import Model.MetricResultWrapper;
import View.ConsoleView;

public class Main {
    public static void main(String[] args) {
        Metric_Controller metric_controller = new Metric_Controller();
        metric_controller.displayResults();
    }
}