package expression;

import java.math.BigInteger;
import java.lang.Math;

public class Min extends BinaryOperation {
    public Min (final AnyExpression value1, final AnyExpression value2) {
        super(value1, value2);
    }

    @Override
    public int calculate(final int a, final int b) {
        return Math.min(a, b);
    }

    @Override
    public BigInteger calculate1(final BigInteger a, final BigInteger b) {
        throw new NumberFormatException("Min only for int. For BigInteger in DLC");
    }


    @Override
    public String sign() {
        return " min ";
    }

    @Override
    public int classCode() {
        return 2;
    }
}