package emoji4j;


public class Emoji {

	private String emoji;

	private String shortCode;

	private String hexHtml;

	private String decimalHtml;

	public String getEmoji() {
		return emoji;
	}

	public void setEmoji(String emoji) {
		int htmlcode = Character.codePointAt(emoji, 0);
		setDecimalHtml("&#x" + htmlcode + ";");
		setHexHtml("&#x" + Integer.toHexString(htmlcode) + ";");
		this.emoji = emoji;
	}

	public String getShortCode() {
		return shortCode;
	}

	public void setShortCode(String shortCode) {

		this.shortCode = shortCode;
	}

	public String getHexHtml() {
		return hexHtml;
	}

	public void setHexHtml(String hexHtml) {
		this.hexHtml = hexHtml;
	}

	public String getDecimalHtml() {
		return decimalHtml;
	}

	public void setDecimalHtml(String decimalHtml) {
		this.decimalHtml = decimalHtml;
	}

}
