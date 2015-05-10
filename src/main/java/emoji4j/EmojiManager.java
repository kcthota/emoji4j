package emoji4j;

import static com.kcthota.JSONQuery.expressions.Expr.eq;
import static com.kcthota.JSONQuery.expressions.Expr.or;

import java.io.InputStream;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.kcthota.JSONQuery.Query;

public class EmojiManager {
	static ArrayNode emojiData;

	static {
		try {
			ObjectMapper mapper = new ObjectMapper();
			InputStream stream = EmojiManager.class.getResourceAsStream("/emoji.json");
			List<Emoji> emojiList = mapper.readValue(stream, TypeFactory.defaultInstance().constructCollectionType(List.class,  
					   Emoji.class));
			EmojiManager.emojiData = (ArrayNode) mapper.valueToTree(emojiList);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public static ArrayNode data() {
		return emojiData;
	}

	public static boolean isEmoji(String code) {
		return getEmoji(code) == null ? false : true;
	}

	public static Emoji getEmoji(String code) {
		ArrayNode filtered = Query.q(emojiData).filter(or(eq("emoji", code), eq("shortCode", code), eq("hexHtml", code), eq("decimalHtml", code)));
		if (filtered.size() > 0) {
			ObjectMapper mapper=new ObjectMapper();
			try {
				return mapper.treeToValue(filtered.get(0), Emoji.class);
			} catch (JsonProcessingException e) {
				return null;
			}
		}
		return null;
	}

}
