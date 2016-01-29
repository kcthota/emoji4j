package emoji4j;

import static ch.lambdaj.Lambda.having;
import static ch.lambdaj.Lambda.on;
import static ch.lambdaj.Lambda.selectFirst;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hamcrest.Matchers;

/**
 * Utils to deal with emojis
 * 
 * @author Krishna Chaitanya Thota
 *
 */
public class EmojiUtils extends AbstractEmoji {

	private static final Pattern shortCodePattern = Pattern.compile(":(\\w+):");
	
	private static final Pattern htmlEntityPattern = Pattern.compile("&#\\w+;");
	
	private static final Pattern shortCodeOrHtmlEntityPattern = Pattern.compile(":\\w+:|&#\\w+;");
	
	/**
	 * Get emoji by unicode, short code, decimal html entity or hexadecimal html
	 * entity
	 * 
	 * @param code
	 * @return Emoji
	 */
	public static Emoji getEmoji(String code) {

		Matcher m = shortCodePattern.matcher(code);

		// test for shortcode with colons
		if (m.find()) {
			code = m.group(1);
		}

		Emoji emoji = selectFirst(
				EmojiManager.data(),
				having(on(Emoji.class).getEmoji(), Matchers.equalTo(code)).or(having(on(Emoji.class).getEmoji(), Matchers.equalTo(code)))
						.or(having(on(Emoji.class).getHexHtml(), Matchers.startsWith(code)))
						.or(having(on(Emoji.class).getDecimalHtml(), Matchers.startsWith(code)))
						.or(having(on(Emoji.class).getAliases(), Matchers.hasItem(code)))
						.or(having(on(Emoji.class).getEmoticons(), Matchers.hasItem(code))));

		return emoji;
	}

	/**
	 * Checks if an Emoji exists for the unicode, short code, decimal or
	 * hexadecimal html entity
	 * 
	 * @param code
	 * @return
	 */
	public static boolean isEmoji(String code) {
		return getEmoji(code) == null ? false : true;
	}

	/**
	 * Converts emoji short codes or html entities in string with emojis
	 * 
	 * @param text
	 * @return
	 */
	public static String emojify(String text) {

		text = processStringWithRegex(text, shortCodeOrHtmlEntityPattern);

		// emotions should be processed in second go.
		// this will avoid conflicts with shortcodes. For Example: :p:p should
		// not
		// be processed as shortcode, but as emoticon
		text = processStringWithRegex(text, EmojiManager.getEmoticonRegexPattern());

		return text;
	}

	/**
	 * Common method used for processing the string to replace with emojis
	 * 
	 * @param text
	 * @param regex
	 * @return
	 */
	private static String processStringWithRegex(String text, Pattern pattern) {
		Matcher matcher = pattern.matcher(text);
		StringBuffer sb = new StringBuffer();

		while (matcher.find()) {
			String emojiCode = matcher.group();
			Emoji emoji = getEmoji(emojiCode);
			// replace matched tokens with emojis
			matcher.appendReplacement(sb, emoji == null ? emojiCode : emoji.getEmoji());
		}

		matcher.appendTail(sb);
		return sb.toString();
	}

	/**
	 * Counts valid emojis passed string
	 * 
	 * @param text
	 * @return
	 */
	public static int countEmojis(String text) {

		String htmlifiedText = htmlify(text);
		// regex to identify html entitities in htmlified text
		Matcher matcher = htmlEntityPattern.matcher(htmlifiedText);

		int counter = 0;
		while (matcher.find()) {
			String emojiCode = matcher.group();
			if (isEmoji(emojiCode)) {
				counter++;
			}
		}
		return counter;
	}

	/**
	 * Converts unicode characters in text to corresponding decimal html
	 * entities
	 * 
	 * @param text
	 * @return
	 */
	public static String htmlify(String text) {
		String emojifiedStr = emojify(text);
		return htmlifyHelper(emojifiedStr, false);
	}

	/**
	 * Converts unicode characters in text to corresponding hexadecimal html
	 * entities
	 * 
	 * @param text
	 * @return
	 */
	public static String hexHtmlify(String text) {
		String emojifiedStr = emojify(text);
		return htmlifyHelper(emojifiedStr, true);
	}

	

	/**
	 * Converts emojis, hex, decimal htmls, emoticons in a string to short codes
	 * 
	 * @param text
	 * @return
	 */
	public static String shortCodify(String text) {
		String emojifiedText = emojify(text);

		// TODO - this approach is ugly, need to find an optimal way to replace
		// the emojis
		// could not find an ideal way to identify emojis in the passed string
		// characters like <3 has multiple characters, but doesn't have
		// surrogate pairs
		// so at this point, we iterate through all the emojis and replace with
		// short codes
		for (Emoji emoji : EmojiManager.data()) {
			StringBuilder shortCodeBuilder = new StringBuilder();
			shortCodeBuilder.append(":").append(emoji.getAliases().get(0)).append(":");

			emojifiedText = emojifiedText.replace(emoji.getEmoji(), shortCodeBuilder.toString());
		}
		return emojifiedText;
	}
	
	/**
	 * Removes all emoji characters from the passed string. This method does not remove html characters, shortcodes.
	 * To remove all shortcodes, html characters, emojify and then pass the emojified string to this method.
	 * @param text
	 * @return
	 */
	public static String removeAllEmojis(String emojiText) {
		
		for (Emoji emoji : EmojiManager.data()) {
			emojiText = emojiText.replace(emoji.getEmoji(), "");
		}
		return emojiText;
	}

}
