package markup;

import java.util.*;

public class UnorderedList extends AbstractList implements ListOrParagraph {
	public UnorderedList(final List<ListItem> list) {
		super(list);
	}

	@Override
	public String sepBBBegin() {
		return "[list]";
	}

	@Override
	public String sepBBEnd() {
		return "[/list]";
	}
}