package emoji4j;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

public class EmojiTest {

	@Test
	public void testEmojiByUnicode() {
		// existing emoji
		Emoji emoji = EmojiUtils.getEmoji("😃");
		assertThat(emoji).isNotNull();
		assertThat(emoji.getAliases().contains("smiley")).isTrue();

		emoji = EmojiUtils.getEmoji("\uD83D\uDC2D");
		assertThat(emoji).isNotNull();
		assertThat(emoji.getHexHtml()).isEqualTo("&#x1f42d;");

		// not an emoji character
		emoji = EmojiUtils.getEmoji("అ");
		assertThat(emoji).isNull();
	}

	@Test
	public void testEmojiByShortCode() {
		// existing emoji
		Emoji emoji = EmojiUtils.getEmoji("blue_car");
		assertThat(emoji).isNotNull();
		assertThat(emoji.getEmoji()).isEqualTo("🚙");

		// not an emoji character
		emoji = EmojiUtils.getEmoji("bluecar");
		assertThat(emoji).isNull();
	}

	@Test
	public void testEmojiByShortCodeWithColons() {
		// existing emoji
		Emoji emoji = EmojiUtils.getEmoji(":blue_car:");
		assertThat(emoji).isNotNull();
		assertThat(emoji.getEmoji()).isEqualTo("🚙");

		// not an emoji character
		emoji = EmojiUtils.getEmoji(":bluecar:");
		assertThat(emoji).isNull();
	}

	@Test
	public void testEmojiByHexHtml() {
		// get by hexhtml
		Emoji emoji = EmojiUtils.getEmoji("&#x1f42d;");
		assertThat(emoji).isNotNull();
		assertThat(emoji.getEmoji()).isEqualTo("🐭");

	}

	@Test
	public void testEmojiByDecimalHtml() {
		// get by decimal html
		Emoji emoji = EmojiUtils.getEmoji("&#128045;");
		assertThat(emoji).isNotNull();
		assertThat(emoji.getEmoji()).isEqualTo("🐭");

	}

	@Test
	public void testIsEmoji() {
		assertThat(EmojiUtils.isEmoji("&#128045;")).isTrue();

		assertThat(EmojiUtils.isEmoji("&#123;")).isFalse();

		assertThat(EmojiUtils.isEmoji("🐭")).isTrue();

		assertThat(EmojiUtils.isEmoji("smile")).isTrue();

		assertThat(EmojiUtils.isEmoji(":smiley:")).isTrue();
	}

	@Test
	public void testEmojify1() {
		String text = "A :cat:, :dog: and a :mouse: became friends. For :dog:'s birthday party, they all had :hamburger:s, :fries:s, :cookie:s and :cake:.";
		assertThat(EmojiUtils.emojify(text))
				.isEqualTo(
						"A 🐱, 🐶 and a 🐭 became friends. For 🐶's birthday party, they all had 🍔s, 🍟s, 🍪s and 🍰.");

		text = "A :cat:, :dog:, :coyote: and a :mouse: became friends. For :dog:'s birthday party, they all had :hamburger:s, :fries:s, :cookie:s and :cake:.";
		assertThat(EmojiUtils.emojify(text))
				.isEqualTo(
						"A 🐱, 🐶, :coyote: and a 🐭 became friends. For 🐶's birthday party, they all had 🍔s, 🍟s, 🍪s and 🍰.");

	}

	@Test
	public void testEmojify2() {
		String text = "A &#128049;, &#x1f436; and a :mouse: became friends. For :dog:'s birthday party, they all had :hamburger:s, :fries:s, :cookie:s and :cake:.";
		assertThat(EmojiUtils.emojify(text))
				.isEqualTo(
						"A 🐱, 🐶 and a 🐭 became friends. For 🐶's birthday party, they all had 🍔s, 🍟s, 🍪s and 🍰.");

		text = "A &#128049;, &#x1f436;, &nbsp; and a :mouse: became friends. For :dog:'s birthday party, they all had :hamburger:s, :fries:s, :cookie:s and :cake:.";
		assertThat(EmojiUtils.emojify(text))
				.isEqualTo(
						"A 🐱, 🐶, &nbsp; and a 🐭 became friends. For 🐶's birthday party, they all had 🍔s, 🍟s, 🍪s and 🍰.");
	}

