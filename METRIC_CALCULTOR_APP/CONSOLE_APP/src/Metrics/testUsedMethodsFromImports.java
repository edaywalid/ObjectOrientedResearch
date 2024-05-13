package Metrics;
import Model.ClassLevelMetric;
import Model.Result;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;
public class testUsedMethodsFromImports extends ClassLevelMetric {

    public testUsedMethodsFromImports(String metricName) {
        super(metricName);
    }

    public float countUsedMethods(String filePath) throws IOException {
        int methodCount = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder codeLine = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                codeLine.append(line).append("\n");
            }
            methodCount += countMethodCalls(codeLine.toString());
        }
        return methodCount;
    }

    private static int countMethodCalls(String code) {
        int count = 0;

        // Pattern to capture method calls, including those used as parameters
        Pattern pattern = Pattern.compile("\\b([a-zA-Z0-9_.]+)(?:\\.[a-zA-Z0-9_]+)+\\(.*?\\)");

        Matcher matcher = pattern.matcher(code);
        while (matcher.find()) {
            count++;
        }
        return count;
    }

    @Override
    public float calculate(String file_path) {
        try {
            return countUsedMethods(file_path);
        } catch (IOException e) {
            return -1;
        }
    }

    @Override
    public Result execute(String file_path) {
        return new Result(this.metricName, String.valueOf(calculate(file_path)));
    }
}

