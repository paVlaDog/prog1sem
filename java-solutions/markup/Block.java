package markup;

public interface Block extends Item {
	void toMarkdown(StringBuilder str);
	void toBBCode(StringBuilder str);
}
