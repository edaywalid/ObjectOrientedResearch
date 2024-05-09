import java.io.FileNotFoundException;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        AttributeUsageCounter counter = new AttributeUsageCounter();
        counter.countAttributeUsage("YourClassPathMyFriend<3");
        Map<String, Integer> attributeUsageCount = counter.getAttributeUsageCount();
        for (Map.Entry<String, Integer> entry : attributeUsageCount.entrySet()) {
            System.out.println("Attribute: " + entry.getKey() + ", Usage: " + entry.getValue());
        }
    }
}