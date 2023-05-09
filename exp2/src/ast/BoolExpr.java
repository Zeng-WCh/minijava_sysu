package ast;

import exceptions.ExpressionException;
import exceptions.TypeMismatchedException;
import token.TokenType;

/**
 * BoolExpr AST Node
 * 
 * @author Weichao Zeng
 * @version 1.00 (Last update: 2023/05/09)
 */
public class BoolExpr implements ast {
    /**
     * type 0, BoolExpr ::= true | false
     */
    private boolean value = false;
    /**
     * type 1, BoolExpr ::= ( BoolExpr ), which will assigned to left
     * type 2, BoolExpr ::= BoolExpr op BoolExpr, which will assigned to left, op, right
     * also type2 BoolExpr ::= ArithExpr op ArithExpr, which will assigned to left, op, right
     * type 3, BoolExpr ::= ! BoolExpr, which will assigned to left
     */
    private ast left = null, right = null;
    /**
     * the operator used in type 2
     */
    private TokenType op = null;
    /**
     * to decide use which way to eval and print
     */
    private int type = -1;

    
    /**
     * type 0 constructor
     * 
     * @param value, true or false
     */
    public BoolExpr(boolean value) {
        this.value = value;
        this.type = 0;
    }

    /**
     * type 1 constructor
     * 
     * @param BoolExpr, the BoolExpr
     */
    public BoolExpr(ast BoolExpr) {
        this.left = BoolExpr;
        this.type = 1;
    }

    /**
     * type 2 constructor
     * 
     * @param left, left ExprNode
     * @param op, operator
     * @param right, right ExprNode
     */
    public BoolExpr(ast left, TokenType op, ast right) {
        this.left = left;
        this.op = op;
        this.right = right;
        this.type = 2;
    }

    /**
     * type 3 constructor
     * 
     * @param left, the BoolExpr that will be negated
     * @param op, given that unary bool operator is only '!', so it is not quite necessary, just to make it more general
     */
    public BoolExpr(ast left, TokenType op) {
        this.left = left;
        this.op = op;
        this.type = 3;
    }

    /**
     * In BoolExpr, to reuse the field, we do not specify the type of the field(we use ast instead), so we need to check the type before eval
     * 
     * @throws TypeMismatchedException if type not matched
     */
    private void checkType() throws TypeMismatchedException {
        /**
         * Bool relation operator
         */
        if (this.op == TokenType.tok_greater || this.op == TokenType.tok_less || this.op == TokenType.tok_greater_equal || this.op == TokenType.tok_less_equal || this.op == TokenType.tok_not_equal) {
            if (this.left instanceof ArithExpr && this.right instanceof ArithExpr) {
                return;
            }
            throw new TypeMismatchedException();
        }
        /**
         * Arith relation operator
         */
        if (this.op == TokenType.tok_or || this.op == TokenType.tok_and) {
            if (this.left instanceof BoolExpr && this.right instanceof BoolExpr) {
                return;
            }
            throw new TypeMismatchedException();
        }
        /**
         * Bool unary operator
         */
        if (this.op == TokenType.tok_not) {
            if (this.left instanceof BoolExpr) {
                return;
            }
            throw new TypeMismatchedException();
        }
    }

    @Override
    public double eval() throws ExpressionException {
        checkType();
        if (this.type == 0) {
            return this.value ? 1 : 0;
        }
        else if (this.type == 1) {
            return this.left.eval();
        }
        else if (this.type == 2) {
            double left = this.left.eval();
            double right = this.right.eval();

            if (this.op == TokenType.tok_greater) {
                return left > right ? 1 : 0;
            }
            else if (this.op == TokenType.tok_greater_equal) {
                return left >= right ? 1 : 0;
            }
            else if (this.op == TokenType.tok_less) {
                return left < right ? 1 : 0;
            }
            else if (this.op == TokenType.tok_less_equal) {
                return left <= right ? 1 : 0;
            }
            else if (this.op == TokenType.tok_equal) {
                return left == right ? 1 : 0;
            }
            else if (this.op == TokenType.tok_not_equal) {
                return left != right ? 1 : 0;
            }
            else if (this.op == TokenType.tok_and) {
                boolean l = left == 1 ? true : false;
                boolean r = right == 1 ? true : false;
                return l && r ? 1 : 0;
            }
            else if (this.op == TokenType.tok_or) {
                boolean l = left == 1 ? true : false;
                boolean r = right == 1 ? true : false;
                return l || r ? 1 : 0;
            }
        }
        else if (this.type == 3) {
            if (this.op == TokenType.tok_not) {
                boolean v = (this.left.eval() == 1 ? true : false);
                return !v ? 1 : 0;
            }
        }
        return 0;
    }

    @Override
    public void print(int depth) {
        for (int i = 0; i < depth; ++i) {
            System.out.print(' ');
        }
        if (this.type == 0) {
            System.out.printf("`-- BoolExpr: %s\n", this.value);
        }
        else if (this.type == 1) {
            System.out.printf("`-- BoolExpr: ( BoolExpr )\n");
            this.left.print(depth + 1);
        }
        else if (this.type == 2) {
            try {
                checkType();
            } catch (Exception e) {
                System.err.printf("Type Mismatched!\n");
                System.exit(1);
            }
            String operator = "";
            if (this.op == TokenType.tok_or) {
                operator = "|";
            }
            else if (this.op == TokenType.tok_and) {
                operator = "&";
            }
            else if (this.op == TokenType.tok_greater) {
                operator = ">";
            }
            else if (this.op == TokenType.tok_greater_equal) {
                operator = ">=";
            }
            else if (this.op == TokenType.tok_less) {
                operator = "<";
            }
            else if (this.op == TokenType.tok_less_equal) {
                operator = "<=";
            }
            else if (this.op == TokenType.tok_equal) {
                operator = "=";
            }
            else if (this.op == TokenType.tok_not_equal) {
                operator = "!=";
            }
            if (this.op == TokenType.tok_not || this.op == TokenType.tok_or || this.op == TokenType.tok_and) {
                System.out.printf("`-- BoolExpr: BoolExpr %s BoolExpr\n", operator);
                this.left.print(depth + 1);
                this.right.print(depth + 1);
            }
            else {
                System.out.printf("`-- BoolExpr: ArithExpr %s ArithExpr\n", operator);
                this.left.print(depth + 1);
                this.right.print(depth + 1);
            }
        }
        else if (this.type == 3) {
            System.out.printf("`-- BoolExpr: ! BoolExpr\n");
            this.left.print(depth + 1);
        }
    }
}
