import java.util.Set;

public class Main {

  public static void main(String[] args) {
    NumberOfImportedClasses noic = new NumberOfImportedClasses(args[0]);
    Set<String> importedPackages = noic.getImportedPackages();

    System.out.println("\t------------- Imported Packages -------------");
    //    for (String p : importedPackages) {
    //      System.out.println("[" + p + "]");
    //    }
    System.out.println("number of imported packages: " + importedPackages.size());

    System.out.println("\n\n------------- Used Classes -------------");
    NumberOfImportedClassesUsed noicu = new NumberOfImportedClassesUsed(args[0]);
    Set<String> usedClasses = noicu.getNumberOfUsedClasses();
    for (String p : usedClasses) {
      System.out.println("[" + p + "]");
    }
    System.out.println("\nnumber of used classes: " + usedClasses.size());
  }
}
