package MetricsImplementations.src.main.java;
public class InterfaceCounter {

    public static int countInterfaces(String filePath) throws Exception {
        Class<?> clazz = DynamicClassLoading.DynamicClassLoad(filePath);
        int count = 0;
        if (clazz != null) {
            // Get all interfaces directly implemented by the class
            Class<?>[] interfaces = clazz.getInterfaces();
            count = interfaces.length;

            // Check for implemented interfaces by superclasses recursively
            Class<?> superclass = clazz.getSuperclass();
            while (superclass != null) {
                count += countInterfaces(String.valueOf(superclass));
                superclass = superclass.getSuperclass();
            }
        }
        return count;
    }
}
