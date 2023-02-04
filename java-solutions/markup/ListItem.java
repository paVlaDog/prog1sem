package markup;

import java.util.*;

public class ListItem implements Item {
	private List<ListOrParagraph> list;
	
	public ListItem (List<ListOrParagraph> list) {
		this.list = list;
	}

	public void toBBCode(StringBuilder builder) {
		builder.append(SepBBBegin());
		for (ListOrParagraph it : list) {
			it.toBBCode(builder);
		}
	}	

	public String SepBBBegin() {
		return "[*]";
	}

	public String SepBBEnd() {
		return "";
	}	
}