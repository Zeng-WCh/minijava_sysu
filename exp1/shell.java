import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

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
    private ArrayList<tax> taxList;
    private boolean existOthers;


    public shell() {
        this.taxList = new ArrayList<>();
        this.existOthers = false;
    }

    public void addRule(tax t) throws RuleException {
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
                System.out.printf("Warning: tax rule %d has a range overlap with the new tax rule, please check your config\n", i);
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
                    s = reader.readLine();
                    tax t;
                    try {
                        t = parseString(s);
                    }
                    catch (RangeException r) {
                        System.out.println(r.getInfo());
                        throw r;
                    }
                    this.addRule(t);
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

    public static tax parseString(String s) throws RangeException {
        return new tax(-1, -1, 0);
    }
}