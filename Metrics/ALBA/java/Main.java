import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        AttributeUsageCounter counter = new AttributeUsageCounter();
        counter.countAttributeUsage("YourCodePath");
        for (Map.Entry<String, Set<String>> entry : counter.methodAttributeUsage.entrySet()) {
            if (!entry.getValue().isEmpty()){
                System.out.println("Attribute: " + entry.getKey() +" Usage :"+entry.getValue().size()+ " in: " + entry.getValue());
            }else
                System.out.println("Attribute: \"" + entry.getKey() +"\" Usage :"+ 0 );
        }
        System.out.println("UARUM : "+counter.utilisationAttribute()+" / "+counter.getNumberOfMethods()+ "= "+counter.utilisationAttribute()/counter.getNumberOfMethods());
    }
}