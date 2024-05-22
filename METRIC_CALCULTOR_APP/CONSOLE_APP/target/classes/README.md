### HOW TO ADD METRICS IN THE APP 
1. Add the metric in the `Metrics` folder
2. make sure the metric is extending the corresponding type either (ClassLevel , PackageLevel)
3. Add the metric name with the super constructor
4. implement the `calculate` method
5. implement the  `execute` method
6. Add the metric in the `Repository/Model_Repository.java` file as pointed in the 2nd point
7. Run the app and check the results
##  HERE IS A PRACTICE EXAMPLE

### Step 1 -> 5 :

    package Metrics;

    import Model.DefaultMetric.ClassLevelMetric;
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

### Step 6 :

    public Model_Repository(){
        
        classLevelMetrics = new ArrayList<>(List.of(
           // ... previous metrics

            // Add the metric here
            new ExampleMetric("Example Metric")
        ));

        packageLevelMetrics = new ArrayList<>(List.of(

        ));
    }