	@Test
	public void testCountEmojis() {
		String text = "A &#128049;, &#x1f436;,&nbsp;:coyote: and a :mouse: became friends. For :dog:'s birthday party, they all had 🍔s, :fries:s, :cookie:s and :cake:.";
		assertThat(EmojiUtils.countEmojis(text)).isEqualTo(8);
	}

	@Test
	public void testHtmlify() {
		String text = "A :cat:, :dog: and a :mouse: became friends. For :dog:'s birthday party, they all had :hamburger:s, :fries:s, :cookie:s and :cake:.";
		String htmlifiedText = EmojiUtils.htmlify(text);

		assertThat(htmlifiedText)
				.isEqualTo(
						"A &#128049;, &#128054; and a &#128045; became friends. For &#128054;'s birthday party, they all had &#127828;s, &#127839;s, &#127850;s and &#127856;.");

		// also verify by emojifying htmlified text

		assertThat(EmojiUtils.emojify(htmlifiedText))
				.isEqualTo(
						"A 🐱, 🐶 and a 🐭 became friends. For 🐶's birthday party, they all had 🍔s, 🍟s, 🍪s and 🍰.");
	}

	@Test
	public void testHexHtmlify() {
		String text = "A :cat:, :dog: and a :mouse: became friends. For :dog:'s birthday party, they all had :hamburger:s, :fries:s, :cookie:s and :cake:.";
		String htmlifiedText = EmojiUtils.hexHtmlify(text);

		assertThat(htmlifiedText)
				.isEqualTo(
						"A &#x1f431;, &#x1f436; and a &#x1f42d; became friends. For &#x1f436;'s birthday party, they all had &#x1f354;s, &#x1f35f;s, &#x1f36a;s and &#x1f370;.");
		// also verify by emojifying htmlified text
		assertThat(EmojiUtils.emojify(htmlifiedText))
				.isEqualTo(
						"A 🐱, 🐶 and a 🐭 became friends. For 🐶's birthday party, they all had 🍔s, 🍟s, 🍪s and 🍰.");
	}

	@Test
	public void testShortCodifyFromEmojis() {
		String text = "A 🐱, 🐶 and a 🐭 became friends❤️. For 🐶's birthday party, they all had 🍔s, 🍟s, 🍪s and 🍰.";
		assertThat(EmojiUtils.shortCodify(text))
				.isEqualTo(
						"A :cat:, :dog: and a :mouse: became friends:heart:. For :dog:'s birthday party, they all had :hamburger:s, :fries:s, :cookie:s and :cake:.");

	}

	@Test
	public void testShortCodifyFromEmoticons() {
		String text = ":):-),:-):-]:-xP=*:*<3:P:p,=-)";
		String actual = EmojiUtils.shortCodify(text);
		assertThat(actual)
				.isEqualTo(
						":smiley::smiley::sweat_smile::smiley::no_mouth::stuck_out_tongue_closed_eyes::kissing::kissing::heart::stuck_out_tongue::stuck_out_tongue::sweat_smile:");

		assertThat(EmojiUtils.emojify(actual)).isEqualTo(
				"😃😃😅😃😶😝😗😗❤️😛😛😅");
	}

	@Test
	public void testShortCodifyFromHtmlEntities() {
		String text = "A &#128049;, &#128054; and a &#128045; became friends. For &#128054;'s birthday party, they all had &#127828;s, &#127839;s, &#127850;s and &#127856;.";
		assertThat(EmojiUtils.shortCodify(text))
				.isEqualTo(
						"A :cat:, :dog: and a :mouse: became friends. For :dog:'s birthday party, they all had :hamburger:s, :fries:s, :cookie:s and :cake:.");

		text = "A &#x1f431;, &#x1f436; and a &#x1f42d; became friends. For &#x1f436;'s birthday party, they all had &#x1f354;s, &#x1f35f;s, &#x1f36a;s and &#x1f370;.";
		assertThat(EmojiUtils.shortCodify(text))
				.isEqualTo(
						"A :cat:, :dog: and a :mouse: became friends. For :dog:'s birthday party, they all had :hamburger:s, :fries:s, :cookie:s and :cake:.");

	}

