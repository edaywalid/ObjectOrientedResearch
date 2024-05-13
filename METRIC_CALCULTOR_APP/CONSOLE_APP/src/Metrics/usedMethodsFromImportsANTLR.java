package Metrics;

import Model.ClassLevelMetric;
import Model.Result;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class usedMethodsFromImportsANTLR extends ClassLevelMetric {

    public usedMethodsFromImportsANTLR(String metricName) {
        super(metricName);
    }

    public int countUsedMethods(String filePath) throws IOException {
        int methodCount = 0;
        Stack<String> callStack = new Stack<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder codeBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                codeBuilder.append(line).append("\n");
            }

            CharStream charStream = new ANTLRInputStream(codeBuilder.toString());
            JavaLexer lexer = new JavaLexer(charStream);
//            JavaTarget target = new JavaTarget();
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            JavaParser parser = new JavaParser(tokens);
            ParseTree tree = parser.compilationUnit();

            JavaListener listener = new UsedMethodCountingListener(callStack);
            listener.enterCompilationUnit(tree); // Trigger listener
            methodCount = callStack.size(); // Final count is the stack size
        } catch (IOException e) {
            e.printStackTrace();
        }
        return methodCount;
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

// Custom Listener class to track method calls
class UsedMethodCountingListener extends JavaBaseListener {

    private final Stack<String> callStack;

    public UsedMethodCountingListener(Stack<String> callStack) {
        this.callStack = callStack;
    }

    @Override
    public void enterMethodCall(JavaParser.MethodCallContext ctx) {
        super.enterMethodCall(ctx);

        String className = ctx.expressionName().getText();
        String methodName = ctx.IDENTIFIER().getText();

        // Check if method call is part of a chain
        if (!callStack.isEmpty() && !className.equals(callStack.peek())) {
            callStack.push(className + "." + methodName); // Nested call, push to stack
        } else {
            callStack.push(methodName); // Top-level call, push only method name
        }
    }

    @Override
    public void exitMethodCall(JavaParser.MethodCallContext ctx) {
        super.exitMethodCall(ctx);
        callStack.pop(); // Pop the method call from the stack
    }
}

