public class TaxCalculator {
    public static void main(String[] args) {
        argParser ap = new argParser();
        ap.setInfo("TaxCalculator");
        ap.addArgs("config", argType.typeString, "A CSV file that contain tax rules");
        // ap.addArgs("interactive", argType.typeBoolean, "Whather to enable interactive mode", "false");
        ap.addArgs("salary", argType.typeDouble, "Salary someone get");
        ap.addArgs("start", argType.typeInt, "At how much should someone start to pay taxes");

        try {
            ap.parseArgs(args);
        } catch (ArgException e) {
            System.out.println(e.getInfo());
            ap.helpInfo();
            System.exit(1);
        }
        try {
            String fileName = ap.get("config");
        } catch (ArgException a) {
            System.out.println(a.getInfo());
            System.exit(1);
        }
    }
}
