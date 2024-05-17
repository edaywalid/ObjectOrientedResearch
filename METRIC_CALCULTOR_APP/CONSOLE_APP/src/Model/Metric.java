package Model;

import java.io.IOException;
import java.util.ArrayList;

public interface Metric {
       float calculate(String file_path) ;
       Result execute(String file_path);
}
