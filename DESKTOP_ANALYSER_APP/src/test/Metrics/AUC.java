//package Metrics;
//
//import Model.ClassLevelMetric;
//import Model.Result;
//import com.github.javaparser.JavaParser;
//import com.github.javaparser.ast.CompilationUnit;
//import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
//import com.github.javaparser.ast.body.FieldDeclaration;
//import com.github.javaparser.ast.body.MethodDeclaration;
//import com.github.javaparser.ast.body.VariableDeclarator;
//import com.github.javaparser.ast.expr.NameExpr;
//import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
//
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.util.*;
//
//public class AUC  extends ClassLevelMetric {
//    private Map<String, Integer> attributeUsageCount = new HashMap<>();
//    Map<String, Set<String>> methodAttributeUsage = new HashMap<>();
//    int numberOfMethods = 0;
//    ArrayList<String> methodsNames = new ArrayList<>();
//
//    public AUC(String metricName) {
//        super(metricName);
//    }
//
//    public void countAttributeUsage(String filePath) throws FileNotFoundException {
//        FileInputStream in = new FileInputStream(filePath);
//        //System.out.println("Processing file: " + filePath);
//        JavaParser javaParser = new JavaParser();
//        CompilationUnit cu = javaParser.parse(in).getResult().orElseThrow();
//        cu.accept(new VoidVisitorAdapter<Object>() {
//            @Override
//            public void visit(ClassOrInterfaceDeclaration n, Object arg) {
////                System.out.println("Visiting class: " + n.getName());
//                for (MethodDeclaration method : n.getMethods()) {
////                    System.out.println("Visiting method: " + method.getName());
//                    method.accept(this, null);
//                    numberOfMethods++;
//                    methodsNames.add(method.getNameAsString());
//                }
//                super.visit(n, arg);
//            }
//
//            @Override
//            public void visit(FieldDeclaration n, Object arg) {
//                for (VariableDeclarator var : n.getVariables()) {
//                    String attributeName = var.getNameAsString();
//                    methodAttributeUsage.put(attributeName, new HashSet<>());
//                    attributeUsageCount.put(attributeName, -1);
//                    //   System.out.println("Found attribute: " + attributeName);
//                }
//                super.visit(n, arg);
//            }
//
//
//            @Override
//            public void visit(NameExpr n, Object arg) {
//                String name = n.getNameAsString();
//                if (attributeUsageCount.containsKey(name)) {
//                    attributeUsageCount.put(name, attributeUsageCount.get(name) + 1);
//                    if (arg instanceof MethodDeclaration) {
//                        MethodDeclaration method = (MethodDeclaration) arg;
//                        String methodName = method.getNameAsString();
//                        if (methodAttributeUsage.containsKey(name)) {
//                            methodAttributeUsage.get(name).add(methodName);
//                        } else {
//                            Set<String> methods = new HashSet<>();
//                            methods.add(methodName);
//                            methodAttributeUsage.put(name, methods);
//                        }
//                    }
//                }
//                super.visit(n, arg);
//            }
//
//            @Override
//            public void visit(MethodDeclaration n, Object arg) {
//                methodsNames.add(n.getNameAsString());
//                // Pass the current method as an argument to the visit method of its children
//                n.getBody().ifPresent(body -> body.accept(this, n));
//            }
//
//            @Override
//            public void visit(VariableDeclarator n, Object arg) {
//                String attributeName = n.getNameAsString();
//                attributeUsageCount.put(attributeName, attributeUsageCount.getOrDefault(attributeName, 0) + 1);
//                super.visit(n, arg);
//            }
//        }, null);
//
//
//    }
//
//    public Map<String, Integer> getAttributeUsageCount() {
//        return attributeUsageCount;
//    }
//
//    public int getNumberOfMethods() {
//        return numberOfMethods;
//    }
//
//    public ArrayList<String> getMethodsNames() {
//        return methodsNames;
//    }
//
//    public float utilisationAttribute() {
//        float x = 0;
//        for (Map.Entry<String, Set<String>> entry : methodAttributeUsage.entrySet()) {
//            x = x + entry.getValue().size();
//        }
//        return x;
//    }
//
//    @Override
//    public float calculate(String file_path) {
//        try {
//            countAttributeUsage(file_path);
//        } catch (FileNotFoundException e) {
//        }
//        return this.utilisationAttribute()/this.getNumberOfMethods();
//    }
//
//    @Override
//    public Result execute(String file_path) {
//        return new Result(this.metricName, String.valueOf(calculate(file_path)).substring(0,1));
//    }
//}
