package markup;

import java.util.List;

public class Strong extends AbstractBlock implements Block {
	
	public Strong (List<Block> list) {
		super(list);
	}
	
	public String sep() {
		return ("__");
	}

	public String SepBBBegin() {
		return "[b]";
	}

	public String SepBBEnd() {
		return "[/b]";
	}
}
