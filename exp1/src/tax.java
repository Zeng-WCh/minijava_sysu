class RangeException extends Exception {
    public RangeException(String info) {
        super(info);
    }
}

class range implements Comparable<range> {
    int low, high;

    public range() {
        this.low = 0;
        this.high = 0;
    }

    public range(int low, int high) throws RangeException {
        if (low > high) {
            throw new RangeException("Low range value is bigger than high range value");
        } else {
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

    /**
     * @param r: a range instance
     * @return true if two ranges have a range overlap, else false
     */
    public boolean isOverlap(range r) {
        if (this.low != -1 && r.low != -1)
            return (!(this.low >= r.high || this.high <= r.low));
        else if (this.low == -1 && r.low == -1)
            return true;
        return false;
    }

    @Override
    public String toString() {
        if (this.low == -1 || this.high == -1) {
            return "others";
        }
        return String.format("(%d, %d)", this.low, this.high);
    }

    /**
     * @param t, a range instance
     * @return true if two ranges have the same range, else false
     */
    public boolean isEqual(range r) {
        return this.low == r.low && this.high == r.high;
    }

    @Override
    public int compareTo(range t) {
        if (t.low == -1) {
            return -1;
        }
        if (this.low == -1) {
            return 1;
        }
        if (t.low == this.low) {
            return this.high - t.high;
        }
        return this.low - t.low;
    }
}

public class tax extends range {
    double point;
    private final double maxVal;

    public tax(int low, int high, double point) throws RangeException {
        super(low, high);
        this.point = point;
        if (this.low == -1 && this.high == -1) {
            this.maxVal = 0;
        } else {
            this.maxVal = (this.high - this.low) * this.point;
        }
    }

    /**
     * @param salary: salary minus the baseline, for example if someone get 55000 and the baseline is 5000, then set salary to 50000
     * @return how much taxes should someone pay at this level
     */
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

    @Override
    public String toString() {
        return String.format("%s, %.2f%%", super.toString(), this.point * 100);
    }

    public void setPoint(double point) {
        this.point = point;
    }
}