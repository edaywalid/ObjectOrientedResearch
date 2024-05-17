package Metrics;
import java.util.Date;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
public class testUsedMethodsImports{
    Random random = new Random();
    ArrayList<String> list = new ArrayList<>();
    testUsedMethodsImports(){
        Date date = new Date();
        date.setHours(random.nextInt(10));
        random.nextBoolean();
        list.add("Nabil");
        list.add("Walid");
        list.add("Abderrahman");
        list.removeFirst();
        String test;
        list.getLast();
    }
}
