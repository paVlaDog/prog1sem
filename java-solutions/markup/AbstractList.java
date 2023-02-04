package markup;

import java.util.*;

public abstract class AbstractList {
	private List<ListItem> list;
	
	public AbstractList (List<ListItem> list) {
		this.list = list;
	}

	public void toBBCode (StringBuilder builder) {
		builder.append(sepBBBegin());
		for (ListItem it : list) {
			it.toBBCode(builder);
		}
		builder.append(sepBBEnd());
	}	

	public abstract String sepBBBegin();
	public abstract String sepBBEnd();
}