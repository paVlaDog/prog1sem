package expression;

import java.math.BigInteger;

public class Variable implements AnyExpression {
    private final String var;

    public Variable (final String var) {
        this.var = var;
    }

    @Override
    public int evaluate (final int var) {
        return var;
    }

    @Override
    public int evaluate (final int var1, final int var2, final int var3) {
        if ("x".equals(var)) {
            return var1;
        }else if ("y".equals(var)) {
            return var2;
        } else {
            return var3;
        }
    }

    @Override
    public BigInteger evaluate (final BigInteger var) {
        return var;
    }

    @Override
    public String toString() {
        return var;
    }

    @Override
    public void toString(final StringBuilder str) {
        str.append(var);
    }

    @Override
    public String toMiniString() {
        return var;
    }

    @Override
    public void toMiniString(final StringBuilder str, final int classCode, final int order) {
        str.append(var);
    }

    @Override
    public boolean equals (Object obj) {
        return obj instanceof Variable &&  var.equals(((Variable) obj).var);
    }

    @Override
    public int hashCode () {
        return var.hashCode() * 17 + 462442;
    }

    @Override
    public int classCode () {
        return 102;
    }
}
