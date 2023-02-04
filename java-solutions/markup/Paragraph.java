package markup;

import java.util.List;

public class Paragraph extends AbstractBlock implements ListOrParagraph {
	public Paragraph(final List<Block> list) {
		super(list);
	}

	public String sep() {
		return "";
	}

	public String SepBBBegin() {
		return "";
	}

	public String SepBBEnd() {
		return "";
	}
}
