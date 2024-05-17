package Metrics;
import Model.ClassLevelMetric;
import Model.Result;

import java.io.IOException;

public class InterfaceCounter extends ClassLevelMetric {

    public InterfaceCounter(String metricName) {
        super(metricName);
    }

    public float countInterfaces(String filePath) throws Exception {
        Class<?> clazz = DynamicClassLoading.DynamicClassLoad(filePath);
        float count = 0;
        if (clazz != null) {
            // Get all interfaces directly implemented by the class
            Class<?>[] interfaces = clazz.getInterfaces();
            count = interfaces.length;

            // Check for implemented interfaces by superclasses recursively
            Class<?> superclass = clazz.getSuperclass();
            while (superclass != null) {
                count += countInterfaces(String.valueOf(superclass));
                superclass = superclass.getSuperclass();
            }
        }
        return count;
    }

    @Override
    public float calculate(String file_path) {
        try{
            return countInterfaces(file_path);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return -1;
        }
    }

    @Override
    public Result execute(String file_path) {
       return new Result(this.metricName, String.valueOf(calculate(file_path)));
    }
}
