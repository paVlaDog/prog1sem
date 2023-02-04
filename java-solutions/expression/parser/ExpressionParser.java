package expression.parser;

import expression.Add;
import expression.AnyExpression;
import expression.Const;
import expression.Divide;
import expression.Min;
import expression.Max;
import expression.Multiply;
import expression.Minus;
import expression.Subtract;
import expression.Variable;
import expression.L0;
import expression.T0;

public final class ExpressionParser implements Parser{

    public AnyExpression parse(final String string) {
        return parse(new StringCharSource(string));
    }

    public AnyExpression parse(final CharSource source) {
        return new ExpParser(source).parse();
    }

    private class ExpParser extends BaseParser {
        public ExpParser (CharSource source) {
            super(source);
        }

        public AnyExpression parse() {
            return parseValue(END, 10);
        }

        public AnyExpression parseValue(char endSymb, int priority) {
            AnyExpression exp = null;
            while (!take(endSymb)) {
                skipWhitespace();
                if (take('(')){
                    exp = parseValue(')', 10);
                    skipWhitespace();
                } else if (between('1', '9')) {
                    exp = parseNumber(false);
                } else if (take('0')) {
                    exp = new Const(0);
                } else if (take('x') || take('y') || take('z')) {
                    // xxx(2) => 4
                    exp = new Variable(Character.toString(getPrevCh()));
                } else if (priority > 9 && take('m')) {
                    if (take('i') && take('n')) {
                        exp = new Min(exp, parseValue(END, 9));
                    }else if (take('a') && take('x')) {
                        exp = new Max(exp, parseValue(END, 9));
                    }
                }  else if (priority > 8 && take('+')) {
                    exp = new Add(exp, parseValue(END, 8));
                } else if (priority > 8 && !(exp == null) && take('-')) {
                    exp = new Subtract(exp, parseValue(END, 8));
                } else if (priority > 6 && take('*')) {
                    exp = new Multiply(exp, parseValue(END, 6));
                } else if (priority > 6 && take('/')) {
                    exp = new Divide(exp, parseValue(END, 6));
                } else if (take('l') && take('0')) {
                    exp = new L0(parseValue(END, 1));
                } else if (take('t') && take('0')) {
                    exp = new T0(parseValue(END, 1));
                } else if (exp == null && take('-')) {
                    skipWhitespace();
                    if (between('1', '9')){
                        exp = parseNumber(true);
                    } else {
                        exp = new Minus(parseValue(END, 1));
                    }
                } else {
                    break;
                }
            }
            return exp;
        }

        private AnyExpression parseNumber(boolean withMinus) {
            StringBuilder builder = new StringBuilder();
            if (withMinus){
                builder = new StringBuilder("-");
            }
            builder.append(getPrevCh());
            while (between('0', '9')) {
                builder.append(getPrevCh());
            }
            return new Const(Integer.parseInt(builder.toString()));
        }
    }


}
