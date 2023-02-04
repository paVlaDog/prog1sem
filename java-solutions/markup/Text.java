package markup;

public class Text implements Block {
	private String text;
	
	public Text(String text) {
		this.text = text;
	}
	
	public void toMarkdown(StringBuilder builder) {
		builder.append(text);
	}

	public void toBBCode(StringBuilder builder) {
		builder.append(text);
	}
}
