package md2html;

import java.util.List;

public class Strikeout extends AbstractInLine {
	
	public Strikeout (List<InLine> list) {
		super(list);
	}

	public String SepBBBegin() {
		return "<s>";
	}

	public String SepBBEnd() {
		return "</s>";
	}
}
