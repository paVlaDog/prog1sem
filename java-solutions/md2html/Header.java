package md2html;

import java.util.List;

public class Header extends AbstractInLine {
    String level;

    public Header (List<InLine> list, String level) {
        super(list);
        this.level = level;
    }

    public String SepBBBegin() {
        return "<h" + level + ">";
    }

    public String SepBBEnd() {
        return "</h" + level + ">";
    }
}
