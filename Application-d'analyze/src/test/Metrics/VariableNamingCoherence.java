package test.Metrics;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class VariableNamingCoherence {
    String name;
    public VariableNamingCoherence(String metricName) {
        name=metricName;
    }

    public float calculate(String file_path) {
         Set<String> variableNames = VariableNamingCoherence.VariableExtractor.extractVariables(file_path);
         List<String> list = new ArrayList<>(variableNames);
         double similarityScore = VariableNamingCoherence.StringSimilarityScorer.calculateScore(list);
         return (float) similarityScore;
    }

    

    static class VariableExtractor {

        public static Set<String> extractVariables(String filePath) {

            Set<String> variableNames = new LinkedHashSet<>();
            Pattern variablePattern = Pattern.compile("\\b([a-zA-Z_$][a-zA-Z0-9_$]*)\\b");

            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = br.readLine()) != null) {
                    Matcher matcher = variablePattern.matcher(line);
                    while (matcher.find()) {
                        variableNames.add(matcher.group(1));
                    }
                    variableNames.removeAll(Arrays.asList(
                        "public", "static", "void", "main", "String", "System", "out", "println",
                        "for", "int", "float", "double", "char", "boolean", "long", "short", "byte",
                        "if", "else", "switch", "case", "break", "default", "continue", "do", "while",
                        "return", "try", "catch", "finally", "throw", "throws", "new", "this", "super",
                        "extends", "implements", "interface", "package", "import", "null", "true",
                        "false", "instanceof", "enum", "assert", "transient", "volatile", "strictfp",
                        "const", "goto", "synchronized", "final", "protected", "private", "abstract",
                        "native", "class", "Exception", "JFrame", "swing", "javax"
                    ));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return variableNames;
        }
    }

    static class StringSimilarityScorer {

        public static double calculateScore(List<String> strings) {
            if (strings.isEmpty() || strings.size() == 1) {
                return 1.0;
            }

            double totalPairs = 0;
            double totalScore = 0.0;

            for (int i = 0; i < strings.size(); i++) {
                for (int j = i + 1; j < strings.size(); j++) {
                    totalScore += calculateResemblance(strings.get(i), strings.get(j));
                    totalPairs++;
                }
            }

            return totalScore / totalPairs;
        }

        private static double calculateResemblance(String str1, String str2) {
            int lengthDifference = Math.abs(str1.length() - str2.length());
            double lengthScore = 1.0 - (lengthDifference / (double) Math.max(str1.length(), str2.length()));

            int uppercaseCount1 = countUppercaseCharacters(str1);
            int uppercaseCount2 = countUppercaseCharacters(str2);
            double uppercaseScore = 1.0;
            if (uppercaseCount1 != 0 || uppercaseCount2 != 0) {
                uppercaseScore = 1.0 - (Math.abs(uppercaseCount1 - uppercaseCount2) / (double) Math.max(uppercaseCount1, uppercaseCount2));
            }

            // The final score is the average of the length score and the uppercase score
            return (lengthScore + uppercaseScore) / 2.0;
        }

        private static int countUppercaseCharacters(String str) {
            int uppercaseCount = 0;
            for (char c : str.toCharArray()) {
                if (Character.isUpperCase(c)) {
                    uppercaseCount++;
                }
            }
            return uppercaseCount;
        }
    }
}