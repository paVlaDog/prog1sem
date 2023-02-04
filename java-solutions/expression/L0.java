package expression;

import java.math.BigInteger;

public class L0 extends UnaryOperation{
    public L0 (final AnyExpression oper) {
        super(oper);
    }

    @Override
    public int evaluate (final int var) {
        return Integer.numberOfLeadingZeros(oper.evaluate(var));
    }

    @Override
    public BigInteger evaluate (final BigInteger var) {
        throw new NumberFormatException("L0 only for int. For BigInteger in DLC");
    }

    @Override
    public int evaluate (final int var1, final int var2, final int var3) {
        return Integer.numberOfLeadingZeros(oper.evaluate(var1, var2, var3));
    }

    public int classCode() {
        return 202;
    }

    public String sign(){
        return "l0";
    }
}
