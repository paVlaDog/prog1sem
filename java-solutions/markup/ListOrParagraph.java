package markup;

public interface ListOrParagraph extends Item {
	@Override
	void toBBCode(StringBuilder str);
}
