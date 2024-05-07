package View;

import java.util.Scanner;

public class ConsoleView {
    Scanner scanner = new Scanner(System.in);

    public String getFilePath() {
        System.out.println("Enter the file path: ");
        return scanner.nextLine();
    }

    public void printMetricClass(String metricType) {
        System.out.println("Result For : " + metricType);
    }

    public void printMetricResult(String metricName, String result) {
        System.out.println(metricName + " : " + result);
    }
}
