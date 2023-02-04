package expression;

import java.math.BigInteger;
import java.util.Objects;

public abstract class BinaryOperation implements AnyExpression {
    protected final AnyExpression oper1;
    protected final AnyExpression oper2;
    private int cachedHashCode = 0;
    private String cachedToString = null;
    private String cachedToMiniString = null;

    public BinaryOperation(final AnyExpression oper1, final AnyExpression oper2) {
        this.oper1 = oper1;
        this.oper2 = oper2;
    }

    @Override
    public int evaluate(final int var) {
        return calculate(oper1.evaluate(var), oper2.evaluate(var));
    }

    @Override
    public BigInteger evaluate(final BigInteger var) {
        return calculate1(oper1.evaluate(var), oper2.evaluate(var));
    }

    @Override
    public int evaluate(final int var1, final int var2, final int var3) {
        return calculate(oper1.evaluate(var1, var2, var3), oper2.evaluate(var1, var2, var3));
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
            str.append("(");
            oper1.toString(str);
            str.append(sign());
            oper2.toString(str);
            str.append(")");
            cachedToString = str.substring(lenBegin, str.length());
        } else {
            str.append(cachedToString);
        }
    }

    public boolean earlierParent(final int classCode) {
        return (classCode - 5 >= this.classCode() && classCode < 200);
    }

    public boolean specialCase(final int classCode) {
        return ((classCode == 22) || (classCode == 21 && this.classCode() != 21)
                || (classCode == 12 && this.classCode() != 22 && this.classCode() != 21));
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
            if (earlierParent(classCode) || (order == 2 && specialCase(classCode))) {
                str.append("(");
                oper1.toMiniString(str, this.classCode(), 1);
                str.append(sign());
                oper2.toMiniString(str, this.classCode(), 2);
                str.append(")");
            } else {
                oper1.toMiniString(str, this.classCode(), 1);
                str.append(sign());
                oper2.toMiniString(str, this.classCode(), 2);
            }
            cachedToMiniString = str.substring(lenBegin, str.length());
        } else {
            str.append(cachedToMiniString);
        }
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof BinaryOperation
                && this.getClass().equals(obj.getClass())
                && oper1.equals(((BinaryOperation) obj).oper1)
                && oper2.equals(((BinaryOperation) obj).oper2);
    }

    public abstract int calculate(final int a, final int b);

    public abstract BigInteger calculate1(final BigInteger a, final BigInteger b);

    public abstract String sign();

    public abstract int classCode();

    @Override
    public int hashCode() {
        if (cachedHashCode != 0) {
            return cachedHashCode;
        } else {
            cachedHashCode = Objects.hash(oper1.hashCode() * 7, oper2.hashCode() * 31, getClass());
            return cachedHashCode;
        }
//        return Objects.hash(oper1.hashCode() * 7, oper2.hashCode() * 31, getClass());
    }
}
