import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class AttributeUsageCounter {
    private Map<String, Integer> attributeUsageCount = new HashMap<>();
    public void countAttributeUsage(String filePath) throws FileNotFoundException {
        FileInputStream in = new FileInputStream(filePath);
        //System.out.println("Processing file: " + filePath);
        JavaParser javaParser = new JavaParser();
        CompilationUnit cu = javaParser.parse(in).getResult().orElseThrow();
        cu.accept(new VoidVisitorAdapter<Object>() {
            @Override
            public void visit(ClassOrInterfaceDeclaration n, Object arg) {
            //    System.out.println("Visiting class: " + n.getName());
                for (MethodDeclaration method : n.getMethods()) {
                    System.out.println("Visiting method: " + method.getName());
                    method.accept(this, null);
                }
                super.visit(n, arg);
            }

            @Override
            public void visit(FieldDeclaration n, Object arg) {
                for (VariableDeclarator var : n.getVariables()) {
                    String attributeName = var.getNameAsString();
                    attributeUsageCount.put(attributeName, 0);
                 //   System.out.println("Found attribute: " + attributeName);
                }
                super.visit(n, arg);
            }

            @Override
            public void visit(NameExpr n, Object arg) {
                String name = n.getNameAsString();
                if (attributeUsageCount.containsKey(name)) {
                    attributeUsageCount.put(name, attributeUsageCount.get(name) + 1);
               //     System.out.println("Used attribute: " + name);
                }
                super.visit(n, arg);
            }

            @Override
            public void visit(VariableDeclarator n, Object arg) {
                String attributeName = n.getNameAsString();
                attributeUsageCount.put(attributeName, attributeUsageCount.getOrDefault(attributeName, 0) + 1);
               // System.out.println("Found attribute: " + attributeName);
                super.visit(n, arg);
            }
        }, null);

    }
    public Map<String, Integer> getAttributeUsageCount() {
        return attributeUsageCount;
    }
}