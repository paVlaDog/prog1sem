package md2html;

import java.util.List;

public class Code extends AbstractInLine {

    public Code (List<InLine> list) {
        super(list);
    }

    public String SepBBBegin() {
        return "<code>";
    }

    public String SepBBEnd() {
        return "</code>";
    }
}
