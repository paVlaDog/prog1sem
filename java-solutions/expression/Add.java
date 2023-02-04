package expression;

import java.math.BigInteger;

public class Add extends BinaryOperation {
    public Add (final AnyExpression value1, final AnyExpression value2) {
        super(value1, value2);
    }

    @Override
    public int calculate(final int a, final int b) {
        return (a + b);
    }

    @Override
    public BigInteger calculate1(final BigInteger a, final BigInteger b) {
        return (a.add(b));
    }


    @Override
    public String sign() {
        return " + ";
    }

    @Override
    public int classCode() {
        return 11;
    }
}
