package org.example.View;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleView {
    Scanner scanner = new Scanner(System.in);

    public ArrayList<String> getFilePaths() {
        ArrayList<String> filePaths = new ArrayList<>();

        System.out.println("Enter the git repository path: ");
        filePaths.add(scanner.nextLine());

        System.out.println("Enter the file path : ");
        filePaths.add(scanner.nextLine());

        // if it is a windows file path convert the path to unix style
        filePaths.replaceAll(s -> s.replace("\\", "/"));

        return filePaths;
    }

    public void printMetricClass(String metricType) {
        System.out.println("\n========== " + metricType + " ==========");
    }

    public void printMetricResult(String metricName, String result) {
        System.out.println(metricName + " ---> " + result);
    }
}
