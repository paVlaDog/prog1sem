package expression;

import java.math.BigInteger;

public class T0 extends UnaryOperation{
    public T0(final AnyExpression oper) {
        super(oper);
    }

    @Override
    public int evaluate (final int var) {
        return Integer.numberOfTrailingZeros(oper.evaluate(var));
    }

    @Override
    public BigInteger evaluate (final BigInteger var) {
        throw new NumberFormatException("T0 only for int. For BigInteger in DLC");
    }

    @Override
    public int evaluate (final int var1, final int var2, final int var3) {
        return Integer.numberOfTrailingZeros(oper.evaluate(var1, var2, var3));
    }

    public int classCode() {
        return 202;
    }

    public String sign(){
        return "t0";
    }
}
