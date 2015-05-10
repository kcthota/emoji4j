package emoji4j;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Emoji
 * @author Krishna Chaitanya Thota
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Emoji {

	private String emoji;

	private Set<String> aliases;

	private String hexHtml;

	private String decimalHtml;

	public String getEmoji() {
		return emoji;
	}

	public void setEmoji(String emoji) {
		int htmlcode = Character.codePointAt(emoji, 0);
		setDecimalHtml("&#" + htmlcode + ";");
		setHexHtml("&#x" + Integer.toHexString(htmlcode) + ";");
		this.emoji = emoji;
	}

	public Set<String> getAliases() {
		return aliases;
	}

	public void setAliases(Set<String> aliases) {
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

	
}
