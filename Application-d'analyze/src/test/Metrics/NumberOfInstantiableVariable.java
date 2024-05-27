package test.Metrics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberOfInstantiableVariable  {
    String name;
    public NumberOfInstantiableVariable(String metricName) {
        name = metricName;
    }

    public float calculate(String file_path) {
        try {
            return countNonPrimitiveVariables(file_path);
        } catch (IOException e) {
            return -1;
        }
    }

    public static int countNonPrimitiveVariables(String filename) throws IOException {
        int nonPrimitiveVariableCount = 0;
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            nonPrimitiveVariableCount += countNonPrimitiveVariablesInLine(line);
        }
        reader.close();
        return nonPrimitiveVariableCount;
    }

    public static int countNonPrimitiveVariablesInLine(String line) {
        int count = 0;
        // Match variable declarations: ClassName variableName;
        Pattern pattern = Pattern.compile("\\b(?!int|long|float|double|boolean|char|byte|short)\\w+\\b\\s+\\w+\\s*[;,=]");
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            count++;
        }
        // Match variable declarations with generic type: ClassName<...> variableName;
        pattern = Pattern.compile("\\b(?!int|long|float|double|boolean|char|byte|short)\\w+\\b\\s*<.*?>\\s+\\w+\\s*[;,=]");
        matcher = pattern.matcher(line);
        while (matcher.find()) {
            count++;
        }
        return count;
    }
}
