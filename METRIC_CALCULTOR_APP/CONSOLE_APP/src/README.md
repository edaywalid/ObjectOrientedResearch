### HOW TO ADD METRICS IN THE APP 
1. Add the metric in the `Metrics` folder
2. make sure the metric is extending the corresponding type either (ClassLevel , PackageLevel)
3. Add the metric name with the super constructor
4. implement the `calculate` method
5. implement the  `execute` method


### HERE IS A PRACTICE EXAMPLE

## u can always find this example in the Metrics folder

    package Metrics;

    import Model.ClassLevelMetric;
    import Model.Result;

    public class ExampleMetric extends ClassLevelMetric {

        public ExampleMetric(String metricName) {
            super(metricName);
        }

        int result(){
            // This is a dummy method to test the functionality of the test cases
            return 124;
        }

        @Override
        public float calculate(String file_path) {
            return result();
        }

        @Override
        public Result execute(String file_path) {
            return new Result(this.metricName, String.valueOf(this.calculate(file_path)));
        }
    }
