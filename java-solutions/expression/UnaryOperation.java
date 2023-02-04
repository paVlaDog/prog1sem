package expression;

import java.math.BigInteger;
import java.util.Objects;

public abstract class UnaryOperation implements AnyExpression{
    protected final AnyExpression oper;
    private int cachedHashCode = 0;
    private String cachedToString = null;
    private String cachedToMiniString = null;

    public UnaryOperation (AnyExpression oper) {
        this.oper = oper;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        toString(str);
        return str.toString();
    }

    @Override
    public void toString(final StringBuilder str) {
        if (cachedToString == null) {
            int lenBegin = str.length();
            str.append(sign() + "(");
            oper.toString(str);
            str.append(")");
            cachedToString = str.substring(lenBegin, str.length());
        } else {
            str.append(cachedToString);
        }
    }

    @Override
    public String toMiniString() {
        StringBuilder str = new StringBuilder();
        toMiniString(str, 0, 1);
        return str.toString();
    }

    @Override 
    public void toMiniString(final StringBuilder str, final int classCode, final int order) {
        if (cachedToMiniString == null) {
            int lenBegin = str.length();
            if (oper.getClass() != Const.class && oper.getClass() != Variable.class && oper.classCode() < 200) {
                str.append(sign() + "(");
                oper.toMiniString(str, this.classCode(), 1);
                str.append(")");
            } else {
                str.append(sign() + " ");
                oper.toMiniString(str, this.classCode(), 1);
            }
            cachedToMiniString = str.substring(lenBegin, str.length());
        } else {
            str.append(cachedToMiniString);
        }
    }

    // oper.getClass() != Minus.class && oper.getClass() != L0.class && oper.getClass() != T0.class

    @Override
    public boolean equals (Object obj) {
        return obj instanceof UnaryOperation
                &&  this.getClass().equals(obj.getClass())
                &&  oper.equals(((UnaryOperation) obj).oper);
    }

    @Override
    public int hashCode () {
        if (cachedHashCode != 0) {
            return cachedHashCode;
        } else {
            cachedHashCode = Objects.hash(oper.hashCode() * 3, getClass());
            return cachedHashCode;
        }
    }

    public abstract String sign();
    public abstract int classCode();
}
