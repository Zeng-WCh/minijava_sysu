package ast;


/**
 * statement AST
 * 
 * @author Weichao Zeng
 * @version 1.0 (Last updated: 2023/06/03)
 */
public class stmt implements ast {
    public assignmentStmt asg;
    public callStmt call;
    public ifStmt ifSt;
    public whileStmt whileSt;

    public stmt() {
        this(null, null, null, null);
    }

    public stmt(assignmentStmt asg) {
        this(asg, null, null, null);
    }

    public stmt(callStmt call) {
        this(null, call, null, null);
    }

    public stmt(ifStmt ifSt) {
        this(null, null, ifSt, null);
    }

    public stmt(whileStmt whileSt) {
        this(null, null, null, whileSt);
    }

    public stmt(assignmentStmt asg, callStmt call, ifStmt ifSt, whileStmt whileSt) {
        this.asg = asg;
        this.call = call;
        this.ifSt = ifSt;
        this.whileSt = whileSt;
    }

    public void convert() {
        if (asg != null)
            asg.convert();
        if (call != null)
            call.convert();
        if (ifSt != null)
            ifSt.convert();
        if (whileSt != null)
            whileSt.convert();
    }

    public ast getStmt() {
        if (asg != null)
            return asg;
        if (call != null)
            return call;
        if (ifSt != null)
            return ifSt;
        if (whileSt != null)
            return whileSt;
        return null;
    }

    @Override
    public String toString() {
        if (asg != null)
            return asg.toString();
        if (call != null)
            return call.toString();
        if (ifSt != null)
            return ifSt.toString();
        if (whileSt != null)
            return whileSt.toString();
        return "";
    }
}
