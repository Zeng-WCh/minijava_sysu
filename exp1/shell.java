import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Scanner;

class RuleException extends Exception {
    public RuleException(String info) {
        super(info);
    }
}

enum token {
    tok_set,
    tok_calc,
    tok_show,
    tok_help,
    tok_exit,
    tok_load,
    tok_remove,
    tok_start,
    tok_salary,
    tok_range,
    tok_percentage,
    tok_int,
    tok_double,
    tok_file,
    tok_unknown,
}

class lexer {
    private final Map<token, Pattern> tp;

    public lexer() {
        Map<token, String> ts = new HashMap<>();
        this.tp = new HashMap<>();
        ts.put(token.tok_set, "^set$");
        ts.put(token.tok_calc, "^calc$");
        ts.put(token.tok_show, "^show$");
        ts.put(token.tok_help, "^help$");
        ts.put(token.tok_exit, "^exit$");
        ts.put(token.tok_load, "^load$");
        ts.put(token.tok_remove, "^remove$");
        ts.put(token.tok_start, "^start$");
        ts.put(token.tok_salary, "^salary$");
        ts.put(token.tok_range, "^(\\d+)-(\\d+)$");
        ts.put(token.tok_percentage, "(\\d+)%");
        ts.put(token.tok_int, "^(-?)(\\d)+$");
        ts.put(token.tok_double, "^(-?\\d+)(\\.\\d+)?$");
        ts.put(token.tok_file, "^(\\w|.)+.csv$");
        for (token t : ts.keySet()) {
            this.tp.put(t, Pattern.compile(ts.get(t)));
        }
    }

    public token match(String s) {
        for (token t : this.tp.keySet()) {
            Matcher m = this.tp.get(t).matcher(s);
            if (m.find()) {
                return t;
            }
        }
        return token.tok_unknown;
    }
}

public class shell {
    private final ArrayList<tax> taxList;
    private boolean existOthers;
    private double salary;
    private int start;
    private boolean detail;
    private lexer l;

    public shell() {
        this.taxList = new ArrayList<>();
        this.existOthers = false;
        this.salary = 0.0;
        this.start = 0;
        this.detail = false;
        this.l = null;
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
                System.out.printf("Warning: tax rule %d has a range overlap with the tax rule No.%d, please check your config\n", i + 1, this.taxList.size() + 1);
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
                    } catch (RuleException | RangeException r) {
                        System.out.println(r.getMessage());
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
            } else {
                if (t.high > max) {
                    max = t.high;
                }
            }
        }
        if (index != -1)
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
        if (p_int > 100 || p_int < 0) {
            throw new RuleException("Percentage must between 0 and 100");
        }
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
        if (this.taxList.isEmpty()) {
            System.out.println("No tax rule can be found, please add them first");
            return;
        }
        this.setOthers();
        double result = 0.0;
        for (int i = 0; i < this.taxList.size(); ++i) {
            double res = this.taxList.get(i).getTax(this.salary - this.start);
            result += res;
            if (this.detail) {
                System.out.printf("Base on rule No.%d, he/she needs to pay %.2f taxes\n", i + 1, res);
            }
        }
        System.out.printf("Base on all the rules provided, he/she needs to pay %.2f taxes\n", result);
    }

    private void showRules() {
        System.out.printf("Current StartVal: %d\n", this.start);
        System.out.printf("Current Salary: %.2f\n", this.salary);
        if (this.taxList.isEmpty()) {
            System.out.print("No rules here, try set or load from file\n");
        }
        for (int i = 0; i < this.taxList.size(); ++i) {
            System.out.printf("Tax rule NO.%d: %s\n", i + 1, this.taxList.get(i).toString());
        }
    }

    private void parseSet(Scanner sc) {
        String next = sc.next();
        token t = this.l.match(next);
        if (t != token.tok_range && t != token.tok_salary && t != token.tok_start) {
            System.out.println("Syntax Error: Should be like set [salary|range|start] [val]");
        } else if (t == token.tok_range) {
            String range = next;
            next = sc.next();
            if (this.l.match(next) != token.tok_percentage) {
                System.out.println("Syntax Error: Should be like set [range] [percentage]");
                return;
            }
            String p = next;
            String formatS = String.format("%s,%s", range, p);
            tax nt = null;
            try {
                nt = parseString(formatS);
            } catch (Exception e) {
                System.out.println("Syntax Error: Please check range or percentage");
                return;
            }
            try {
                this.addRule(nt);
            } catch (RuleException e) {
                System.out.println(e.getMessage());
            }
        } else if (t == token.tok_salary) {
            next = sc.next();
            if (this.l.match(next) != token.tok_double && this.l.match(next) != token.tok_int) {
                System.out.println("Syntax Error: Should be like set salary [salaryVal]");
                return;
            }
            double val = 0.0;
            try {
                val = Double.parseDouble(next);
            }
            catch (Exception e) {
                System.out.println("Syntax Error: Please check your salary val");
                return;
            }
            this.setSalary(val);
        }
        else {
            next = sc.next();
            if (this.l.match(next) != token.tok_double && this.l.match(next) != token.tok_int) {
                System.out.println("Syntax Error: Should be like set start [startVal]");
                return;
            }
            int start = 0;
            try {
                start = Integer.parseInt(next);
            }
            catch (Exception e) {
                System.out.println("Syntax Error: Please check your start val");
                return;
            }
            this.setStart(start);
        }
    }

    private void helpInfo() {

    }

    private void parseRemove(Scanner sc) {

    }

    private void parseLoad(Scanner sc) {
        String file = sc.next();
        if (!(this.l.match(file) == token.tok_file)) {
            System.out.println("Syntax Error: Should be like load [file], file should be a csv file");
        }
        try {
            this.loadFile(file);
        }
        catch (Exception e) {
            System.out.println("Error Occured, Please check if file exist");
        }
    }

    public void start() throws Exception {
        if (this.l == null) {
            this.l = new lexer();
        }

        System.out.print(">>> ");

        Scanner sc = new Scanner(System.in);
        String cur = sc.next();
        token t = l.match(cur);

        while (t != token.tok_exit) {
            switch (t) {
                case tok_show: {
                    showRules();
                    break;
                }
                case tok_set: {
                    parseSet(sc);
                    break;
                }
                case tok_calc: {
                    setDetail(true);
                    run();
                    break;
                }
                case tok_help: {
                    // TODO
                    helpInfo();
                    break;
                }
                case tok_remove: {
                    // TODO
                    parseRemove(sc);
                    break;
                }
                case tok_load: {
                    parseLoad(sc);
                    break;
                }
                case tok_unknown: {
                    System.out.printf("Unknown Token: %s\n", cur);
                    break;
                }
            }
            System.out.print(">>> ");
            cur = sc.next();
            t = l.match(cur);
        }

        System.out.println("GoodBye");
    }
}
