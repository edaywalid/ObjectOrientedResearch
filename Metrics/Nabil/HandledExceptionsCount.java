import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HandledExceptionsCount {
    public static void main(String[] args) {

        String fileName = "file.java";
        int Count = 0;
        String[] keywords = {"throw", "try"};

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Count occurrences of try and throw
                for (String keyword : keywords) {
                    Count += countOccurrences(keyword, line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        System.out.println("Number of occurrences of 'try' in the Java class: " + Count);
    }

    // Function to count occurrences of a word in a line
    private static int countOccurrences(String line, String word) {
        int count = 0;
        int index = 0;
        while ((index = line.indexOf(word, index)) != -1) {
            count++;
            index += word.length();
        }
        return count;
    }
}