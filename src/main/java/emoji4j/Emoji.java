package emoji4j;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Emoji
 * @author Krishna Chaitanya Thota
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Emoji {

	private String emoji;

	private List<String> aliases;

	private String hexHtml;

	private String decimalHtml;
	
	private List<String> emoticons;

	public String getEmoji() {
		return emoji;
	}

	public void setEmoji(String emoji) {
		int htmlcode = Character.codePointAt(emoji, 0);
		setDecimalHtml("&#" + htmlcode + ";");
		setHexHtml("&#x" + Integer.toHexString(htmlcode) + ";");
		this.emoji = emoji;
	}

	public List<String> getAliases() {
		return aliases;
	}

	public void setAliases(List<String> aliases) {
		this.aliases = aliases;
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

	public List<String> getEmoticons() {
		return emoticons;
	}

	public void setEmoticons(List<String> emoticons) {
		this.emoticons = emoticons;
	}
	
	
}
