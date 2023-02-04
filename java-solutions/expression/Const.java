package expression;

import java.math.BigInteger;

public class Const implements AnyExpression {
    private final BigInteger value;

    public Const (final int value) {
        this.value = BigInteger.valueOf(value);
    }

    public Const (final BigInteger value) {
        this.value = value;
    }

    @Override
    public int evaluate (final int var) {
        return this.value.intValue();
    }

    @Override
    public int evaluate (final int var1, final int var2, final int var3) {
        return this.value.intValue();
    }

    @Override
    public BigInteger evaluate (final BigInteger var) {
        return this.value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public void toString(final StringBuilder str) {
        str.append(value.toString());
    }

    @Override
    public String toMiniString() {
        return value.toString();
    }

    @Override
    public void toMiniString(final StringBuilder str, final int classCode, final int order) {
        str.append(value.toString());
    }

    @Override
    public boolean equals (Object obj) {
        return obj instanceof Const &&  value.intValue() == ((Const) obj).value.intValue();
    }

    @Override
    public int hashCode () {
        return Integer.hashCode(value.intValue()) * 17 + 462442;
    }

    @Override
    public int classCode () {
        return 101;
    }
}
