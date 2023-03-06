import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

class RuleException extends Exception {
    private String info;

    public RuleException(String info) {
        super();
        this.info = info;
    }

    public String getInfo() {
        return this.info;
    }
}

public class shell {
    private final ArrayList<tax> taxList;
    private boolean existOthers;
    private double salary;
    private int start;
    private boolean detail;


    public shell() {
        this.taxList = new ArrayList<>();
        this.existOthers = false;
        this.salary = 0.0;
        this.start = 0;
        this.detail = false;
    }

    public void addRule(tax t) throws RuleException {
        if (this.taxList.isEmpty()) {
            this.taxList.add(t);
            return;
        }
        if (t.high == -1 && t.low == -1 && this.existOthers) {
            throw new RuleException("Duplicate others range");
        }
        if (t.high == -1 && t.low == -1) {
            this.existOthers = true;
            this.taxList.add(t);
            return;
        }
        for (int i = 0; i < this.taxList.size(); ++i) {
            if (this.taxList.get(i).isOverlap(t)) {
                System.out.printf("Warning: tax rule %d has a range overlap with the new tax rule, please check your config\n", i+1);
            }
        }
        this.taxList.add(t);
    }

    public void removeRule(int index) {
        if (index > this.taxList.size()) {
            System.out.printf("Warning: index %d is out of range\n", index);
        } else {
            this.taxList.remove(index);
        }
    }

    public void clean() {
        this.existOthers = false;
        this.taxList.clear();
    }

    public void loadFile(String fileName) throws Exception {
        try (FileReader file = new FileReader(fileName)) {
            try (BufferedReader reader = new BufferedReader(file)) {
                String s = reader.readLine();
                while (s != null) {
                    tax t;
                    try {
                        t = parseString(s);
                    }
                    catch (RuleException r) {
                        System.out.println(r.getInfo());
                        throw r;
                    }
                    catch (RangeException r) {
                        System.out.println(r.getInfo());
                        throw r;
                    }
                    this.addRule(t);
                    s = reader.readLine();
                }
            }
        }
    }

    private void setOthers() throws RangeException {
        if (!this.existOthers) {
            return;
        }
        int max = 0, index = -1;
        for (int i = 0; i < this.taxList.size(); ++i) {
            tax t = this.taxList.get(i);
            if (t.high == -1 && t.low == -1) {
                index = i;
            }
            else {
                if (t.high > max) {
                    max = t.high;
                }
            }
        }
        this.taxList.get(index).setHigh(max);
    }

    /**
     * @param s, a line read from csv file
     * @return a tax instance
     * @throws Exception, {@link RangeException if Range is not correct}, {@link RuleException if fileFormat is not correct}
     */
    public static tax parseString(String s) throws Exception {
        String[] buf = s.split(",");
        String range = buf[0];
        String percentage = buf[1];
        Pattern p = Pattern.compile("(\\d+)%");
        Matcher m = p.matcher(percentage);
        if (!m.find() || m.groupCount() != 1) {
            throw new RuleException("Percentage can not be found");
        }
        int p_int = Integer.parseInt(m.group(1));
        double point = (double) p_int / 100.0;
        int low, high;
        if (range.equals("others")) {
            return new tax(-1, -1, point);
        }
        p = Pattern.compile("(\\d+)-(\\d+)");
        m = p.matcher(range);
        if (!m.find() || m.groupCount() != 2) {
            throw new RuleException("Range info error, should be a range of \"others\"");
        }
        low = Integer.parseInt(m.group(1));
        high = Integer.parseInt(m.group(2));
        return new tax(low, high, point);
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public void setDetail(boolean detail) {
        this.detail = detail;
    }

    public void run() throws Exception {
        this.setOthers();
        double result = 0.0;
        for (int i = 0; i < this.taxList.size(); ++i) {
            double res = this.taxList.get(i).getTax(this.salary - this.start);
            result += res;
            if (this.detail) {
                System.out.printf("Base on rule No.%d, he/she needs to pay %.2f taxes\n", i+1, res);
            }
        }
        System.out.printf("Base on all the rules provided, he/she needs to pay %.2f taxes\n", result);
    }
}
