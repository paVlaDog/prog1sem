package expression;

public interface AnyExpression extends Expression, ToMiniString, TripleExpression, BigIntegerExpression {
    void toMiniString(StringBuilder str, int classCode, int order);
    void toString(StringBuilder str);
    int classCode();
}