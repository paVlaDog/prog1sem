package expression;

import java.math.BigInteger;

public class Minus extends UnaryOperation{

    public Minus (final AnyExpression oper) {
        super(oper);
    }

    @Override
    public int evaluate (final int var) {
        return -1 * oper.evaluate(var);
    }

    @Override
    public BigInteger evaluate (final BigInteger var) {
        throw new NumberFormatException("Minus only for int. For BigInteger in DLC");
    }

    @Override
    public int evaluate (final int var1, final int var2, final int var3) {
        return -1 * oper.evaluate(var1, var2, var3);
    }

    public int classCode() {
        return 201;
    }

    @Override
    public String sign(){
        return "-";
    }
}
