package md2html;

import java.util.*;

public abstract class AbstractInLine implements InLine {
	protected List<InLine> list;

	public AbstractInLine(List<InLine> list) {
		this.list = list;
		//List <Item> list1 = list;
	}

	public void toHTML (StringBuilder builder) {
		builder.append(SepBBBegin());
		for (InLine it : list) {
			it.toHTML(builder);
		}
		builder.append(SepBBEnd());
	}

	public abstract String SepBBBegin();	
	public abstract String SepBBEnd();

}