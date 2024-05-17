package Metrics;
import Model.ClassLevelMetric;
import Model.Result;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import Metrics.JavaParserAndLexer.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;
import java.util.HashSet;
import java.util.Set;

public class UsedMethodsFromImportsANTLR extends ClassLevelMetric {

    public UsedMethodsFromImportsANTLR(String metricName) {
        super(metricName);
    }

    public int countUsedMethods(String filePath) throws IOException {
        int methodCount = 0;
        Set<String> analyzedFiles = new HashSet<>();
        Stack<String> callStack = new Stack<>();
        analyzeFile(filePath, analyzedFiles, callStack);
        methodCount = callStack.size(); // Final count is the stack size
        return methodCount;
    }

    private void analyzeFile(String filePath, Set<String> analyzedFiles, Stack<String> callStack) throws IOException {
        if (analyzedFiles.contains(filePath)) {
            return; // Already analyzed this file to avoid infinite recursion
        }
        analyzedFiles.add(filePath);

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder codeBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                codeBuilder.append(line).append("\n");
            }

            CharStream charStream = CharStreams.fromString(codeBuilder.toString());
            Java20Lexer lexer = new Java20Lexer(charStream);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            Java20Parser parser = new Java20Parser(tokens);
            ParseTree tree = parser.compilationUnit();

            UsedMethodCountingListener listener = new UsedMethodCountingListener(callStack, analyzedFiles);
            ParseTreeWalker.DEFAULT.walk(listener, tree); // Use ParseTreeWalker to traverse the tree
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public float calculate(String filePath) {
        try {
            return countUsedMethods(filePath);
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public Result execute(String filePath) {
        return new Result(this.metricName, String.valueOf(calculate(filePath)));
    }

    // Custom Listener class to track method calls and imports
    class UsedMethodCountingListener extends Java20ParserBaseListener {
        private final Stack<String> callStack;
        private final Set<String> analyzedFiles; // Pass the analyzedFiles to avoid re-analyzing

        public UsedMethodCountingListener(Stack<String> callStack, Set<String> analyzedFiles) {
            this.callStack = callStack;
            this.analyzedFiles = analyzedFiles;
        }

        @Override
        public void enterMethodInvocation(Java20Parser.MethodInvocationContext ctx) {
            super.enterMethodInvocation(ctx);
            String methodName = ctx.Identifier().getText(); // Use Identifier() to get method name

            // Check if method call is part of a chain
            if (!callStack.isEmpty()) {
                callStack.push(callStack.peek() + "." + methodName); // Nested call, push to stack
            } else {
                callStack.push(methodName); // Top-level call, push only method name
            }

            // Check if method belongs to an imported class based on analyzed files
            String possibleImport = callStack.peek().replace('.', '/'); // Convert to path format
            if (!possibleImport.contains("/") || analyzedFiles.contains(possibleImport + ".java")) {
                // Not an external method or already analyzed file, likely a custom method
                return;
            }
            // Potentially an imported method, increment count
            methodCount++;
        }

        // No need to modify exitMethodInvocation

        public int methodCount = 0; // Track method count for imported methods
    }
}