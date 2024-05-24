package Metrics;

import Model.DefaultMetric.ClassLevelMetric;
import Model.Result;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class VariableNamingCoherence extends ClassLevelMetric {
    public VariableNamingCoherence(String metricName) {
        super(metricName);
    }

    @Override
    public float calculate(String file_path) {
         Set<String> variableNames = VariableNamingCoherence.VariableExtractor.extractVariables(file_path);
         List<String> list = new ArrayList<>(variableNames);
         double similarityScore = VariableNamingCoherence.StringSimilarityScorer.calculateScore(list);
         return (float) similarityScore;
    }

    @Override
    public Result execute(String file_path) {
        return new Result(this.metricName, String.valueOf(this.calculate(file_path)));
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
                    variableNames.remove("public");
                    variableNames.remove("static");
                    variableNames.remove("void");
                    variableNames.remove("main");
                    variableNames.remove("String");
                    variableNames.remove("System");
                    variableNames.remove("out");
                    variableNames.remove("println");
                    variableNames.remove("for");
                    variableNames.remove("int");
                    variableNames.remove("String");
                    variableNames.remove("float");
                    variableNames.remove("double");
                    variableNames.remove("char");
                    variableNames.remove("boolean");
                    variableNames.remove("long");
                    variableNames.remove("short");
                    variableNames.remove("byte");
                    variableNames.remove("if");
                    variableNames.remove("else");
                    variableNames.remove("switch");
                    variableNames.remove("case");
                    variableNames.remove("break");
                    variableNames.remove("default");
                    variableNames.remove("continue");
                    variableNames.remove("do");
                    variableNames.remove("while");
                    variableNames.remove("return");
                    variableNames.remove("try");
                    variableNames.remove("catch");
                    variableNames.remove("finally");
                    variableNames.remove("throw");
                    variableNames.remove("throws");
                    variableNames.remove("new");
                    variableNames.remove("this");
                    variableNames.remove("super");
                    variableNames.remove("extends");
                    variableNames.remove("implements");
                    variableNames.remove("interface");
                    variableNames.remove("package");
                    variableNames.remove("import");
                    variableNames.remove("null");
                    variableNames.remove("true");
                    variableNames.remove("false");
                    variableNames.remove("instanceof");
                    variableNames.remove("enum");
                    variableNames.remove("assert");
                    variableNames.remove("transient");
                    variableNames.remove("volatile");
                    variableNames.remove("strictfp");
                    variableNames.remove("const");
                    variableNames.remove("goto");
                    variableNames.remove("synchronized");
                    variableNames.remove("final");
                    variableNames.remove("public");
                    variableNames.remove("protected");
                    variableNames.remove("private");
                    variableNames.remove("static");
                    variableNames.remove("abstract");
                    variableNames.remove("native");
                    variableNames.remove("class");
                    variableNames.remove("interface");
                    variableNames.remove("void");
                    variableNames.remove("main");
                    variableNames.remove("String");
                    variableNames.remove("System");
                    variableNames.remove("out");
                    variableNames.remove("Exception");
                    variableNames.remove("JFrame");
                    variableNames.remove("swing");
                    variableNames.remove("javax");
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
                for (int j = i + 1; j < strings.size()-1; j++) {

                    totalScore += calculateResemblance(strings.get(i), strings.get(j));
                }
            }
            totalPairs= (double) strings.size();
            return totalScore / totalPairs;
        }

        private static double calculateResemblance(String str1, String str2) {
            int lengthDifference = Math.abs(str1.length() - str2.length());
            double lengthScore = 1.0 - (lengthDifference / (double) Math.max(str1.length(), str2.length()));

            int uppercaseCount1 = countUppercaseCharacters(str1);
            int uppercaseCount2 = countUppercaseCharacters(str2);
            double uppercaseScore = 1.0 - (Math.abs(uppercaseCount1 - uppercaseCount2) / (double) Math.max(uppercaseCount1, uppercaseCount2));

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
