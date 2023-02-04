// Не работает. Хотел узнать, почему я не могу передать лист блоков в этот конструктор
// Блоки наследуются от Item, поэтому по идее всё должно быть хорошо. 
// Но если в AbstractBlock сделать super(list) при extends AbstractItem, всё ломается. Как так?

package markup;

import java.util.*;

public abstract class AbstractItem {
	private List<Item> list;
	
	public AbstractItem (List<Item> list) {
		this.list = list;
	}

	public void toBBCode (StringBuilder builder) {
		builder.append(SepBBBegin());
		for (Item it : list) {
			it.toBBCode(builder);
		}
	}

	public abstract String SepBBBegin();	
	public abstract String SepBBEnd();
}