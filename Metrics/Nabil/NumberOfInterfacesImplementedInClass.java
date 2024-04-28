import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InterfaceCountInJavaClass {
    public static void main(String[] args) {

        String fileName = "file.java";
        int interfaceCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;

            // Regular expression pattern to match interface implementations sentence
            Pattern pattern = Pattern.compile(".*implements\\s+((\\w+)(\\s*,\\s*\\w+)*)\\s*.*");

            while ((line = br.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);

                if (matcher.matches()) {
                    interfaceCount += matcher.group(1).split("\\s*,\\s*").length;
                }
            }

            System.out.println("Number of interfaces implemented in the Java class: " + interfaceCount);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
