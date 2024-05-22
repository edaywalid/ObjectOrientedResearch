package Metrics;

import Model.DefaultMetric.ClassLevelMetric;
import Model.Result;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class WebImportCounter extends ClassLevelMetric {

    private static final String WEB_IMPORT_PATTERN =
            "import\\s+(java\\.net\\..*|javax\\.servlet\\..*|org\\.apache\\.http\\..*|okhttp3\\..*|com\\..*);";
    private Pattern pattern;
    private String filename;

    public WebImportCounter(String metricName) {
        super(metricName);
        this.pattern = Pattern.compile(WEB_IMPORT_PATTERN);

    }

    public boolean isWebImport(String importStatement) {
        Matcher matcher = pattern.matcher(importStatement);
        return matcher.matches();
    }

    public int countWebImports(String fl)  {
        int webImportCount = 0;
        String filename = fl ;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("import ") && isWebImport(line.trim())) {
                    webImportCount++;
                }
            }
        }catch (IOException e){

        }

        return webImportCount;
    }


    @Override
    public float calculate(String file_path)  {

        return  this.countWebImports(file_path);
    }

    @Override
    public Result execute(String file_path)  {
        return new Result(this.metricName, String.valueOf(this.calculate(file_path)));
    }
}
