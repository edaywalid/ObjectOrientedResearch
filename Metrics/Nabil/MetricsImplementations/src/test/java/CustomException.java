package MetricsImplementations.src.test.java;

public class CustomException extends Exception{
    @Override
    public String getMessage() {
        return "Custom Exception";
    }
}
