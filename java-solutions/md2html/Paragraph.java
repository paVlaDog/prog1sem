package md2html;

import java.util.List;

public class Paragraph extends AbstractInLine {
	
	public Paragraph (final List<InLine> list) {
		super(list);
	}

	public String SepBBBegin() {
		return "<p>";
	}

	public String SepBBEnd() {
		return "</p>";
	}
}
