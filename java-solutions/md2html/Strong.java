package md2html;

import java.util.List;

public class Strong extends AbstractInLine {
	
	public Strong (List<InLine> list) {
		super(list);
	}

	public String SepBBBegin() {
		return "<strong>";
	}

	public String SepBBEnd() {
		return "</strong>";
	}
}
