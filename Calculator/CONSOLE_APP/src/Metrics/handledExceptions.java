package Metrics;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class handledExceptions {



    public handledExceptions(String metricName) {

    }

    public int handledExceptionsCount(String filePath) throws IOException {
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

                // Check for try and throw statements (outside comments)
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

    public float calculate(String file_path){
        try{
            return this.handledExceptionsCount(file_path);

        }catch (IOException e){
            return -1;
        }
    }



}





