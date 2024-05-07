import java.lang.reflect.Field;

public class InterfaceCounter {

    public static int countInterfaces(Class<?> clazz) {
        int count = 0;
        if (clazz != null) {
            // Get all interfaces directly implemented by the class
            Class<?>[] interfaces = clazz.getInterfaces();
            count = interfaces.length;

            // Check for implemented interfaces by superclasses recursively
            Class<?> superclass = clazz.getSuperclass();
            while (superclass != null) {
                count += countInterfaces(superclass);
                superclass = superclass.getSuperclass();
            }
        }
        return count;
    }
}
