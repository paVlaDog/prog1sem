package markup;

import java.util.List;

public class Emphasis extends AbstractBlock implements Block {
	
	public Emphasis (List<Block> list) {
		super(list);
	}
	
	public String sep() {
		return ("*");
	}

	public String SepBBBegin() {
		return "[i]";
	}

	public String SepBBEnd() {
		return "[/i]";
	}
}
