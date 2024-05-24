package MetricsImplementations.src.main.java;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class numberOfUsedMethodsFromImports {

    public static void main(String[] args) {
        String filePath = "/home/nabilkara/metricImpl/ObjectOrientedResearch/Metrics/Nabil/MetricsImplementations/src/test/java/testUsedMethodsImports.java"; // Provide the path to your Java file here
        int methodCount = countUsedMethods(filePath);
        System.out.println("Number of used methods from imports: " + methodCount);
    }

    public static int countUsedMethods(String filePath) {
        int methodCount = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                methodCount += countMethodCalls(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
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
}
