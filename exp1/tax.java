class RangeException extends Exception {
    private final String info;
    public RangeException(String info) {
        super();
        this.info = info;
    }

    public String getInfo() {
        return this.info;
    }
}

class range {
    int low, high;

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

    public void setHigh(int high) throws RangeException {
        if (this.low > high) {
            throw new RangeException(String.format("New high range(%d) is smaller than current low(%d)", high, this.low));
        }
        this.high = high;
    }

    public void setLow(int low) throws RangeException {
        if (low > this.high) {
            throw new RangeException(String.format("New low range(%d) is bigger than current high(%d)", low, this.high));
        }
        this.low = low;
    }

    public boolean isOverlap(range r) {
        return (!(this.low > r.high || this.high < r.low));
    }
}

public class tax extends range {
    private double point;
    private double maxVal;
    public tax(int low, int high, double point) throws RangeException {
        super(low, high);
        this.point = point;
        if (this.low == -1 && this.high == -1) {
            this.maxVal = 0;
        }
        else {
            this.maxVal = (this.high - this.low) * this.point;
        }
    }

    public double getTax(double salary) {
        if (this.low == -1) {
            if (salary < this.high) {
                return 0.0;
            }
            return (salary - this.high) * this.point;
        }
        if (salary < this.low) {
            return 0.0;
        }
        if (salary > this.high) {
            return this.maxVal;
        }
        return (salary - this.low) * this.point;
    }
}