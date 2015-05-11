package emoji4j;

import static ch.lambdaj.Lambda.having;
import static ch.lambdaj.Lambda.on;
import static ch.lambdaj.Lambda.selectFirst;

import java.io.InputStream;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hamcrest.Matchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class EmojiManager {
	static List<Emoji> emojiData;

	static {
		try {
			ObjectMapper mapper = new ObjectMapper();
			InputStream stream = EmojiManager.class.getResourceAsStream("/emoji.json");
			emojiData = mapper.readValue(stream, TypeFactory.defaultInstance().constructCollectionType(List.class, Emoji.class));
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	/**
	 * Returns the complete emoji data
	 * @return
	 */
	public static List<Emoji> data() {
		return emojiData;
	}

	/**
	 * Get emoji by unicode, short code, decimal html entity or hexadecimal html entity
	 * @param code
	 * @return Emoji
	 */
	public static Emoji getEmoji(String code) {
		
		Pattern pattern = Pattern.compile(":(\\w+):");
		Matcher m = pattern.matcher(code);
		
		if(m.find()) {
			code = m.group(1);
		}
		
		Emoji emoji = selectFirst(
				data(),
				having(on(Emoji.class).getEmoji(), Matchers.equalTo(code)).or(having(on(Emoji.class).getEmoji(), Matchers.equalTo(code)))
						.or(having(on(Emoji.class).getHexHtml(), Matchers.equalTo(code)))
						.or(having(on(Emoji.class).getDecimalHtml(), Matchers.equalTo(code)))
						.or(having(on(Emoji.class).getAliases(), Matchers.hasItem((code)))));
		
		return emoji;
	}
	

	/**
	 * Checks if an Emoji exists for the unicode, short code, decimal or hexadecimal html entity
	 * @param code
	 * @return
	 */
	public static boolean isEmoji(String code) {
		return getEmoji(code) == null ? false : true;
	}

}
