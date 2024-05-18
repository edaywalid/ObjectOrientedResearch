package Metrics;

import Model.ClassLevelMetric;
import Model.Result;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class numberOfUsedMethodsFromImports extends ClassLevelMetric {

    public numberOfUsedMethodsFromImports(String metricName) {
        super(metricName);
    }

    public float countUsedMethods(String filePath) throws IOException {
        StringBuilder codeBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                codeBuilder.append(line).append("\n");
            }
        }

        String code = codeBuilder.toString();
        code = removeComments(code);

        int methodCount = countMethodCalls(code);
        return methodCount;
    }

    private static String removeComments(String code) {
        // Regex to remove both single-line and multi-line comments
        String singleLineCommentPattern = "//.*";
        String multiLineCommentPattern = "/\\*.*?\\*/";
        code = code.replaceAll(singleLineCommentPattern, "");
        code = code.replaceAll(multiLineCommentPattern, "");
        return code;
    }

    private static int countMethodCalls(String code) {
        int count = 0;
        Pattern pattern = Pattern.compile("\\b([a-zA-Z0-9_.]+)\\s*\\(");
        Matcher matcher = pattern.matcher(code);

        Stack<Integer> stack = new Stack<>();
        while (matcher.find()) {
            int start = matcher.start();
            int end = findClosingParenthesis(code, start);
            if (end != -1) {
                String methodCall = code.substring(start, end + 1).trim();
                if (!isConstructorCall(methodCall) && isBalanced(methodCall)) {
                    count++;
                }
            }
        }
        return count;
    }

    private static int findClosingParenthesis(String code, int start) {
        Stack<Character> stack = new Stack<>();
        for (int i = start; i < code.length(); i++) {
            char ch = code.charAt(i);
            if (ch == '(') {
                stack.push(ch);
            } else if (ch == ')') {
                stack.pop();
                if (stack.isEmpty()) {
                    return i;
                }
            }
        }
        return -1; // No matching closing parenthesis found
    }

    private static boolean isBalanced(String str) {
        Stack<Character> stack = new Stack<>();
        for (char ch : str.toCharArray()) {
            if (ch == '(') {
                stack.push(ch);
            } else if (ch == ')') {
                if (stack.isEmpty() || stack.pop() != '(') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    private static boolean isConstructorCall(String methodCall) {
        // Regex to detect object instantiations (new keyword followed by class name)
        Pattern constructorPattern = Pattern.compile("\\bnew\\s+([a-zA-Z0-9_]+)\\s*\\(");
        Matcher constructorMatcher = constructorPattern.matcher(methodCall);
        return constructorMatcher.find();
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