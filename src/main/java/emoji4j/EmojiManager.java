package emoji4j;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

/**
 * Loads emojis from resource bundle
 * @author Krishna Chaitanya Thota
 *
 */
public class EmojiManager {
	private static Pattern emoticonRegexPattern;
	
	private static List<Emoji> emojiData;

	static {
		try {
			ObjectMapper mapper = new ObjectMapper();
			InputStream stream = EmojiManager.class.getResourceAsStream("/emoji.json");
			emojiData = mapper.readValue(stream, TypeFactory.defaultInstance().constructCollectionType(List.class, Emoji.class));
			stream.close();
			processEmoticonsToRegex();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	/**
	 * Returns the complete emoji data
	 * @return List of emoji objects
	 */
	public static List<Emoji> data() {
		return emojiData;
	}
	
	/**
	 * Returns the Regex which can match all emoticons in a string
	 * @return regex pattern for emoticons
	 */
	public static Pattern getEmoticonRegexPattern() {
		return emoticonRegexPattern;
	}


	/**
	 * Processes the Emoji data to emoticon regex
	 */
	private static void processEmoticonsToRegex() {
		
		List<String> emoticons=new ArrayList<String>();
		
		for(Emoji e: emojiData) {
			if(e.getEmoticons()!=null) {
				emoticons.addAll(e.getEmoticons());
			}
		}
		
		//List of emotions should be pre-processed to handle instances of subtrings like :-) :-
		//Without this pre-processing, emoticons in a string won't be processed properly
		for(int i=0;i<emoticons.size();i++) {
			for(int j=i+1;j<emoticons.size();j++) {
				String o1=emoticons.get(i);
				String o2=emoticons.get(j);
				
				if(o2.contains(o1)) {
					String temp = o2;
					emoticons.remove(j);
					emoticons.add(i, temp);
				}
			}
		}
		
		
		StringBuilder sb=new StringBuilder();
		for(String emoticon: emoticons) {
			if(sb.length() !=0) {
				sb.append("|");
			}
			sb.append(Pattern.quote(emoticon));
		}
		
		emoticonRegexPattern = Pattern.compile(sb.toString());
	}
}
