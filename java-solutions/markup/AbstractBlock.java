package markup;

import java.util.*;

public abstract class AbstractBlock {
	private List<Block> list;
	
	public AbstractBlock(List<Block> list) {
		this.list = list;
	}
	
	public void toMarkdown(StringBuilder builder) {
		builder.append(sep());
		for (Block it : list) {
			it.toMarkdown(builder);
		}
		builder.append(sep());
	}

	public void toBBCode(StringBuilder builder) {
		builder.append(SepBBBegin());
		for (Block it : list) {
			it.toBBCode(builder);
		}
		builder.append(SepBBEnd());
	}

    public abstract String sep();
	public abstract String SepBBBegin();	
	public abstract String SepBBEnd();
}