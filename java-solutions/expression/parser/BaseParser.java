package expression.parser;

public abstract class BaseParser {
    protected final CharSource source;
    protected char ch, prevCh;
    protected static final char END = 0;

    public BaseParser (CharSource source) {
        this.source = source;
        take();
    }

    protected boolean test(final char expected) {
        return ch == expected;
    }

    protected char take() {
        final char result = ch;
        prevCh = ch;
        ch = source.hasNext() ? source.next() : END;
        return result;
    }

    protected boolean take(final char expected) {
        if (test(expected)) {
            take();
            return true;
        } else {
            return false;
        }
    }
    protected boolean between (char ch1, char ch2) {
        for (int i = 0; i <= (int)ch2 - (int)ch1; i++) {
            if (take((char)((int)ch1 + i))) {
                return true;
            }
        }
        return false;
    }

    protected boolean skipWhitespace() {
        if (Character.isWhitespace(ch)) {
            take(ch);
            while (Character.isWhitespace(ch)) {
                take(ch);
            }
            return true;
        }
        return false;
    }

    protected char getPrevCh() {
        return prevCh;
    }
}
