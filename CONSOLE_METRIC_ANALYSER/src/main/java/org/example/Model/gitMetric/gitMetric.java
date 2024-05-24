package org.example.Model.gitMetric;

import org.example.Model.BaseMetric;
import org.example.Model.Result;

public interface gitMetric extends BaseMetric {
    float calculate(String git_file_path , String file_name);
    Result execute(String git_file_path , String file_name);
}
