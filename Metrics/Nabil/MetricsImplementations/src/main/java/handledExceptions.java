package MetricsImplementations.src.main.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class handledExceptions {

    public static int handledExceptionsCount(String filePath) throws IOException {

        int handledExceptionsCount = 0;
        boolean inSingleLineComment = false;
        boolean inMultiLineComment = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.strip();

                // Skip empty lines
                if (line.isEmpty()){
                    continue;
                }

                // Handle single-line comments
                if (line.startsWith("//")) {
                    inSingleLineComment = true;
                    continue;
                }
                inSingleLineComment = false;

                // Handle multi-line comments
                if (inMultiLineComment) {
                    if (line.endsWith("*/")) {
                        inMultiLineComment = false;
                    }
                    continue;
                } else if (line.startsWith("/*")) {
                    inMultiLineComment = true;
                    continue;
                }

                // Check for try and throws statements (outside comments)
                if (!inSingleLineComment && !inMultiLineComment) {
                    if (line.toLowerCase().startsWith("try")) {
                        handledExceptionsCount++;
                    } else if (line.toLowerCase().contains("throw ")) {
                        handledExceptionsCount++;
                    }
                }
            }
        }
        return handledExceptionsCount;
    }
}

