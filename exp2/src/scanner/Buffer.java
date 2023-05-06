package scanner;

/**
 * 
 * @author Weichao Zeng
 * @version 1.00 (Last update: 2023/05/06)
 */
public class Buffer {
    private String buf;
    private int curPos;
    private boolean holdOn;

    /**
     * To construct a buffer with the input expression string
     * 
     * @param buf, the input expression string
     */
    public Buffer(String buf) {
        this.buf = buf.toLowerCase();
        this.curPos = 0;
        this.holdOn = false;
    }

    /**
     * 
     * @return the character(not zero) if buffer is not empty, else return 0
     */
    public int next() {
        if (this.holdOn) {
            this.holdOn = false;
            return (int) this.buf.charAt(this.curPos - 1);
        }
        if (curPos < this.buf.length()) {
            return (int) this.buf.charAt(this.curPos++);
        }
        return 0;
    }

    /**
     * 
     * To hold on the last character that has been read in
     */
    public void holdOn() {
        this.holdOn = true;
    }
}
