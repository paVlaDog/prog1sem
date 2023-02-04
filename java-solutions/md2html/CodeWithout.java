package md2html;

import java.util.List;

public class CodeWithout extends AbstractInLine {

    public CodeWithout (List<InLine> list) {
        super(list);
    }

    public String SepBBBegin() {
        return "<pre>";
    }

    public String SepBBEnd() {
        return "</pre>";
    }
}
