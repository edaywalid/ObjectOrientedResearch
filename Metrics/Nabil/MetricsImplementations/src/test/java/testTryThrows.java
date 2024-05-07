package MetricsImplementations.src.test.java;


public class testTryThrows {
    testTryThrows() throws CustomException{
        try {
            Integer.parseInt("ska");

        }catch (NumberFormatException e){
            throw new CustomException();
        }
    }
}
