class RangeException extends Exception {
    private final String info;
    public RangeException(String info) {
        this.info = info;
    }

    public String getInfo() {
        return this.info;
    }
}

class range {
    private int low, high;

    public range() {
        this.low = 0;
        this.high = 0;
    }

    public range(int low, int high) throws RangeException {
        if (low > high) {
            throw new RangeException("Low range value is bigger than high range value");
        }
        else {
            this.low = low;
            this.high = high;
        }
    }
}

public class tax extends range {
    private double point;
    public tax(int low, int high, double point) throws RangeException {
        super(low, high);
        this.point = point;
    }
}