import java.util.Set;

public class Main {

  public static void main(String[] args) {
    NumberOfImportedClasses NOIC = new NumberOfImportedClasses(args[0]);
    Set<String> importedPackages = NOIC.getImportedPackages();

    System.out.println("\t------------- Imported Packages -------------");
    for (String p : importedPackages) {
      System.out.println("[" + p + "]");
    }
    System.out.println("number of imported packages: " + importedPackages.size());

    System.out.println("\n\n------------- Used Classes -------------");
    NumberOfImportedClassesUsed NOICU = new NumberOfImportedClassesUsed(args[0]);
    Set<String> UsedClasses = NOICU.getNumberOfUsedClasses();
    for (String p : UsedClasses) {
      System.out.println("[" + p + "]");
    }
    System.out.println("\nnumber of used classes: " + UsedClasses.size());
  }
}