	@Test
	public void removeAllEmojisTest() {
		String emojiText = "A 🐱, 🐱 and a 🐭 became friends❤️. For 🐶's birthday party, they all had 🍔s, 🍟s, 🍪s and 🍰.";
		assertThat(EmojiUtils.removeAllEmojis(emojiText))
				.isEqualTo(
						"A ,  and a  became friends. For 's birthday party, they all had s, s, s and .");
	}

	@Test
	public void surrogateDecimalToEmojiTest() {
		String emojiText = "A &#55357;&#56369;, &#x1f436;&#55357;&#56369; and a &#55357;&#56365; became friends. They had &#junk;&#55356;&#57172;&#junk; :-)";
		assertThat(EmojiUtils.emojify(emojiText))
				.isEqualTo(
						"A 🐱, 🐶🐱 and a 🐭 became friends. They had &#junk;🍔&#junk; 😃");

		emojiText = "&#10084;&#65039;&#junk;&#55357;&#56374;";
		assertThat(EmojiUtils.emojify(emojiText)).isEqualTo("❤️&#junk;🐶");

		emojiText = "&#55357;&#56833;";
		assertThat(EmojiUtils.emojify(emojiText)).isEqualTo("😁");
	}

	@Test
	public void toSurrogateDecimalAndBackTest() {
		String text = "😃😃😅😃😶😝😗😗❤️😛😛😅❤️😛";
		String htmlifiedText = EmojiUtils.htmlify(text, true);
		assertThat(htmlifiedText)
				.isEqualTo(
						"&#55357;&#56835;&#55357;&#56835;&#55357;&#56837;&#55357;&#56835;&#55357;&#56886;&#55357;&#56861;&#55357;&#56855;&#55357;&#56855;&#55242;&#56164;&#55296;&#55823;&#55357;&#56859;&#55357;&#56859;&#55357;&#56837;&#55242;&#56164;&#55296;&#55823;&#55357;&#56859;");

		assertThat(EmojiUtils.emojify(htmlifiedText)).isEqualTo(text);
	}

	@Test
	public void surrogateToHTMLTest() {
		String surrogateText = "&#55357;&#56835;&#55357;&#56835;&#55357;&#56837;&#55357;&#56835;&#55357;&#56886;&#55357;&#56861;&#55357;&#56855;&#55357;&#56855;&#55242;&#56164;&#55296;&#55823;&#55357;&#56859;&#55357;&#56859;&#55357;&#56837;&#55242;&#56164;&#55296;&#55823;&#55357;&#56859;";

		String emojiText = "😃😃😅😃😶😝😗😗❤️😛😛😅❤️😛";
		String decHtmlString = EmojiUtils.htmlify(surrogateText);

		String hexHtmlString = EmojiUtils.hexHtmlify(surrogateText);

		assertThat(decHtmlString)
				.isEqualTo(
						"&#128515;&#128515;&#128517;&#128515;&#128566;&#128541;&#128535;&#128535;&#10084;&#65039;&#128539;&#128539;&#128517;&#10084;&#65039;&#128539;");

		assertThat(hexHtmlString)
				.isEqualTo(
						"&#x1f603;&#x1f603;&#x1f605;&#x1f603;&#x1f636;&#x1f61d;&#x1f617;&#x1f617;&#x2764;&#xfe0f;&#x1f61b;&#x1f61b;&#x1f605;&#x2764;&#xfe0f;&#x1f61b;");

		assertThat(EmojiUtils.emojify(decHtmlString)).isEqualTo(emojiText);

		assertThat(EmojiUtils.emojify(hexHtmlString)).isEqualTo(emojiText);
	}
}
