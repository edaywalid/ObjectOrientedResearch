package Metrics;
import Model.ClassLevelMetric;
import Model.Result;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class numberOfUsedMethodsFromImports extends ClassLevelMetric {

    public numberOfUsedMethodsFromImports(String metricName) {
        super(metricName);
    }

    public float countUsedMethods(String filePath) throws IOException {
        int methodCount = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                methodCount += countMethodCalls(line);
            }
        }
        return methodCount;
    }

    private static int countMethodCalls(String line) {
        int count = 0;

        // Improved pattern to capture method calls (including static imports)
        Pattern pattern = Pattern.compile("\\b([a-zA-Z0-9_.]+)(\\.)([a-zA-Z0-9_]+)\\(.*\\)"); // Captures class, method name, and arguments

        Matcher matcher = pattern.matcher(line);
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
        return new Result("Used Methods From Imports", String.valueOf(calculate(file_path)));
    }
}
