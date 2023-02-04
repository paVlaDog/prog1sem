package md2html;


import java.util.List;

public class Emphasis extends AbstractInLine {
	
	public Emphasis (List<InLine> list) {
		super(list);
	}

	public String SepBBBegin() {
		return "<em>";
	}

	public String SepBBEnd() {
		return "</em>";
	}
}
