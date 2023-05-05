package scanner;

public class Buffer {
    private String tok;
    private int curPos;
    private boolean holdOn;

    public Buffer(String tok) {
        this.tok = tok.toLowerCase();
        this.curPos = 0;
        this.holdOn = false;
    }

    /**
     * 
     * @return the character(not zero) if it still can get, else return 0
     */
    public int next() {
        if (this.holdOn) {
            this.holdOn = false;
            return (int) this.tok.charAt(this.curPos - 1);
        }
        if (curPos < this.tok.length()) {
            return (int) this.tok.charAt(this.curPos++);
        }
        return 0;
    }

    public void holdOn() {
        this.holdOn = true;
    }
}
