package Metrics;

import Model.DefaultMetric.ClassLevelMetric;
import Model.Result;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberOfInterfacesImplemented extends ClassLevelMetric {
    public NumberOfInterfacesImplemented(String metricName) {
        super(metricName);
    }

    public float numberOfIntefacesImpl(String filePath) throws IOException {

        int interfaceCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            // Regular expression pattern to match interface implementations sentence
            Pattern pattern = Pattern.compile(".*implements\\s+((\\w+)(\\s*,\\s*\\w+)*)\\s*.*");

            while ((line = br.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);

                if (matcher.matches()) {
                    interfaceCount += matcher.group(1).split("\\s*,\\s*").length;
                }
            }
        }
        return interfaceCount;
    }

    @Override
    public float calculate(String file_path) {
        try{
            return numberOfIntefacesImpl(file_path);
        }catch(IOException e){
            return -1;
        }
    }

    @Override
    public Result execute(String file_path) {
        return new Result(this.metricName, String.valueOf(calculate(file_path)));
    }
}