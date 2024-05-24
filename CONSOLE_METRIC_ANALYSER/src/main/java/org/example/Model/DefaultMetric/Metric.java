package org.example.Model.DefaultMetric;

import org.example.Model.BaseMetric;
import org.example.Model.Result;

public interface Metric extends BaseMetric {
       float calculate(String file_path) ;
       Result execute(String file_path);
}
