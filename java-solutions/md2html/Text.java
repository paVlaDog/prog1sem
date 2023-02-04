package md2html;

public class Text implements InLine {
	private String text;

	public Text(String text) {
		this.text = text;
	}

	public void toHTML(StringBuilder builder) {
		builder.append(text);
	}
}
