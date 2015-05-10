package emoji4j;

import org.junit.Test;

public class BasicTest {
	
	@Test
	public void test1() {
		Emoji emoji = EmojiManager.getEmoji("&#x1f604;");
		System.out.println(emoji.getEmoji());
	}
}
