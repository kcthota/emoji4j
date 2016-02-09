package emoji4j;

import java.util.List;
import java.util.regex.Matcher;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Emoji
 * @author Krishna Chaitanya Thota
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Emoji extends AbstractEmoji {

	private String emoji;

	private List<String> aliases;

	private String hexHtml;

	private String decimalHtml;
	
	private String decimalHtmlShort;
	
	private String hexHtmlShort;
	
	private String decimalSurrogateHtml;
	
	private List<String> emoticons;

	/**
	 * Gets the unicode emoji character
	 * @return Emoji String
	 */
	public String getEmoji() {
		return emoji;
	}

	public void setEmoji(String emoji) {
		setDecimalHtml(EmojiUtils.htmlifyHelper(emoji,false, false));
		setHexHtml(EmojiUtils.htmlifyHelper(emoji,true, false));
		
		setDecimalSurrogateHtml(EmojiUtils.htmlifyHelper(emoji,false, true));
		this.emoji = emoji;
	}

	/**
	 * Gets the list of all shortcodes for the emoji. shortcode is not enclosed in colons.
	 * @return List of all aliases
	 */
	public List<String> getAliases() {
		return aliases;
	}

	public void setAliases(List<String> aliases) {
		this.aliases = aliases;
	}

	/**
	 * Gets the hexadecimal html entity for the emoji
	 * @return the hexadecimal html string
	 */
	public String getHexHtml() {
		return hexHtml;
	}

	public void setHexHtml(String hexHtml) {
		this.hexHtml = hexHtml;
		Matcher matcher = htmlSurrogateEntityPattern.matcher(hexHtml);
		if(matcher.find()) {
			String signifiantHtmlEntity = matcher.group("H");
			this.setHexHtmlShort(signifiantHtmlEntity);
		} else {
			this.setHexHtmlShort(hexHtml);
		}
	}

	/**
	 * Gets the decimal html entity for the emoji
	 * @return the decimal html string
	 */
	public String getDecimalHtml() {
		return decimalHtml;
	}

	public void setDecimalHtml(String decimalHtml) {
		
		this.decimalHtml = decimalHtml;
		Matcher matcher = htmlSurrogateEntityPattern.matcher(decimalHtml);
		if(matcher.find()) {
			String signifiantHtmlEntity = matcher.group("H");
			this.setDecimalHtmlShort(signifiantHtmlEntity);
		} else {
			this.setDecimalHtmlShort(decimalHtml);
		}
	}

	/**
	 * Gets the list of emoticons associated with the emoji
	 * @return List of all emoticons associated with the emoji
	 */
	public List<String> getEmoticons() {
		return emoticons;
	}

	public void setEmoticons(List<String> emoticons) {
		//for(String emoticon:emoticons) {
			//EmojiManager.addEmoticon(emoticon);
		//}
		this.emoticons = emoticons;
	}

	public String getDecimalSurrogateHtml() {
		return decimalSurrogateHtml;
	}

	public void setDecimalSurrogateHtml(String decimalSurrogateHtml) {
		this.decimalSurrogateHtml = decimalSurrogateHtml;
	}

	public String getDecimalHtmlShort() {
		return decimalHtmlShort;
	}

	public void setDecimalHtmlShort(String decimalHtmlShort) {
		this.decimalHtmlShort = decimalHtmlShort;
	}

	public String getHexHtmlShort() {
		return hexHtmlShort;
	}

	public void setHexHtmlShort(String hexHtmlShort) {
		this.hexHtmlShort = hexHtmlShort;
	}
	
	
	
}
