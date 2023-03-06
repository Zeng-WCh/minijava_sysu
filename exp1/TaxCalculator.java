public class TaxCalculator {
    public static void main(String[] args) throws Exception {
        argParser ap = new argParser();
        ap.setInfo("TaxCalculator");
        ap.addArgs("config", argType.typeString, "A CSV file that contain tax rules", "config.csv");
        // ap.addArgs("interactive", argType.typeBoolean, "Whather to enable interactive mode", "false");
        ap.addArgs("salary", argType.typeDouble, "Salary someone get", "50000.0");
        ap.addArgs("start", argType.typeInt, "At how much should someone start to pay taxes", "5000");
        ap.addArgs("detail", argType.typeBoolean, "To display detail info", "true");

        try {
            ap.parseArgs(args);
        } catch (ArgException e) {
            System.out.println(e.getInfo());
            ap.helpInfo();
            System.exit(1);
        }
        String fileName = "", startValS = "", salaryS = "", detailS = "";
        try {
            fileName = ap.get("config");
            startValS = ap.get("start");
            salaryS = ap.get("salary");
            detailS = ap.get("detail");
        } catch (ArgException a) {
            System.out.println(a.getInfo());
            System.exit(1);
        }

        int start = 0;
        double salary = 0.0;
        boolean detail = true;

        try {
            start = Integer.parseInt(startValS);
            salary = Double.parseDouble(salaryS);
            detail = Boolean.parseBoolean(detailS);
        } catch (Exception e) {
            ap.helpInfo();
            System.exit(1);
        }

        shell sh = new shell();
        sh.loadFile(fileName);
        sh.setStart(start);
        sh.setSalary(salary);
        sh.setDetail(detail);
        sh.run();

    }
}
