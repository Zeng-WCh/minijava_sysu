public class TaxCalculator {
  public static void main(String[] args) {
    argParser ap = new argParser();
    ap.setInfo("TaxCalculator");
    ap.addArgs("config", argType.typeString, "A CSV Config File");

    try {
        ap.parseArgs(args);
    }catch (Exception e) {
        ap.helpInfo();
        System.exit(1);
    }
  }
}
