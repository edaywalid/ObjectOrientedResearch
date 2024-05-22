package Model.DefaultMetric;

import Model.BaseMetric;
import Model.Result;

public interface Metric extends BaseMetric {
       float calculate(String file_path) ;
       Result execute(String file_path);
}
