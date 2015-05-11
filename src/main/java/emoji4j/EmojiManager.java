package emoji4j;

import java.io.InputStream;
import java.util.List;

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

	

}
