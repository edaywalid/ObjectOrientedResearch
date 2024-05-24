package Model.gitMetric;

import Model.BaseMetric;
import Model.Result;

public interface gitMetric extends BaseMetric {
    float calculate(String git_file_path , String file_name);
    Result execute(String git_file_path , String file_name);
}
