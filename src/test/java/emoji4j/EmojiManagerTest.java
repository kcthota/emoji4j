package emoji4j;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class EmojiManagerTest {
	
	@Test
	public void testEmojiByUnicode() {
		//existing emoji
		Emoji emoji = EmojiManager.getEmoji("😃");
		assertThat(emoji).isNotNull();
		assertThat(emoji.getAliases().contains("smiley")).isTrue();
		
		//not an emoji character
		emoji = EmojiManager.getEmoji("అ");
		assertThat(emoji).isNull();
	}
	
	
	@Test
	public void testEmojiByShortCode() {
		//existing emoji
		Emoji emoji = EmojiManager.getEmoji("blue_car");
		assertThat(emoji).isNotNull();
		assertThat(emoji.getEmoji()).isEqualTo("🚙");
		
		//not an emoji character
		emoji = EmojiManager.getEmoji("bluecar");
		assertThat(emoji).isNull();
	}
	
	@Test
	public void testEmojiByShortCodeWithColons() {
		//existing emoji
		Emoji emoji = EmojiManager.getEmoji(":blue_car:");
		assertThat(emoji).isNotNull();
		assertThat(emoji.getEmoji()).isEqualTo("🚙");
		
		//not an emoji character
		emoji = EmojiManager.getEmoji(":bluecar:");
		assertThat(emoji).isNull();
	}
	
	@Test
	public void testEmojiByHexHtml() {
		//get by hexhtml
		Emoji emoji = EmojiManager.getEmoji("&#x1f42d;");
		assertThat(emoji).isNotNull();
		assertThat(emoji.getEmoji()).isEqualTo("🐭");
		
	}
	
	@Test
	public void testEmojiByDecimalHtml() {
		//get by decimal html
		Emoji emoji = EmojiManager.getEmoji("&#128045;");
		assertThat(emoji).isNotNull();
		assertThat(emoji.getEmoji()).isEqualTo("🐭");
		
	}
	
	
}
