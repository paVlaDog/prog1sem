package markup;

import java.util.*;

public class OrderedList extends AbstractList implements ListOrParagraph{
	public OrderedList (List<ListItem> list) {
		super(list);
	}

	public String sepBBBegin() {
		return "[list=1]";
	}

	public String sepBBEnd() {
		return "[/list]";
	}
}