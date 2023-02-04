package markup;

import java.util.List;

public class Strikeout extends AbstractBlock implements Block {
	
	public Strikeout(final List<Block> list) {
		super(list);
	}
	
	public String sep() {
		return "~";
	}
	public String SepBBBegin() {
		return "[s]";
	}

	public String SepBBEnd() {
		return "[/s]";
	}

}
