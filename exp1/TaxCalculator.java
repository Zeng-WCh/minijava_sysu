public class TaxCalculator {
    public static void main(String[] args) throws Exception {
        argParser ap = new argParser();
        ap.setInfo("TaxCalculator");
        ap.addArgs("config", argType.typeString, "A CSV file that contain tax rules");
        ap.addArgs("interactive", argType.typeBoolean, "Whather to enable interactive mode", "false");
        ap.addArgs("salary", argType.typeDouble, "Salary someone get");
        ap.addArgs("start", argType.typeInt, "At how much should someone start to pay taxes");
        ap.addArgs("detail", argType.typeBoolean, "To display detail info");

        try {
            ap.parseArgs(args);
        } catch (ArgException e) {
            System.out.println(e.getInfo());
            ap.helpInfo();
            System.exit(1);
        }

        String interactiveS = ap.get("interactive");
        boolean interactive = Boolean.parseBoolean(interactiveS);

        String fileName = "", startValS = "", salaryS = "", detailS = "";
        try {
            fileName = ap.get("config");
        } catch (ArgException a) {
            if (!interactive) {
                System.out.println(a.getInfo());
                System.exit(1);
            } else {
                fileName = null;
            }
        }
        try {
            startValS = ap.get("start");

        } catch (ArgException a) {
            if (!interactive) {
                System.out.println(a.getInfo());
                System.exit(1);
            } else {
                startValS = null;
            }
        }
        try {
            salaryS = ap.get("salary");
        } catch (ArgException a) {
            if (!interactive) {
                System.out.println(a.getInfo());
                System.exit(1);
            } else {
                salaryS = null;
            }
        }
        try {
            detailS = ap.get("detail");
        } catch (ArgException a) {
            if (!interactive) {
                System.out.println(a.getInfo());
                System.exit(1);
            } else {
                detailS = null;
            }
        }

        int start = 0;
        double salary = 0.0;
        boolean detail = true;

        try {
            if (startValS != null)
                start = Integer.parseInt(startValS);
            if (salaryS != null)
                salary = Double.parseDouble(salaryS);
            if (detailS != null)
                detail = Boolean.parseBoolean(detailS);
        } catch (Exception e) {
            ap.helpInfo();
            System.exit(1);
        }

        shell sh = new shell();
        if (fileName != null)
            sh.loadFile(fileName);
        sh.setStart(start);
        sh.setSalary(salary);
        sh.setDetail(detail);

        if (!interactive) {
            sh.run();
        } else {
            sh.start();
        }
    }
}
