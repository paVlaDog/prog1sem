package expression;

import java.math.BigInteger;
import java.lang.Math;

public class Max extends BinaryOperation {
    public Max (final AnyExpression value1, final AnyExpression value2) {
        super(value1, value2);
    }

    @Override
    public int calculate(final int a, final int b) {
        return Math.max(a, b);
    }

    @Override
    public BigInteger calculate1(BigInteger a, BigInteger b) {
        throw new NumberFormatException("Max only for int. For BigInteger in DLC");
    }


    @Override
    public String sign() {
        return " max ";
    }

    @Override
    public int classCode() {
        return 1;
    }
}