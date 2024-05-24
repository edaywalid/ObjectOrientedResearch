package com.example;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Arrays;

public class App {

    public static void main(String[] args) {
       
    }

    public static int countClassLevelObjects(String fileName) {
        List <String> keywords = Arrays.asList("class", "interface", "enum" , "int" , "double" , "float" , "long" , "short" , "byte" , "char" , "boolean" , "void" );
        int count = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            boolean isInsideClass = false;

            // Regular expression pattern to match variable declarations
            Pattern pattern = Pattern.compile("^\\s*(\\w+)\\s+(\\w+)\\s*;");

            while ((line = br.readLine()) != null) {
                line = line.trim();

        

                // Match variable declarations inside the class
                if (true) {
                    Matcher matcher = pattern.matcher(line);
                    if (matcher.find()) {
                        String type = matcher.group(1); // Variable type
                        System.out.print("match : " + type);
                        if (!type.equals("class") && !type.equals("interface") && !type.equals("enum") && !keywords.contains(type)) {
                            // If the type is not a class, interface, or enum, it's likely an object
                            count++;
                            System.out.println("  pass : " + count);
                            continue;
                        }
                        System.out.println("  fail : " + count);

                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return count;
    }
}
