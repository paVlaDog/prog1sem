package md2html;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ParseTree {
    private static final Set<String> MARKUP = Set.of("*", "**", "_", "__", "`", "```", "--");

    int index = 0, textBegin = 0, inLastAst, inLastUnd;
    StringBuilder paragraph;
    InLine el;

    public ParseTree(final StringBuilder str) {
        paragraph = str;
        final int numOfOct = hasHeader();
        el = parser(Integer.toString(numOfOct));
    }

    public InLine getEl(){
        return el;
    }

    public int hasHeader() {
        int num = 0;
        for (; paragraph.charAt(index) == '#' && index < paragraph.length(); index++) {
            num++;
        }
        if (Character.isWhitespace(paragraph.charAt(index)) && num != 0) {
            index++;
            return num;
        } else {
            index = 0;
            return 0;
        }
    }

    public InLine parser(final String sep) {
        final List<InLine> list = new ArrayList<>();
        String nowSep;
        textBegin = index;

        for (; index < paragraph.length(); index++) {
            if (paragraph.charAt(index) == '\\') {
                list.add(new Text(paragraph.substring(textBegin, index)));
                textBegin = ++index;
            } else {
                nowSep = findSep();
                if (nowSep != "NO" && (!"```".equals(sep) || nowSep.equals(sep))) {
                    list.add(new Text(paragraph.substring(textBegin, index)));
                    index += nowSep.length();
                    if (!sep.equals(nowSep)) {
                        list.add(parser(nowSep));
                    } else {
                        textBegin = index--;
                        return createEl(list, nowSep);
                    }
                }
            }
        }


        if ("_".equals(sep)) {
            textBegin = inLastUnd;
            index = textBegin + 1;
            return new Text("");
        } else if ("*".equals(sep)) {
            textBegin = inLastAst;
            index = textBegin + 1;
            return new Text("");
        } else {
            if (textBegin < paragraph.length()) {
                list.add(new Text(paragraph.substring(textBegin, index - 1)));
            }
            if ("0".equals(sep)) {
                return new Paragraph(list);
            } else {
                return new Header(list, sep);
            }
        }

    }


    public String findSep() {
        if (index + 2 < paragraph.length() && MARKUP.contains(paragraph.substring(index, index + 3))) {
            return paragraph.substring(index, index + 3);
        } else if (index + 1 < paragraph.length() && MARKUP.contains(paragraph.substring(index, index + 2))) {
            return paragraph.substring(index, index + 2);
        } else if (MARKUP.contains(paragraph.substring(index, index + 1))) {
            switch(paragraph.charAt(index)){
                case('*'):
                    inLastAst = index;
                    break;
                case('_'):
                    inLastUnd = index;
                    break;
            }
            return paragraph.substring(index, index + 1);
        } else {
            return "NO";
        }
    }

    public static InLine createEl(final List<InLine> list, final String nowSep) {
        return switch (nowSep) {
            case "__", "**" -> new Strong(list);
            case "```" -> new CodeWithout(list);
            case "--" -> new Strikeout(list);
            case "`" -> new Code(list);
            case "_", "*" -> new Emphasis(list);
            default -> new Text("");
        };
    }

    public StringBuilder toHtmlOut() {
        final StringBuilder s = new StringBuilder();
        el.toHTML(s);
        return (s);
    }
}
