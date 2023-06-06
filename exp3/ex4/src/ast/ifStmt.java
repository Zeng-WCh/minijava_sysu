package ast;

import java.util.ArrayList;
import java.util.Collections;

import flowchart.IfStatement;
import flowchart.WhileStatement;
import flowchart.PrimitiveStatement;

/**
 * the if statement class
 * 
 * @author Weichao Zeng
 * @version 1.0 (Last updated: 2023/06/03)
 */
public class ifStmt implements ast {
    /**
     * the condition of the if statement
     */
    public expr condition;
    /**
     * if condition is true, execute the body
     */
    public stmts body;
    /**
     * else if statements
     */
    public ArrayList<ifStmt> elseIfs;
    /**
     * if none of the conditions are true, execute the else body
     */
    public stmts elseBody;
    /**
     * to define if this node is an else if statement
     */
    public boolean isElseIf;

    public ifStmt() {
        this.condition = null;
        this.body = null;
        this.elseIfs = new ArrayList<>();
        this.elseBody = null;
        this.isElseIf = false;
    }

    public ifStmt(expr condition, stmts body) {
        this.condition = condition;
        this.body = body;
        this.elseIfs = new ArrayList<>();
        this.elseBody = null;
        this.isElseIf = false;
    }

    public ifStmt(expr condition, stmts body, boolean isElseIf) {
        this.condition = condition;
        this.body = body;
        this.elseIfs = new ArrayList<>();
        this.elseBody = null;
        this.isElseIf = isElseIf;
    }

    public ifStmt(expr condition, stmts body, ArrayList<ifStmt> elseIfs, stmts elseBody) {
        this.condition = condition;
        this.body = body;
        this.elseIfs = elseIfs;
        this.elseBody = elseBody;
    }

    public void convert() {
        if (condition != null)
            condition.convert();
        if (body != null)
            body.convert();
        if (elseIfs != null && !elseIfs.isEmpty()) {
            Collections.reverse(elseIfs);
            for (ifStmt i : elseIfs) {
                i.convert();
            }
        }
        if (elseBody != null)
            elseBody.convert();
    }

    @Override
    public String toString() {
        if (this.isElseIf) {
            StringBuilder sb = new StringBuilder();
            sb.append("else if (");
            sb.append(condition.toString());
            sb.append(") {\n");
            sb.append(body.toString());
            sb.append("\n}\n");
            return sb.toString();
        }
        else {
            StringBuilder sb = new StringBuilder();
            sb.append("if (");
            sb.append(condition.toString());
            sb.append(") {\n");
            sb.append(body.toString());
            sb.append("\n}\n");
            if (elseIfs != null && !elseIfs.isEmpty()) {
                for (ifStmt i : elseIfs) {
                    sb.append(i.toString());
                }
            }
            if (elseBody != null) {
                sb.append("else {\n");
                sb.append(elseBody.toString());
                sb.append("\n}\n");
            }
            return sb.toString();
        }
    }

    public IfStatement eval() {
        String condition = this.condition.toString();
        IfStatement ifs = new IfStatement(condition);

        for (stmt s : this.body.statements) {
            Object val = s.eval();

            if (val instanceof WhileStatement) {
                ifs.getTrueBody().add((WhileStatement) val);
            }
            else if (val instanceof IfStatement) {
                ifs.getTrueBody().add((IfStatement) val);
            }
            else if (val instanceof PrimitiveStatement) {
                ifs.getTrueBody().add((PrimitiveStatement) val);
            }
        }

        IfStatement entryPart = ifs;
        
        if (this.elseIfs != null) {
            for (ifStmt elseif : this.elseIfs) {
                IfStatement elsi = new IfStatement(elseif.condition.toString());
                
                for (stmt s : elseif.body.statements) {
                    Object val = s.eval();
        
                    if (val instanceof WhileStatement) {
                        elsi.getTrueBody().add((WhileStatement) val);
                    }
                    else if (val instanceof IfStatement) {
                        elsi.getTrueBody().add((IfStatement) val);
                    }
                    else if (val instanceof PrimitiveStatement) {
                        elsi.getTrueBody().add((PrimitiveStatement) val);
                    }
                }
                
                entryPart.getFalseBody().add(elsi);
                entryPart = elsi;
            }
        }

        if (this.elseBody != null) {
            for (stmt s : this.elseBody.statements) {
                Object val = s.eval();
    
                if (val instanceof WhileStatement) {
                    entryPart.getFalseBody().add((WhileStatement) val);
                }
                else if (val instanceof IfStatement) {
                    entryPart.getFalseBody().add((IfStatement) val);
                }
                else if (val instanceof PrimitiveStatement) {
                    entryPart.getFalseBody().add((PrimitiveStatement) val);
                }
            }
        }

        return ifs;
    }
}
